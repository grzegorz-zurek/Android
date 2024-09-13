package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.*
import com.example.models.Categories.toCategory
import com.example.models.Products.toProduct
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*

class DAOFacadeImpl : DAOFacade {
    override suspend fun allProducts(): List<Product> = dbQuery{
        Products.selectAll().map(::toProduct)
    }

    override suspend fun product(id: Int): Product? = dbQuery{
        Products
            .select {Products.id eq id}
            .map(::toProduct)
            .singleOrNull()
    }

    override suspend fun addNewProduct(name: String, price: Double, mass: Double, dateAdded: String, category_id: Int): Product? = dbQuery{
        val insertStatement = Products.insert {
            it[Products.name] = name
            it[Products.price] = price
            it[Products.mass] = mass
            it[Products.dateAdded] = dateAdded
            it[Products.category_id] = category_id
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::toProduct)
    }

    override suspend fun editProduct(id: Int, name: String, price: Double, mass: Double, dateAdded: String, category_id: Int): Boolean = dbQuery{
        Products.update({ Products.id eq id }) {
            it[Products.name] = name
            it[Products.price] = price
            it[Products.mass] = mass
            it[Products.dateAdded] = dateAdded
        } > 0
    }

    override suspend fun deleteProduct(id: Int): Boolean = dbQuery{
        Products.deleteWhere { Products.id eq id } > 0
    }

    override suspend fun allCategories(): List<Category> = dbQuery{
        Categories.selectAll().map(::toCategory)
    }

    override suspend fun category(id: Int): Category? = dbQuery{
        Categories
            .select {Categories.id eq id}
            .map(::toCategory)
            .singleOrNull()
    }

    override suspend fun addNewCategory(name: String, desc: String, dateAdded: String, dangerPriority: Int): Category? = dbQuery{
        val insertStatement = Categories.insert {
            it[Categories.name] = name
            it[Categories.desc] = desc
            it[Categories.dateAdded] = dateAdded
            it[Categories.dangerPriority] = dangerPriority
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::toCategory)
    }

    override suspend fun editCategory(id: Int, name: String, desc: String, dateAdded: String, dangerPriority: Int): Boolean = dbQuery{
        Categories.update({Categories.id eq id}) {
            it[Categories.name] = name
            it[Categories.desc] = desc
            it[Categories.dateAdded] = dateAdded
            it[Categories.dangerPriority] = dangerPriority
        } > 0
    }

    override suspend fun deleteCategory(id: Int): Boolean = dbQuery{
        Categories.deleteWhere { Categories.id eq id } > 0
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allCategories().isEmpty()){
            addNewCategory("bezpieczne","kategoria nr 1","01012020",0)
        }
        if(allProducts().isEmpty()) {
            addNewProduct("test_produkt",3.14,0.4,"01012020", 1)
        }
    }
}
