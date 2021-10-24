package com.example.loginfirebase.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.loginfirebase.EmployeeActivity
import com.example.loginfirebase.R
import com.example.loginfirebase.databinding.FragmentHomeBinding
import com.example.loginfirebase.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var authViewModel: AuthenticationViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        authViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun singOut() {
        val prefs = this.activity?.getSharedPreferences(getString(R.string.prefs_file),
            Context.MODE_PRIVATE)?.edit()
        prefs?.clear()
        prefs?.apply()
        authViewModel.singOut()
        this.activity?.onBackPressed()
    }

    private fun setUp() {
        val bundle = this.activity?.intent?.extras
        val email = bundle?.getString("email")

        if (email != null) {
            binding.currentEmailTextView.text = email
            binding.employeeButton.setOnClickListener { showEmployees(email) }
        }

        binding.logOutButton.setOnClickListener { singOut() }
    }

    //Llama a la vista de empleados y envía el email del usuario logueado
    private fun showEmployees(email: String) {
        val employeeIntent = Intent(activity, EmployeeActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(employeeIntent)
    }
}