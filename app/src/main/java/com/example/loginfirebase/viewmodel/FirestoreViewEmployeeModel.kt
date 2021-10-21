package com.example.loginfirebase.viewmodel

import androidx.lifecycle.ViewModel
import com.example.loginfirebase.domain.FirestoreEmployeeStory
import com.example.loginfirebase.models.Employee

class FirestoreViewEmployeeModel: ViewModel() {
    private val firestoreEmployeeStory = FirestoreEmployeeStory()
    private val employee = Employee()

    fun createEmployee(employerEmail: String?, id: String, name: String, email: String, job: String, department: String) {
        if (employerEmail != null) {
            employee.employerEmail = employerEmail
            employee.id = id
            employee.name = name
            employee.email = email
            employee.job = job
            employee.department = department
            firestoreEmployeeStory.setEmployeeFirestore(employee)
        }
    }

    fun getAllEmployeesVM(){
        firestoreEmployeeStory.getAllEmployees()
    }
}