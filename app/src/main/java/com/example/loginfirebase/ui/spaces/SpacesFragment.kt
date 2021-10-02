package com.example.loginfirebase.ui.spaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBindings
import com.example.loginfirebase.DatePickerFragment
import com.example.loginfirebase.TimePickerFragment
import com.example.loginfirebase.databinding.FragmentSpacesBinding

class SpacesFragment : Fragment() {

    private lateinit var spacesViewModel: SpacesViewModel
    private var _binding: FragmentSpacesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        spacesViewModel =
            ViewModelProvider(this).get(SpacesViewModel::class.java)

        _binding = FragmentSpacesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUp()
        return root
    }

    private fun setUp() {
        binding.editTextDate.setOnClickListener { showDatePickerDialog() }
        binding.editTextStartTime.setOnClickListener { showTimePickerDialog() }
        binding.editTextFinalTime.setOnClickListener { showTimePickerDialog() }
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { time -> onTimeSelected(time) }
        activity?.let { timePicker.show(it.supportFragmentManager, "Time") }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        activity?.let { datePicker.show(it.supportFragmentManager, "DatePicker") }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val realMonth = month + 1
        val dateText = if (realMonth < 10) {
            "$day-0$realMonth-$year"
        } else {
            "$day-$realMonth-$year"
        }
        binding.editTextDate.setText(dateText)
    }

    private fun onTimeSelected(time: String) {
        binding.editTextStartTime.setText(time)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}