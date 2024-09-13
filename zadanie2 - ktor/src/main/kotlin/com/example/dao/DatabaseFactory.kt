package com.example.dao

import com.example.models.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init() {
        val database = Database.connect("jdbc:sqlite:data.sqlite", "org.sqlite.JDBC")
        transaction(database) {
            SchemaUtils.create(Products)
            SchemaUtils.create(Categories)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}