package com.example.loginfirebase.data.repo

import android.widget.Toast
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
}