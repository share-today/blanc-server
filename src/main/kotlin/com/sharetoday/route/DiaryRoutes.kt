package com.sharetoday.route

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonArray

fun Route.getMyDiaryListRoute() {
    get("/api/diary-storage/date-list/{id?}") {
        val id = call.parameters["id"]
            ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
        val member =
            members.find { it == id } ?: return@get call.respondText("Not Found User", status = HttpStatusCode.NotFound)

        call.respond(buildJsonObject {
            putJsonArray("dateList") {
                diaryList.forEach {
                    add(it)
                }
            }
        })
    }
}


val members = listOf<String>(
    "josh"
)


val diaryList = listOf(
    "2022.12.01",
    "2022.12.02",
    "2022.12.03",
    "2022.12.04",
    "2022.12.05",
    "2022.12.08",
    "2022.12.09",
    "2022.12.14",
    "2022.12.15",
    "2022.12.20",
    "2022.12.23",
    "2022.12.26",
    "2022.12.27"
)
