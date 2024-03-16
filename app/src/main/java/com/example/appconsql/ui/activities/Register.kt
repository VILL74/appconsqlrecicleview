package com.example.appconsql.ui.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.appconsql.R
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import com.example.appconsql.data.model.UserModel
import com.example.appconsql.ui.viewmodels.UserViewModel


class Register : AppCompatActivity() {

    lateinit var back: Button
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var names: EditText
    lateinit var celphone: EditText
    lateinit var register: Button
    lateinit var loginpage: TextView

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        back = findViewById(R.id.btnback)
        email = findViewById(R.id.etemail)
        password = findViewById(R.id.etpassword)
        names = findViewById(R.id.etnames)
        celphone = findViewById(R.id.etcelphone)
        register = findViewById(R.id.btnregister)
        loginpage = findViewById(R.id.loginpage)




        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        loginpage.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        register.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            val names = names.text.toString()
            val celphone = celphone.text.toString()
            if (validation()) {
                val user= UserModel(null,email,password,names,celphone)
                userViewModel.insertUser(user)
                val intent = Intent(this, Login::class.java)
                startActivity(intent)


               /* saveUser(email,password,names,celphone)
                Toast.makeText(this, "Formulario enviado", Toast.LENGTH_LONG).show()
                saveCredentials(email, password)
                goToProfileActivity()*/
                /*
                userViewModel.saveUser(email,password,names,celphone)
                val intent= Intent(this,Login::class.java)
                startActivity(intent)*/



                /*val intent = Intent(this, Profile::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("names", names)
                intent.putExtra("celphone", celphone)
                startActivity(intent)*/

            } else {
                Toast.makeText(this, "Error: Formulario incompleto", Toast.LENGTH_LONG).show()
            }

        }


    }
    //validaciones
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
        // Puedes ajustar este patrón según tus necesidades específicas para validar números de teléfono.
        val phonePattern = Regex("^[0-9]{10}$")
        return phonePattern.matches(phoneNumber)
    }



    private fun saveCredentials(email: String, password: String) {
        val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    private fun goToProfileActivity() {
        val intent = Intent(this, Profile   ::class.java)
        startActivity(intent)
    }

}




