package com.example

import com.example.models.Category
import com.example.models.Product
import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.util.*

suspend fun main() {
    val url = "http://localhost:8080/"
    val client = HttpClient(CIO) {
        install(Resources)
        expectSuccess = true
    }
    val products = getProducts(client,url)
    val categories = getCategories(client,url)
    println(products.contentToString())
    println(categories.contentToString())
    client.close()
}

suspend fun getProducts(client: HttpClient, url: String): Array<Product> {
    val response = client.request(url + "product"){
        method = HttpMethod.Get
    }.body<String>()
    return toProductList(response)
}

suspend fun getCategories(client: HttpClient, url: String): Array<Category> {
    val response = client.request(url + "category"){
        method = HttpMethod.Get
    }.body<String>()
    return toCategoryList(response)
}

fun toProductList(response: String): Array<Product> {
    val s = response.substring(12,response.length-1)
    val list: MutableList<Array<Product>> = mutableListOf(GsonBuilder().create().fromJson(s, Array<Product>::class.java))
    return list[0]
}

fun toCategoryList(response: String): Array<Category> {
    val s = response.substring(14,response.length-1)
    val list: MutableList<Array<Category>> = mutableListOf(GsonBuilder().create().fromJson(s, Array<Category>::class.java))
    return list[0]
}