package com.example.models

import org.jetbrains.exposed.sql.*

data class Product(val id: Int, val name: String, val price: Double, val mass: Double, val dateAdded: String, val category_id: Int)

object Products : Table(){
    val id = integer("id").autoIncrement()
    val name = varchar("name",128)
    val price = double("price")
    val mass = double("mass")
    val dateAdded = varchar("dateAdded",8) // np. 01012020
    val category_id = integer("category_id")

    override val primaryKey = PrimaryKey(id)

    fun toProduct(row: ResultRow): Product =
        Product(
            id = row[id],
            name = row[name],
            price = row[price],
            mass = row[mass],
            dateAdded = row[dateAdded],
            category_id = row[category_id]
        )
}