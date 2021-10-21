package com.example.loginfirebase.data.repo

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

    fun getAllEmployeesData() {
        val employeesList = mutableListOf<Employee>()

        db.collection("employees").get().addOnSuccessListener { result->
            for(document in result) {
                val employee = document.toObject(Employee::class.java)
                employeesList.add(employee)
            }
        }
    }
}