package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.User
import com.example.models.Users
import com.example.models.Users.toUser
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {
    override suspend fun allUsers(): List<User> = dbQuery{
        Users.selectAll().map(::toUser)
    }
    override suspend fun usernameTaken(name: String): Boolean = dbQuery{
        Users.select {Users.name eq name}
            .map(::toUser)
            .singleOrNull() ?: return@dbQuery false
        true
    }
    override suspend fun addNewUser(name: String, password: String): Unit = dbQuery{
        Users.insert {
            it[Users.name] = name
            it[Users.password] = password
        }
    }
    override suspend fun loginUser(name: String, password: String): Boolean = dbQuery{
        val statement = Users.select {Users.name eq name}
            .map(::toUser)
            .singleOrNull() ?: return@dbQuery false
        return@dbQuery statement.password == password
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allUsers().isEmpty()){
            addNewUser("TestUser1","VeryStrongPass")
            addNewUser("TestUser2","123456789")
        }
    }
}