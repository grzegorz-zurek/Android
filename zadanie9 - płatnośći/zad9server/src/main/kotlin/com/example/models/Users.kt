package com.example.models

import org.jetbrains.exposed.sql.*

data class User(val id: Int, val name: String, var balance: Double)

object Users : Table(){
    val id = integer("id").autoIncrement()
    val name = varchar("name",128)
    var balance = double("balance")

    override val primaryKey = PrimaryKey(id)

    fun toUser(row: ResultRow): User =
        User(
            id = row[id],
            name = row[name],
            balance = row[balance]
        )
}