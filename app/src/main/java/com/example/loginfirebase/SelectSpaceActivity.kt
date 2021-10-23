package com.example.loginfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import com.example.loginfirebase.databinding.ActivitySelectSpaceBinding
import com.example.loginfirebase.enums.SpaceState
import com.example.loginfirebase.models.Space
import com.example.loginfirebase.viewmodel.SpaceViewModel

class SelectSpaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectSpaceBinding
    private val spaceViewModel: SpaceViewModel by viewModels()
    private lateinit var imageButtons: Map<String, ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSpaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
        spaceViewModel.getSpaceList()
        spaceViewModel.spaceList.observe(this, {
            loadSpaces(it)
        })
    }

    private fun setUp() {
        title = getString(R.string.space_to_reserve)
        imageButtons = mutableMapOf(
            "01" to (binding.imageButtonSpace01),
            "02" to (binding.imageButtonSpace02),
            "03" to (binding.imageButtonSpace03),
            "04" to (binding.imageButtonSpace04),
            "05" to (binding.imageButtonSpace05),
            "06" to (binding.imageButtonSpace06)
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

    private fun loadSpaces(spaceList: MutableList<Space>){
        for (space in spaceList){
            var image: Int

            when (space.state) {
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