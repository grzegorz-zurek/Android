package com.example.plugins

import com.example.dao.dao
import com.example.models.User
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        route("/register") {
            post {
                val user = call.receive<User>()
                if(dao.usernameTaken(user.name)) {
                    call.respondText("Username already taken!", status = HttpStatusCode.Conflict)
                } else {
                    dao.addNewUser(user.name, user.password)
                    call.respondText("User successfully added.", status = HttpStatusCode.Accepted)
                }
            }
        }
        route("/login") {
            post {
                val user = call.receive<User>()
                if(dao.loginUser(user.name,user.password)){
                    call.respondText("Username authenticated.", status = HttpStatusCode.Accepted)
                } else {
                    call.respondText("Username or password is invalid.", status = HttpStatusCode.Conflict)
                }
            }
        }
    }
}
