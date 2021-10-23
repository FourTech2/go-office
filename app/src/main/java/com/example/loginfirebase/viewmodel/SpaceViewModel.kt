package com.example.loginfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginfirebase.data.repo.SpaceRepository
import com.example.loginfirebase.models.Space

class SpaceViewModel: ViewModel() {
    private val spaceRepository = SpaceRepository()
    var spaceList = MutableLiveData<MutableList<Space>>()

    fun getSpaceList() {
        spaceList = spaceRepository.getAll()
    }
}