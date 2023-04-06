package com.sharetoday

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.sharetoday.plugins.*
import io.ktor.network.tls.certificates.*
import org.slf4j.LoggerFactory
import java.io.File

//fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)
//}
//
//fun Application.module() {
//    configureSecurity()
//    configureRouting()
//}

fun main(args: Array<String>) {
    EngineMain.main(args)
    main()
}

fun Application.module() {
    configureRouting()
    configureSerialization()
//    configureTemplating()
}

fun main() {
    val keyStoreFile = File("build/keystore.jks")
    val keyStore = buildKeyStore {
        certificate("sampleAlias") {
            password = "share-today"
            domains = listOf("127.0.0.1", "0.0.0.0", "localhost")
        }
    }
    keyStore.saveToFile(keyStoreFile, "share-today")

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            port = 8082
        }
        sslConnector(
            keyStore = keyStore,
            keyAlias = "sampleAlias",
            keyStorePassword = { "share-today".toCharArray() },
            privateKeyPassword = { "share-today".toCharArray() }) {
            port = 8443
            keyStorePath = keyStoreFile
        }
        module(Application::module)
    }

    embeddedServer(Netty, environment).start(wait = true)
}