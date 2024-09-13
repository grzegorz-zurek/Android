package com.example.dao

import com.example.models.*

interface DAOFacade {
    suspend fun allProducts(): List<Product>
    suspend fun product(id: Int): Product?
    suspend fun addNewProduct(name: String, price: Double, mass: Double , dateAdded: String, category_id: Int): Product?
    suspend fun editProduct(id: Int, name: String, price: Double, mass: Double , dateAdded: String, category_id: Int): Boolean
    suspend fun deleteProduct(id: Int): Boolean
    suspend fun allCategories(): List<Category>
    suspend fun category(id: Int): Category?
    suspend fun addNewCategory(name: String, desc: String, dateAdded: String, dangerPriority: Int): Category?
    suspend fun editCategory(id: Int, name: String, desc: String, dateAdded: String, dangerPriority: Int): Boolean
    suspend fun deleteCategory(id: Int): Boolean
}