package com.example.loginfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.loginfirebase.viewmodel.FirestoreViewEmployeeModel

class EmployeesListActivity : AppCompatActivity() {
    private lateinit var viewModel: FirestoreViewEmployeeModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_list)

        viewModel = ViewModelProvider(this)[FirestoreViewEmployeeModel::class.java]

        //Establece los elementos de la vista
        setUp(intent.extras?.getString("email"))

        //Se listan los empleados
        //getEmployeesList()
    }

    private fun setUp(email: String?) {
        title = "Lista de empleados"
//        userLogEmailTextView = findViewById(R.id.userLogEmailTextView)
//
//        val prayer = StringBuilder()
//        prayer.append(getString(R.string.user_log_text)).append(" ").append(email)
//        userLogEmailTextView.text = prayer.toString()
    }

    private fun getEmployeesList() {
        viewModel.getAllEmployeesVM()
    }
}