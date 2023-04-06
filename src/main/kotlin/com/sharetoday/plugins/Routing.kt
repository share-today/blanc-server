package com.sharetoday.plugins

import com.sharetoday.route.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        customerRouting()

        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()

        getMyDiaryListRoute()
    }
}
