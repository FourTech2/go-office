package com.example.loginfirebase.data.repo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository(private var application: Application) {
    private val auth = FirebaseAuth.getInstance()
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private var userLoggedMutableLiveData = MutableLiveData<Boolean>()

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser>{
        return userMutableLiveData
    }

    fun getUserLoggedMutableLiveData(): MutableLiveData<Boolean>{
        return userLoggedMutableLiveData
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                userMutableLiveData.postValue(auth.currentUser)
            } else {
                Toast.makeText(application, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                userMutableLiveData.postValue(auth.currentUser)
            } else {
                Toast.makeText(application, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun singOut(){
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }
}