package javabot.operations

import com.google.inject.Inject
import javabot.BaseTest
import org.testng.Assert
import org.testng.annotations.Test

@Test(groups = arrayOf("operations")) class SayOperationsTest : BaseTest() {
    @Inject
    private lateinit var operation: SayOperation

    fun testSay() {
        var response = operation.handleMessage(message("~say MAGNIFICENT"))
        Assert.assertEquals(response[0].value, "MAGNIFICENT")
    }
}