package com.example.dao

import com.example.models.User

interface DAOFacade {
    suspend fun allUsers(): List<User>
    suspend fun getUser(name: String): User?
    suspend fun addNewUser(name: String): Boolean
    suspend fun executePayment(user: User): Boolean
}