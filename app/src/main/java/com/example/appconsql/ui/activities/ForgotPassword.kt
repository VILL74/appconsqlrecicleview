package com.example.appconsql.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.appconsql.R


class ForgotPassword : AppCompatActivity() {
    lateinit var btnback: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnback = findViewById(R.id.btnback)

        btnback.setOnClickListener{
            val intent = Intent(this,Login::class.java )
            startActivity(intent)
        }
    }
}