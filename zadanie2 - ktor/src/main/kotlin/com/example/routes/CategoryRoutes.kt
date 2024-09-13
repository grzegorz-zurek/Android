package com.example.routes

import com.example.dao.dao
import com.example.models.Category
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.categoryRouting() {
    route("/category"){
        get {
            call.respond(mapOf("categories" to dao.allCategories()))
        }
        get("/{id}") {
            val id = call.parameters["id"]
            if(id!=null)
                call.respond(mapOf("category" to dao.category(id.toInt())))
        }
        post {
            val category = call.receive<Category>()
            dao.editCategory(category.id,category.name,category.desc,category.dateAdded,category.dangerPriority)
            call.respondText("Category stored correctly", status = HttpStatusCode.Created)
        }
        put {
            val category = call.receive<Category>()
            dao.addNewCategory(category.name,category.desc,category.dateAdded,category.dangerPriority)
            call.respondText("Category updated correctly", status = HttpStatusCode.Created)
        }
        delete("/{id?}") {
            val id = call.parameters["id"]
            if(id!=null) {
                dao.deleteCategory(id.toInt())
                call.respondText("Category deleted correctly", status = HttpStatusCode.Accepted)
            }
        }
    }
}