package com.example.appconsql.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.appconsql.R
import com.example.appconsql.ui.activities.MainActivity
import com.example.appconsql.ui.viewmodels.UserViewModel
import com.example.appconsql.utils.Common

class Login : AppCompatActivity() {
    lateinit var btnback: Button
    lateinit var btnfotgot: Button
    lateinit var btnlogin: Button
    lateinit var loginemail: EditText
    lateinit var loginpassword: EditText
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //inicializar viewmodel
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        //llamo a la funcion que valida la sesion
        validateSession()

        btnback = findViewById(R.id.btnback)
        btnfotgot = findViewById(R.id.btnfotgot)
        btnlogin = findViewById(R.id.btnlogin)
        loginemail = findViewById(R.id.etemail)
        loginpassword = findViewById(R.id.etpassword)

        userViewModel.loginResult.observe( this,{ isValidLogin->
            if(isValidLogin){
                val email =loginemail.text.toString()
                val password =loginpassword.text.toString()
                saveSession(email, password)
                goProfile()
            }else{
                Common.showToast(this,"Contreseña o correo incorrecto")
            }

        })



        btnback.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }

        btnfotgot.setOnClickListener{
            val intent = Intent(this, ForgotPassword::class.java )
            startActivity(intent)
        }

        btnlogin.setOnClickListener{

            val email = loginemail.text.toString()
            val password = loginpassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {

                userViewModel.validateLogin(email, password)

            } else {
                Toast.makeText(this, " correo o Contraseña son incorrectos", Toast.LENGTH_LONG).show()
            }

            /* val email = loginemail.text.toString()
             val password = loginpassword.text.toString()
             userViewModel.validateLogin(email,password)*/

            //saveSession(email, password)
            //goProfile()
            /*val sp= getSharedPreferences("app_iub", MODE_PRIVATE)
            val edit= sp.edit()
            edit.putString("email",email)
            edit.putString("password",password)
            edit.apply()*/

            /*startActivity(intent)*/
        }


    }

    private fun saveSession(email: String, password: String) {
        val sp = getSharedPreferences("data", MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString("email", email)
        edit.putString("password", password)
        edit.apply()


    }

    /*private fun enviadata(email: String, password: String, names: String, celphone: String) {
        val intent = Intent(this, Profile::class.java).apply {
            putExtra("email", email)
            putExtra("password", password)
            putExtra("names", names)
            putExtra("celphone", celphone)
        }
        startActivity(intent)
    }*/
    private fun goProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    private fun validateSession() {
        val sp = getSharedPreferences("data", MODE_PRIVATE)
        val email = sp.getString("email", "")
        val password = sp.getString("password", "")


        if (email != null && password != null) {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                goProfile()
            }
        }
    }


}

