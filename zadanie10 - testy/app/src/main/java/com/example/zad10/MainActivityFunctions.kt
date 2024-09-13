package com.example.zad10

object MainActivityFunctions {
    fun checkLoginCredentials(login: String, password: String): Boolean{
        if(login == "" || password == "")
            return false
        return true
    }
    fun checkPassword(password: String, repeatPassword: String): Boolean{
        if(password == "" || repeatPassword == "")
            return false
        if(password != repeatPassword)
            return false
        if(password.length < 5)
            return false
        return true
    }
    private val list = ArrayList<String>().apply {
        add("In1")
        add("In2")
        add("In3")
    }
    fun inTheList(string: String): Boolean{
        if(list.contains(string))
            return true
        return false
    }
    fun addToList(string: String): Boolean{
        if(!list.contains(string)) {
            list.add(string)
            return true
        }
        return false
    }
    fun removeFromList(string: String): Boolean{
        if(list.contains(string)) {
            list.remove(string)
            return true
        }
        return false
    }
    fun getList(): ArrayList<String>? {
        if(list.size == 0)
            return null
        return list
    }
    fun isFactorialNumber(number: Int): Boolean{
        if(number < 0)
            return false
        var v0 = 0
        var v1 = 1
        while (v0 <= number){
            if(v0 == number)
                return true
            val temp = v1+v0
            v0 = v1
            v1 = temp
        }
        return false
    }
    private val set = HashSet<String>().apply {
        add("N1")
        add("N2")
        add("N3")
    }
    fun isInSet(string: String): Boolean{
        return set.contains(string)
    }
    fun addToSet(string: String): Boolean{
        return set.add(string)
    }
    fun removeFromSet(string: String): Boolean{
        return set.remove(string)
    }
    fun getSet(): HashSet<String>? {
        if(set.isEmpty())
            return null
        return set
    }
    private val products = HashSet<Product>().apply {
        add(Product("Product1","Desc"))
        add(Product("Product2","Desc"))
        add(Product("Product3","Desc"))
    }
    fun isInProducts(product: Product): Boolean {
        return products.contains(product)
    }
    fun addToProducts(product: Product?): Boolean {
        if(product == null)
            return false
        if(product.name == "" || product.description == "")
            return false
        return products.add(product)
    }
    fun removeFromProducts(product: Product?): Boolean {
        if(product == null)
            return false
        return products.remove(product)
    }
    fun getProducts(): HashSet<Product>? {
        if(products.isEmpty())
            return null
        return products
    }
    private val categories = HashSet<Category>().apply {
        add(Category("Category1","Desc"))
        add(Category("Category2","Desc"))
        add(Category("Category3","Desc"))
    }
    fun isInCategories(category: Category): Boolean {
        return categories.contains(category)
    }
    fun addToCategories(category: Category?): Boolean {
        if(category == null)
            return false
        if(category.name == "" || category.description == "")
            return false
        return categories.add(category)
    }
    fun removeFromCategories(category: Category?): Boolean {
        if(category == null)
            return false
        return categories.remove(category)
    }
    fun getCategories(): HashSet<Category>? {
        if(categories.isEmpty())
            return null
        return categories
    }
    private val users = HashSet<User>().apply {
        add(User("User1","Pass123"))
        add(User("User2","Pass465"))
        add(User("User3","Pass321"))
    }
    fun isUsernameAvailable(user: User): Boolean {
        for(u in users) {
           if(u.name == user.name)
               return false
        }
        return true
    }
    fun addUsername(user: User): Boolean {
        if(user.name == "" || user.password == "") {
            return false
        }
        if(!isUsernameAvailable(user)) {
            return false
        }
        return users.add(user)
    }
    fun removeUsername(user: User): Boolean {
        return users.remove(user)
    }
    fun changePassword(user: User, password: String): Boolean {
        if(password.length < 5)
            return false
        for(u in users) {
            if(u == user) {
                u.password = password
                return true
            }
        }
        return false
    }
    fun getUsers(): HashSet<User>? {
        if(users.isEmpty())
            return null
        return users
    }
}