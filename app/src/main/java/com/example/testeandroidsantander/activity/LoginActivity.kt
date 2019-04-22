package com.example.testeandroidsantander.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testeandroidsantander.R
import com.example.testeandroidsantander.controller.LoginController
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    val loginController = LoginController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        passwordButton.setOnClickListener {
            loginClick()
        }
    }

    private fun loginClick() {
        if (validData()) {
            val intent = Intent(this, BankStatementActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validData() : Boolean {

        if (userEditText.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_user_empty),
                Toast.LENGTH_SHORT).show()
            return false
        }
        else if (!loginController.validEmail(userEditText.text.toString())
                    && !loginController.isCPF(userEditText.text.toString())) {
            Toast.makeText(this, getString(R.string.msg_invalid_user),
                Toast.LENGTH_SHORT).show()
            return false
        }
        else if (passwordEditText.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_password_empty),
                Toast.LENGTH_SHORT).show()
            return false
        } else if (!validPassword()) {
            Toast.makeText(this, getString(R.string.msg_invalid_password),
                Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun validPassword(): Boolean {
        return validUpperCase() && validSpecialCharacter() && validAlfaNumeric()
    }

    private fun validUpperCase(): Boolean {
        return passwordEditText.text.toString().any { it.isUpperCase() }
    }

    private fun validSpecialCharacter(): Boolean {
        val regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

        return regex.matcher(passwordEditText.text.toString()).find()
    }

    private fun validAlfaNumeric(): Boolean {
        val regex = Pattern.compile("[^A-Za-z0-9 ]")
        return regex.matcher(passwordEditText.text.toString()).find()
    }

}
