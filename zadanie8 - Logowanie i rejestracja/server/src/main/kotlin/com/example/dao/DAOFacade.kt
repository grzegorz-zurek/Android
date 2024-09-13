package com.example.dao

import com.example.models.User

interface DAOFacade {
    suspend fun allUsers(): List<User>
    suspend fun usernameTaken(name: String): Boolean
    suspend fun addNewUser(name: String, password: String)
    suspend fun loginUser(name: String, password: String): Boolean
}
