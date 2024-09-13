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
    private val defaultBalance: Double = 250.0
    override suspend fun allUsers(): List<User> = dbQuery{
        Users.selectAll().map(Users::toUser)
    }

    override suspend fun getUser(name: String): User? = dbQuery{
        return@dbQuery Users.select { Users.name eq name }
            .map(::toUser)
            .singleOrNull()
    }

    override suspend fun addNewUser(name: String): Boolean = dbQuery{
        if(getUser(name) != null){
            return@dbQuery false
        }
        Users.insert {
            it[Users.name] = name
            it[balance] = defaultBalance
        }
        return@dbQuery true
    }

    override suspend fun executePayment(user: User): Boolean = dbQuery{
        addNewUser(user.name)
        val dbUser = getUser(user.name)
        if(dbUser!!.balance > user.balance){
            dbUser.balance -= user.balance
            return@dbQuery true
        }
        return@dbQuery false
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allUsers().isEmpty()){
            addNewUser("TestUser1")
            addNewUser("TestUser2")
        }
    }
}