package com.example.models

import org.jetbrains.exposed.sql.*

data class User(val id: Int, val name: String, val password: String)

object Users : Table(){
    val id = integer("id").autoIncrement()
    val name = varchar("name",128)
    val password = varchar("password",128)

    override val primaryKey = PrimaryKey(id)

    fun toUser(row: ResultRow): User =
        User(
            id = row[id],
            name = row[name],
            password = row[password]
        )
}
