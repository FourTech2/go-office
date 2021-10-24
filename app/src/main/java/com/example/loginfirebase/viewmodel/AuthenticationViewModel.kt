package com.example.loginfirebase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.loginfirebase.data.repo.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AuthenticationRepository(application)
    private var userMutableLiveData = repository.getUserMutableLiveData()
    private var userLoggedMutableLiveData = repository.getUserLoggedMutableLiveData()

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

    fun getUserLoggedMutableLiveData(): MutableLiveData<Boolean> {
        return userLoggedMutableLiveData
    }

    fun login(email: String, password: String) {
        repository.login(email, password)
    }

    fun register(email: String, password: String){
        repository.register(email, password)
    }

    fun singOut(){
        repository.singOut()
    }
}