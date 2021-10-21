package com.example.loginfirebase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginfirebase.data.repo.FirebaseEmployeeRepo
import com.example.loginfirebase.models.Employee

class FirestoreViewEmployeeModel: ViewModel() {
    //private val firestoreEmployeeStory = FirestoreEmployeeStory()
    private val repo = FirebaseEmployeeRepo()
    private val employee = Employee()

    fun createEmployee(employerEmail: String?, id: String, name: String, email: String, job: String, department: String) {
        if (employerEmail != null) {
            employee.employerEmail = employerEmail
            employee.id = id
            employee.name = name
            employee.email = email
            employee.job = job
            employee.department = department
            repo.setEmployeeData(employee)
        }
    }

    fun fetchEmployeesData(): LiveData<MutableList<Employee>>{
        val mutableData = MutableLiveData<MutableList<Employee>>()
        repo.getAllEmployeesData().observeForever { employeeList ->
            mutableData.value = employeeList
        }
        return mutableData
    }
}