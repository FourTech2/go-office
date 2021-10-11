package com.example.loginfirebase

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EmployeeActivity : AppCompatActivity() {
    private lateinit var userLogEmailTextView: TextView
    private lateinit var idEmpEditText: EditText
    private lateinit var nameEmpEditText: EditText
    private lateinit var emailEmpEditText: EditText
    private lateinit var jobSpinner: Spinner
    private lateinit var departmentSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        //Establece los elementos de la vista
        setUp(intent.extras?.getString("email"))
        //Carga al spinner con los elementos del string_array
        loadSpinnersData()
    }

    private fun setUp(email: String?) {
        title = "Registrar empleados"
        userLogEmailTextView = findViewById(R.id.userLogEmailTextView)

        val prayer = StringBuilder()
        prayer.append(getString(R.string.user_log_text)).append(" ").append(email)
        userLogEmailTextView.text = prayer.toString()

        idEmpEditText = findViewById(R.id.idEmpEditText)
        nameEmpEditText = findViewById(R.id.nameEmpEditText)
        emailEmpEditText = findViewById(R.id.emailEmpEditText)
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