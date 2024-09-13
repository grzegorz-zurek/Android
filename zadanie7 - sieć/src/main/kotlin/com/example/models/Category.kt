package com.example.models

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/category")
data class Category(val id: Int, val name: String, val desc: String, val dateAdded: String, val dangerPriority: Int)