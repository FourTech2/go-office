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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginfirebase.data.repo.AuthenticationRepository
import com.example.loginfirebase.databinding.ActivityAuthBinding
import com.example.loginfirebase.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
        session()
    }

    private fun setUp() {
        title = "Autenticación"
        authViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[AuthenticationViewModel::class.java]
        authViewModel.getUserMutableLiveData().observe(this, { firebaseUser ->
            if (firebaseUser != null) {
                showHome(firebaseUser.email.toString())
            }
        })
        binding.logInButton.setOnClickListener { logIn() }
        binding.singUpButton.setOnClickListener { singUp() }
    }

    override fun onStart() {
        super.onStart()
        binding.authLayout.visibility = View.VISIBLE
    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)

        if (email != null) {
            binding.authLayout.visibility = View.INVISIBLE
            showHome(email)
        }
    }

    fun singUp() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            authViewModel.register(email, password)
        } else {
            showAlert("¡Cuidado!", "Ingrese su correo electrónico y contraseña")
        }
    }

    private fun logIn() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            authViewModel.login(email, password)
        } else {
            showAlert("¡Cuidado!", "Ingrese su correo electrónico y contraseña")
        }
    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}