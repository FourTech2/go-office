package com.example.loginfirebase

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class EmployeeActivity : AppCompatActivity() {
    private lateinit var jobSpinner: Spinner
    private lateinit var departmentSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        //Establece los elementos de la vista
        setUp()
        //Carga al spinner con los elementos del string_array
        loadSpinnersData()
    }

    private fun setUp() {
        title = "Registrar empleados"
        jobSpinner = findViewById(R.id.jobSpinner)
        departmentSpinner = findViewById(R.id.departmentSpinner)
    }

    //Se obtienen los arrays de strings.xml
    private fun selectList(data: String): Array<out String> {
        return when (data){
            "jobs" -> resources.getStringArray(R.array.jobs_array)
            "departments" -> resources.getStringArray(R.array.departments_array)
            else -> emptyArray()
        }
    }

    //Se cargan los spinners con los arrays
    private fun confSpinnerAdapter(kindOfList: String): ArrayAdapter<String> {
        return ArrayAdapter(this, android.R.layout.simple_spinner_item, selectList(kindOfList))
    }

    //Carga al spinner con los elementos del string_array
    private fun loadSpinnersData() {
        jobSpinner.adapter = confSpinnerAdapter("jobs")
        departmentSpinner.adapter = confSpinnerAdapter("departments")
    }

    //Guardar empleado
    private fun saveEmployee (view: View) {
        //Obtener item seleccionado de jobSpinner -> jobSpinner.selectedItem.toString()
    }
}