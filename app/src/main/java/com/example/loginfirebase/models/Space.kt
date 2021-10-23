package com.example.loginfirebase.models

import com.example.loginfirebase.enums.SpaceState

data class Space(var id: String, var state: SpaceState, var userName: String) {
}