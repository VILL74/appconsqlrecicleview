package com.example.appconsql.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.appconsql.R



class MainActivity : AppCompatActivity() {

    lateinit var registerpage: Button
    lateinit var loginpage: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerpage = findViewById(R.id.btnregister)
        loginpage = findViewById(R.id.btnlogin)

        loginpage.setOnClickListener{
            val intent = Intent(this, Login::class.java )
            startActivity(intent)
        }

        registerpage.setOnClickListener{
            val intent = Intent(this, Register::class.java )
            startActivity(intent)
        }

    }
}