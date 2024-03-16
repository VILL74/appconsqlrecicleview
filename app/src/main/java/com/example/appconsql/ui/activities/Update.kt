package com.example.appconsql.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.appconsql.R
import com.example.appconsql.data.model.UserModel
import com.example.appconsql.ui.viewmodels.UserViewModel
import com.example.appconsql.utils.Common

class Update : AppCompatActivity() {
    lateinit var btnback: Button
    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var names: TextView
    lateinit var celphone: TextView
    lateinit var btnregister: Button

    private lateinit var userviewmodel: UserViewModel
    private var user: UserModel? = null
    private val tag = "ProfileActivity"
    private  var uid:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        btnback = findViewById(R.id.btnback)
        email= findViewById(R.id.etemail)
        password= findViewById(R.id.etpassword)
        names= findViewById(R.id.etnames)
        celphone= findViewById(R.id.etcelphone)
        btnregister= findViewById(R.id.btnregistrar)

        userviewmodel = ViewModelProvider(this).get(UserViewModel::class.java)

        intent.getStringExtra("id_user").let {
            Log.d(tag,"it $it")
            if (it.isNullOrEmpty()) {
                Log.d(tag,"it $it")
                Common.showToast(this, "ocurrio un error al mostrar los datos de dicho usuario")
            } else {
                uid = it.toLong()
                userviewmodel.getUserByUid(uid)

            }

        }

        userviewmodel.userForUid.observe(this) { user ->
            email.text = user.email
            password.text = user.password
            names.text = user.names
            celphone.text = user.celphone.toString()

        }

        userviewmodel.updateUser.observe(this){
                isUpdate->
            Log.d(tag,"isUpdate $isUpdate")

        }

        btnregister.setOnClickListener{
            val email = email.text.toString()
            val password = password.text.toString()
            val names = names.text.toString()
            val celphone = celphone.text.toString()

            if (validation()) {
                userviewmodel.updateUser(email,password,names,celphone,uid)
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
                Toast.makeText(this, "Actualizado Correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
            }
        }




        btnback.setOnClickListener{
            val intent = Intent(this, Profile::class.java )
            startActivity(intent)
            finish()
        }
    }

    private fun validation(): Boolean {
        val minLengthnames=6
        val minLengthpassword=8
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$"

        if (names.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo nombres vacio", Toast.LENGTH_LONG).show();
            return false
        } else if (names.text.length < minLengthnames) {
            Toast.makeText(this, "El nombre debe tener al menos $minLengthnames caracteres", Toast.LENGTH_LONG).show()
            return false
        }else if (celphone.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo celular vacio", Toast.LENGTH_LONG).show();
            return false
        } else if (!isValidPhoneNumber(celphone.text.toString())) {
            Toast.makeText(this, "Número de teléfono no válido", Toast.LENGTH_LONG).show()
            return false
        }else if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo email vacio", Toast.LENGTH_LONG).show();
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
            Toast.makeText(this, "Escribe un correo valido", Toast.LENGTH_LONG).show();
            return false
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo contraseña vacio", Toast.LENGTH_LONG).show();
            return false
        }else if (!password.text.toString().matches(passwordPattern.toRegex())) {
            Toast.makeText(this, "La contraseña debe contener al menos un dígito y un carácter", Toast.LENGTH_LONG).show()
            return false
        }else if (password.text.length < minLengthpassword) {
            Toast.makeText(this, "La contraseña debe tener al menos $minLengthpassword caracteres", Toast.LENGTH_LONG).show()
            return false
        }else {
            return true
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phonePattern = Regex("^[0-9]{10}$")
        return phonePattern.matches(phoneNumber)
    }
}