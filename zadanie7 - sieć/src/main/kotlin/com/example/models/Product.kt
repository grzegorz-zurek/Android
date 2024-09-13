package com.example.models

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/product")
data class Product(val id: Int, val name: String, val price: Double, val mass: Double, val dateAdded: String, val category_id: Int)