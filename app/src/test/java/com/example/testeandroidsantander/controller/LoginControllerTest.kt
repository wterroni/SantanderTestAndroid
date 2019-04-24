package com.example.testeandroidsantander.controller

import io.mockk.*
import org.junit.Test
import org.junit.Assert.*
import java.util.regex.Pattern


class LoginControllerTest {

    private val loginController = LoginController()

    @Test
    fun validEmail_IsNotEmail() {
        val email = "hello@gmail.com"
        /*mockkObject(android.util.Patterns.EMAIL_ADDRESS)
        every { android.util.Patterns.EMAIL_ADDRESS } returns Patterns.EMAIL_ADDRESS
        assertTrue(loginController.validEmail(email))

        mockkObject(android.util.Patterns.EMAIL_ADDRESS)*/

        mockkStatic(android.util.Patterns::class)
        every { android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() } returns true
    }

    @Test
    fun validEmail_IsEmail() {
        val email = "teste@santander.com.br"
        assertTrue(loginController.validEmail(email))
    }

    @Test
    fun validUpperCase_IsNotUpperCase() {
        val text = "santander"
        assertFalse(loginController.validUpperCase(text))
    }

    @Test
    fun validUpperCase_IsUpperCase() {
        val text = "Santander"
        assertTrue(loginController.validUpperCase(text))
    }

    @Test
    fun validSpecialCharacter_ThereIsNotSpecialCharacter() {
        val text = "santander"
        assertFalse(loginController.validSpecialCharacter(text))
    }

    @Test
    fun validSpecialCharacter_ThereIsSpecialCharacter() {
        val text = "s@ntander"
        assertTrue(loginController.validSpecialCharacter(text))
    }

    @Test
    fun validAlphaNumeric_IsAlphaNumeric() {
        val text = "banco"
        assertFalse(loginController.validAlphaNumeric(text))
    }

    @Test
    fun validAlphaNumeric_IsNotAlphaNumeric() {
        val text = "banco!!@"
        assertTrue(loginController.validAlphaNumeric(text))
    }

    @Test
    fun isCPF_IsNotCPF() {
        val cpf = "39144510879"
        assertFalse(loginController.isCPF(cpf))
    }

    @Test
    fun isCPF_IsCPF() {
        val cpf = "39144510845"
        assertTrue(loginController.isCPF(cpf))
    }
}

object Patterns {

    private val EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    val EMAIL_ADDRESS = Pattern.compile(EMAIL_PATTERN)

}