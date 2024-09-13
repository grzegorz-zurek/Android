package com.example.models

import org.jetbrains.exposed.sql.*

data class Category(val id: Int, val name: String, val desc: String, val dateAdded: String, val dangerPriority: Int)

object Categories : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name",128)
    val desc = varchar("desc",512)
    val dateAdded = varchar("dateAdded",8)
    val dangerPriority = integer("dangerPriority").default(0)

    override val primaryKey = PrimaryKey(id)

    fun toCategory(row: ResultRow): Category =
        Category(
            id = row[id],
            name = row[name],
            desc = row[desc],
            dateAdded = row[dateAdded],
            dangerPriority = row[dangerPriority]
        )
}