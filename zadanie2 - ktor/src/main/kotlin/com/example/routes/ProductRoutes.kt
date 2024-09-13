package com.example.routes

import com.example.dao.dao
import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.productRouting() {
    route("/product") {
        get {
            call.respond(mapOf("products" to dao.allProducts()))
        }
        get("/{id}") {
            val id = call.parameters["id"]
            if(id!=null)
                call.respond(mapOf("product" to dao.product(id.toInt())))
        }
        post {
            val product = call.receive<Product>()
            dao.addNewProduct(product.name,product.price,product.mass,product.dateAdded,product.category_id)
            call.respondText("Product stored correctly", status = HttpStatusCode.Created)
        }
        put {
            val product = call.receive<Product>()
            dao.editProduct(product.id,product.name,product.price,product.mass,product.dateAdded,product.category_id)
            call.respondText("Product updated correctly", status = HttpStatusCode.Accepted)
        }
        delete("/{id?}") {
            val id = call.parameters["id"]
            if(id!=null) {
                dao.deleteProduct(id.toInt())
                call.respondText("Product deleted correctly", status = HttpStatusCode.Accepted)
            }
        }
    }
}