package com.example.loginfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.loginfirebase.enums.SpaceState
import com.google.firebase.firestore.FirebaseFirestore

class SelectSpaceActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var imageButtons: Map<String, ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_space)
        setUp()
        db.collection("spaces").get().addOnSuccessListener { spaces ->
            for (space in spaces) {
                var image: Int

                when (SpaceState.valueOf(space.data["state"].toString())) {
                    SpaceState.Available -> {
                        image = R.drawable.free_space
                        imageButtons[space.id]?.setOnClickListener{selectFreeSpace(imageButtons[space.id])}
                    }
                    SpaceState.Busy -> {
                        image = R.drawable.block_space
                        imageButtons[space.id]?.setOnClickListener{selectOccupiedSpace(imageButtons[space.id])}
                    }
                    SpaceState.Reserved -> {
                        image = R.drawable.reserved_space
                        imageButtons[space.id]?.setOnClickListener{selectReservedSpace(imageButtons[space.id])}
                    }
                }

                imageButtons[space.id]?.setImageResource(image)
            }
        }
    }

    private fun setUp() {
        title = "Espacio a reservar"
        imageButtons = mutableMapOf(
            "01" to (findViewById(R.id.imageButtonSpace01)),
            "02" to (findViewById(R.id.imageButtonSpace02)),
            "03" to (findViewById(R.id.imageButtonSpace03)),
            "04" to (findViewById(R.id.imageButtonSpace04)),
            "05" to (findViewById(R.id.imageButtonSpace05)),
            "06" to (findViewById(R.id.imageButtonSpace06))
        )
    }

    private fun selectFreeSpace(space: ImageButton?) {
        space?.setImageResource(R.drawable.reserved_space)
    }

    private fun selectOccupiedSpace(space: ImageButton?){
        Toast.makeText(this, "Espacio ocupado", Toast.LENGTH_SHORT).show()
    }

    private fun selectReservedSpace(space: ImageButton?){
        Toast.makeText(this, "Espacio reservado", Toast.LENGTH_SHORT).show()
    }
}