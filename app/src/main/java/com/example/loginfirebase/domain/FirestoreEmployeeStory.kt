package com.example.loginfirebase.domain

import com.example.loginfirebase.data.repo.FirebaseEmployeeRepo
import com.example.loginfirebase.models.Employee

class FirestoreEmployeeStory {
    val repo = FirebaseEmployeeRepo()

    fun setEmployeeFirestore(employee: Employee){
        repo.setEmployeeData(employee)
    }

    fun getAllEmployees() {
        repo.getAllEmployeesData()
    }
}