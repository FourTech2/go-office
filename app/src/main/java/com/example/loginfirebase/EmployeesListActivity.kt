package com.example.loginfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginfirebase.models.Employee
import com.example.loginfirebase.viewmodel.FirestoreViewEmployeeModel

class EmployeesListActivity : AppCompatActivity() {
    private lateinit var adapter: MainAdapter
    private val viewModel by lazy { ViewModelProvider(this)[FirestoreViewEmployeeModel::class.java] }

    private lateinit var employeesListRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_list)

        //Establece los elementos de la vista
        setUp(intent.extras?.getString("email"))

        adapter = MainAdapter(this)
        employeesListRV = findViewById(R.id.employeesListRV)
        employeesListRV.layoutManager = LinearLayoutManager(this)
        employeesListRV.adapter = adapter
        observeData()
    }

    private fun setUp(email: String?) {
        title = "Lista de empleados"
//        userLogEmailTextView = findViewById(R.id.userLogEmailTextView)
//
//        val prayer = StringBuilder()
//        prayer.append(getString(R.string.user_log_text)).append(" ").append(email)
//        userLogEmailTextView.text = prayer.toString()
    }

    fun observeData() {
        viewModel.fetchEmployeesData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}