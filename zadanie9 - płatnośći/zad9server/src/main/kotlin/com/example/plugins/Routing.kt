package com.example.plugins

import com.example.dao.dao
import com.example.models.User
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        route("/register") {
            post {
                val user = call.receive<String>()
                if(dao.addNewUser(user)) {
                    call.respondText("User successfully added.", status = HttpStatusCode.Accepted)
                } else {
                    call.respondText("Username already taken!", status = HttpStatusCode.Conflict)
                }
            }
        }
        route("/payment") {
            post {
                val user = call.receive<User>()
                System.out.println(user)
                if(dao.executePayment(user)){
                    call.respondText("Payment done successfully!", status = HttpStatusCode.Accepted)
                } else {
                    call.respondText("Insufficient balance.", status = HttpStatusCode.Conflict)
                }
            }
        }
    }
}
