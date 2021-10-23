package com.example.loginfirebase.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.loginfirebase.models.Employee
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseEmployeeRepo {
    private val db = FirebaseFirestore.getInstance()

    fun setEmployeeData(employee: Employee) {
        db.collection("employees")
            .add(employee).addOnCompleteListener {
                if(it.isSuccessful) {
                    //mensaje al usuario
                } else {
                    //no se pudo guardar
                }
            }
    }

    fun getAllEmployeesData(): LiveData<MutableList<Employee>> {
        val employeesList = MutableLiveData<MutableList<Employee>>()

        db.collection("employees").get().addOnSuccessListener { result->
            val listData = mutableListOf<Employee>()
            for(document in result) {
                val employee = document.toObject(Employee::class.java)
                listData.add(employee)
            }
            employeesList.value = listData
        }
        return employeesList
    }
}