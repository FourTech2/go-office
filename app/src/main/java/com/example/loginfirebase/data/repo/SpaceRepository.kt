package com.example.loginfirebase.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.loginfirebase.enums.SpaceState
import com.example.loginfirebase.models.Space
import com.google.firebase.firestore.FirebaseFirestore

class SpaceRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collectionName = "spaces"

    fun getAll(): MutableLiveData<MutableList<Space>>{
        val mutableLiveData = MutableLiveData<MutableList<Space>>()

        db.collection(collectionName).get().addOnSuccessListener { data ->
            val spaceList = mutableListOf<Space>()

            for (document in data){
                val spaceState = SpaceState.valueOf(document.data["state"].toString())
                val spaceUserName = document.data["userName"].toString()
                val space = Space(document.id, spaceState, spaceUserName)
                spaceList.add(space)
            }

            mutableLiveData.value = spaceList
        }

        return mutableLiveData
    }
}