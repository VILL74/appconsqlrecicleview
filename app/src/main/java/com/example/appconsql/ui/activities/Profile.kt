package com.example.appconsql.ui.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.appconsql.R
import com.example.appconsql.data.model.UserModel
import com.example.appconsql.ui.adapters.UserAdapter
import com.example.appconsql.ui.viewmodels.UserViewModel
import com.example.appconsql.utils.Common

class Profile : AppCompatActivity() {
    lateinit var btnback: Button
    /*lateinit var bntllamar:Button
    lateinit var btnemail:Button
    lateinit var btnmensaje:Button
    lateinit var btnedit:Button*/
    lateinit var btnlogout:Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var userviewmodel: UserViewModel
    private var user: UserModel? = null
    private val tag = "ProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        userviewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnback = findViewById(R.id.btnback)
        /*bntllamar= findViewById(R.id.btnllamar)
        btnemail= findViewById(R.id.btnemail)
        btnmensaje= findViewById(R.id.btnmensaje)
        btnedit= findViewById(R.id.btnedit)*/
        btnlogout= findViewById(R.id.btnlogout)
        recyclerView = findViewById(R.id.rv)

        userviewmodel.userList.observe(this) { userList ->
            if (userList != null) {
                Log.d(tag, "onCreate:$userList")
                val adapter= UserAdapter(userList)
                recyclerView.adapter=adapter
            } else {
                Common.showToast(this, "Error al obtener los datos")
            }
        }

        userviewmodel.getUsers()

        /*val recivirdatos=intent

        val rcelphone = recivirdatos.getStringExtra("celphone")
        val remail = recivirdatos.getStringExtra("email")
        val rname = recivirdatos.getStringExtra("name")
        val repassword = recivirdatos.getStringExtra("password")*/


        btnback.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }
        /*bntllamar.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${user?.celphone}")
            startActivity(intent)
        }

        btnemail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${user?.email}")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo")
            intent.putExtra(Intent.EXTRA_TEXT, "Cuerpo del correo")
            startActivity(intent)
        }

        btnmensaje.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms:${user?.celphone}")
            intent.putExtra("sms_body", "Hola como estas?")
            startActivity(intent)
        }


        btnedit.setOnClickListener{
            val intent= Intent(this, Update::class.java)
            intent.putExtra("id_user",user?.uid.toString())
            startActivity(intent)

            *//*val intent= Intent(this, Update::class.java)
            intent.putExtra("email",user?.email)
            intent.putExtra("password",user?.password)
            intent.putExtra("names",user?.names)
            intent.putExtra("celphone",user?.celphone)
            startActivity(intent)*//*

        }*/

        btnlogout.setOnClickListener{
            logout()
            gologin()
        }




    }

    private fun logout(){
        val sp = getSharedPreferences("data", MODE_PRIVATE)
        val edit = sp.edit()
        edit.clear()
        edit.apply()
    }

    private fun gologin(){
        val intent=Intent(this,Login::class.java)
        startActivity(intent)
    }
    override fun onResume() {
        super.onResume()
        loadUserData()
    }

    private fun loadUserData() {
        userviewmodel.getUsers()
    }


}