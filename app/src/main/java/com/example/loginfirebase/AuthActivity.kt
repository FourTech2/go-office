package com.example.loginfirebase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example.loginfirebase.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
        session()
    }

    private fun setUp(){
        title = "Autenticación"
    }

    override fun onStart() {
        super.onStart()
        binding.authLayout.visibility = View.VISIBLE
    }

    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)

        if (email != null){
            binding.authLayout.visibility = View.INVISIBLE
            showHome(email)
        }
    }

    fun singUp(view: View){
        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "")
                    } else {
                        showAlert("Error", it.exception?.message ?: "")
                    }
                }
        }
    }
    fun logIn(view: View){
        if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "")
                    } else {
                        showAlert("Error", "Se ha producido un error autenticando el usuario")
                    }
                }
        }
        else{
            showAlert("Cuidado", "Ingrese su correo y contraseña")
        }
    }

    private fun showAlert(title: String, message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}