package com.example.zad10

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private var productMock = mock(Product::class.java)
    private var categoryMock = mock(Category::class.java)

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun emptyLogin() {
        val login = ""
        val pass = "123"
        val result = MainActivityFunctions.checkLoginCredentials(login,pass)
        assertEquals(false,result)
    }
    @Test
    fun emptyPasswordForLogin() {
        val login = "Ga"
        val pass = ""
        val result = MainActivityFunctions.checkLoginCredentials(login,pass)
        assertEquals(false,result)
    }
    @Test
    fun goodLogin() {
        val login = "Ga"
        val pass = "123"
        val result = MainActivityFunctions.checkLoginCredentials(login,pass)
        assertEquals(true,result)
    }
    @Test
    fun emptyPassword() {
        val pass = ""
        val repeatPass = "asd"
        val result = MainActivityFunctions.checkPassword(pass,repeatPass)
        assertEquals(false,result)
    }
    @Test
    fun emptyRepeatPassword() {
        val pass = "asd"
        val repeatPass = ""
        val result = MainActivityFunctions.checkPassword(pass,repeatPass)
        assertEquals(false,result)
    }
    @Test
    fun `passwords Don't Match`() {
        val pass = "asd"
        val repeatPass = "gda"
        val result = MainActivityFunctions.checkPassword(pass,repeatPass)
        assertEquals(false,result)
    }
    @Test
    fun passwordNotStrong() {
        val pass = "asd"
        val repeatPass = "asd"
        val result = MainActivityFunctions.checkPassword(pass,repeatPass)
        assertEquals(false,result)
    }
    @Test
    fun goodPassword() {
        val pass = "ASd45ghf@"
        val repeatPass = "ASd45ghf@"
        val result = MainActivityFunctions.checkPassword(pass,repeatPass)
        assertEquals(true,result)
    }
    @Test
    fun existingElement() {
        val s = "In1"
        val result = MainActivityFunctions.inTheList(s)
        assertEquals(true,result)
    }
    @Test
    fun newElement() {
        val s = "hm"
        val result = MainActivityFunctions.inTheList(s)
        assertEquals(false,result)
    }
    @Test
    fun addExistingElement() {
        val s = "In1"
        val result = MainActivityFunctions.addToList(s)
        assertEquals(false,result)
    }
    @Test
    fun addNewElement() {
        val s = "hm123"
        val result = MainActivityFunctions.addToList(s)
        assertEquals(true,result)
    }
    @Test
    fun removeExistingElement() {
        val s = "In2"
        val result = MainActivityFunctions.removeFromList(s)
        assertEquals(true,result)
    }
    @Test
    fun removeNonExistingElement() {
        val s = "asd123"
        val result = MainActivityFunctions.removeFromList(s)
        assertEquals(false,result)
    }
    @Test
    fun addThenCheckElement() {
        val s = "pls1235hh54"
        var result = MainActivityFunctions.addToList(s)
        assertTrue(result)
        result = MainActivityFunctions.inTheList(s)
        assertTrue(result)
    }
    @Test
    fun addThenRemoveElement() {
        val s = "pls1235"
        var result = MainActivityFunctions.addToList(s)
        assertTrue(result)
        result = MainActivityFunctions.removeFromList(s)
        assertTrue(result)
    }
    @Test
    fun getList() {
        val result = MainActivityFunctions.getList()
        assert(result != null)
    }
    @Test
    fun negativeFact() {
        val number = -123
        val result = MainActivityFunctions.isFactorialNumber(number)
        assertEquals(false, result)
    }
    @Test
    fun zeroFact() {
        val number = 0
        val result = MainActivityFunctions.isFactorialNumber(number)
        assertEquals(true, result)
    }
    @Test
    fun nonFactNum() {
        val number = 123
        val result = MainActivityFunctions.isFactorialNumber(number)
        assertEquals(false, result)
    }
    @Test
    fun properFactNum() {
        val number = 21
        val result = MainActivityFunctions.isFactorialNumber(number)
        assertEquals(true, result)
    }
    @Test
    fun notInSet() {
        val string = "asd"
        val result = MainActivityFunctions.isInSet(string)
        assertEquals(false,result)
    }
    @Test
    fun inSet() {
        val string = "N1"
        val result = MainActivityFunctions.isInSet(string)
        assertEquals(true,result)
    }
    @Test
    fun addNewElementToSet() {
        val string = "asd"
        val result = MainActivityFunctions.addToSet(string)
        assertEquals(true,result)
    }
    @Test
    fun addExistingElementToSet() {
        val string = "N1"
        val result = MainActivityFunctions.addToSet(string)
        assertEquals(false,result)
    }
    @Test
    fun removeExistingElementFromSet() {
        val string = "N2"
        val result = MainActivityFunctions.removeFromSet(string)
        assertEquals(true,result)
    }
    @Test
    fun removeNonExistingElementFromSet() {
        val string = "htg"
        val result = MainActivityFunctions.removeFromSet(string)
        assertEquals(false,result)
    }
    @Test
    fun addThenCheckInSet() {
        val string = "asdfggdhgh"
        var result = MainActivityFunctions.addToSet(string)
        assertTrue(result)
        result = MainActivityFunctions.isInSet(string)
        assertTrue(result)
    }
    @Test
    fun addThenRemoveFromSet() {
        val string = "asdfggdhgh"
        var result = MainActivityFunctions.addToSet(string)
        assertTrue(result)
        result = MainActivityFunctions.removeFromSet(string)
        assertTrue(result)
    }
    @Test
    fun getSet() {
        val result = MainActivityFunctions.getSet()
        assert(result != null)
    }
    @Test
    fun notInProducts() {
        val product = Product("Product123","Desc")
        val result = MainActivityFunctions.isInProducts(product)
        assertEquals(false,result)
    }
    @Test
    fun inProducts() {
        val product = Product("Product1","Desc")
        val result = MainActivityFunctions.isInProducts(product)
        assertEquals(true,result)
    }
    @Test
    fun addNullToProducts() {
        val result = MainActivityFunctions.addToProducts(null)
        assertEquals(false,result)
    }
    @Test
    fun addProductWithEmptyName() {
        val product = Product("","Desc")
        val result = MainActivityFunctions.addToProducts(product)
        assertEquals(false,result)
    }
    @Test
    fun addProductWithEmptyDesc() {
        val product = Product("Product1","")
        val result = MainActivityFunctions.addToProducts(product)
        assertEquals(false,result)
    }
    @Test
    fun addExistingProduct() {
        val product = Product("Product1","Desc")
        val result = MainActivityFunctions.addToProducts(product)
        assertEquals(false,result)
    }
    @Test
    fun addProduct() {
        val product = Product("Product4","Desc")
        val result = MainActivityFunctions.addToProducts(product)
        assertEquals(true,result)
    }
    @Test
    fun removeNullFromProducts() {
        val result = MainActivityFunctions.removeFromProducts(null)
        assertEquals(false,result)
    }
    @Test
    fun removeNonExistingProduct() {
        val product = Product("product12345","desc")
        val result = MainActivityFunctions.removeFromProducts(product)
        assertEquals(false,result)
    }
    @Test
    fun removeExistingProduct() {
        val product = Product("Product2","Desc")
        val result = MainActivityFunctions.removeFromProducts(product)
        assertEquals(true,result)
    }
    @Test
    fun addAndCheckProduct() {
        val product = Product("Apple","Desc")
        var result = MainActivityFunctions.addToProducts(product)
        assertTrue(result)
        result = MainActivityFunctions.isInProducts(product)
        assertTrue(result)
    }
    @Test
    fun addThenRemoveProduct() {
        val product = Product("Apple","Desc")
        var result = MainActivityFunctions.addToProducts(product)
        assertTrue(result)
        result = MainActivityFunctions.removeFromProducts(product)
        assertEquals(true,result)
    }
    @Test
    fun getProducts() {
        val result = MainActivityFunctions.getProducts()
        assert(result != null)
    }
    @Test
    fun notInCategories() {
        val category = Category("Category123","desc")
        val result = MainActivityFunctions.isInCategories(category)
        assertFalse(result)
    }
    @Test
    fun inCategories() {
        val category = Category("Category1","Desc")
        val result = MainActivityFunctions.isInCategories(category)
        assertTrue(result)
    }
    @Test
    fun addNullToCategories() {
        val result = MainActivityFunctions.addToCategories(null)
        assertFalse(result)
    }
    @Test
    fun addCategoryWithEmptyName() {
        val category = Category("","desc")
        val result = MainActivityFunctions.addToCategories(category)
        assertFalse(result)
    }
    @Test
    fun addCategoryWithEmptyDesc() {
        val category = Category("category312","")
        val result = MainActivityFunctions.addToCategories(category)
        assertFalse(result)
    }
    @Test
    fun addExistingCategory() {
        val category = Category("Category1","Desc")
        val result = MainActivityFunctions.addToCategories(category)
        assertFalse(result)
    }
    @Test
    fun addCategory() {
        val category = Category("Category112","Desc")
        val result = MainActivityFunctions.addToCategories(category)
        assertTrue(result)
    }
    @Test
    fun removeNullFromCategories() {
        val result = MainActivityFunctions.removeFromCategories(null)
        assertFalse(result)
    }
    @Test
    fun removeNonExistingCategory() {
        val category = Category("Category777","Desc")
        val result = MainActivityFunctions.removeFromCategories(category)
        assertFalse(result)
    }
    @Test
    fun removeExistingCategory() {
        val category = Category("Category2","Desc")
        val result = MainActivityFunctions.removeFromCategories(category)
        assertTrue(result)
    }
    @Test
    fun addThenRemoveCategory() {
        val category = Category("Vegetable","Desc")
        var result = MainActivityFunctions.addToCategories(category)
        assertTrue(result)
        result = MainActivityFunctions.removeFromCategories(category)
        assertTrue(result)
    }
    @Test
    fun getCategories() {
        val result = MainActivityFunctions.getCategories()
        assert(result != null)
    }
    @Test
    fun notInUsers() {
        val user = User("user","asd")
        val result = MainActivityFunctions.isUsernameAvailable(user)
        assertTrue(result)
    }
    @Test
    fun inUsers() {
        val user = User("User1","123553")
        val result = MainActivityFunctions.isUsernameAvailable(user)
        assertFalse(result)
    }
    @Test
    fun addUserWithEmptyName() {
        val user = User("","123553")
        val result = MainActivityFunctions.addUsername(user)
        assertFalse(result)
    }
    @Test
    fun addUserWithEmptyPass() {
        val user = User("Userrr","")
        val result = MainActivityFunctions.addUsername(user)
        assertFalse(result)
    }
    @Test
    fun addUserWithExistingName() {
        val user = User("User1","Pass123")
        val result = MainActivityFunctions.addUsername(user)
        assertFalse(result)
    }
    @Test
    fun addUser() {
        val user = User("User213","Pass5123")
        val result = MainActivityFunctions.addUsername(user)
        assertTrue(result)
    }
    @Test
    fun removeNonExistingUser() {
        val user = User("asdg","dasd12321")
        val result = MainActivityFunctions.removeUsername(user)
        assertFalse(result)
    }
    @Test
    fun removeExistingUser() {
        val user = User("User2","Pass465")
        val result = MainActivityFunctions.removeUsername(user)
        assertTrue(result)
    }
    @Test
    fun changePasswordWithShortPass() {
        val user = User("User3","Pass321")
        val pass = "123"
        val result = MainActivityFunctions.changePassword(user,pass)
        assertFalse(result)
    }
    @Test
    fun changePasswordForNonExistingUser() {
        val user = User("User3444424234","Pass321")
        val pass = "12345123"
        val result = MainActivityFunctions.changePassword(user,pass)
        assertFalse(result)
    }
    @Test
    fun changePassword() {
        val user = User("User3","Pass321")
        val pass = "ASDG#@$"
        val result = MainActivityFunctions.changePassword(user,pass)
        assertTrue(result)
    }
    @Test
    fun addThenCheckUser() {
        val user = User("User3333333","Pass321")
        var result = MainActivityFunctions.addUsername(user)
        assertTrue(result)
        result = MainActivityFunctions.isUsernameAvailable(user)
        assertFalse(result)
    }
    @Test
    fun addThenRemoveUser() {
        val user = Fixture.defaultUser
        var result = MainActivityFunctions.addUsername(user)
        assertTrue(result)
        result = MainActivityFunctions.removeUsername(user)
        assertTrue(result)
    }
    @Test
    fun getUsers() {
        val result = MainActivityFunctions.getUsers()
        assert(result != null)
    }
}