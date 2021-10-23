package com.example.loginfirebase

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.example.loginfirebase.viewmodel.FirestoreViewEmployeeModel
import android.widget.Spinner

import android.widget.TextView


class EmployeeActivity : AppCompatActivity() {
    private lateinit var viewModel: FirestoreViewEmployeeModel

    private lateinit var userLogEmailTextView: TextView
    private lateinit var idEmpEditText: EditText
    private lateinit var nameEmpEditText: EditText
    private lateinit var emailEmpEditText: EditText
    private lateinit var jobSpinner: Spinner
    private lateinit var departmentSpinner: Spinner
    private var validation = AwesomeValidation(ValidationStyle.BASIC)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        viewModel = ViewModelProvider(this)[FirestoreViewEmployeeModel::class.java]

        //Establece los elementos de la vista
        setUp(intent.extras?.getString("email"))
        //Carga al spinner con los elementos del string_array
        loadSpinnersData()
        formValidation()
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

    //Validaciones
    private fun formValidation() {
        //"^.*@*.\\\\..*\$"
        validation.addValidation(this, R.id.idEmpEditText,
            "^[1-9]+\\s\\d\\d\\d\\d\\s\\d\\d\\d\\d\$", R.string.invalid_id)
        validation.addValidation(this, R.id.nameEmpEditText,
            RegexTemplate.NOT_EMPTY, R.string.invalid_name)
        validation.addValidation(this, R.id.emailEmpEditText,
            "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$", R.string.invalid_email)
        validateSpinner(R.id.jobSpinner, R.string.invalid_job_spinner)
        validateSpinner(R.id.departmentSpinner, R.string.invalid_department_spinner)
    }

    //Validación de spinners
    private fun validateSpinner(formSpinner: Int, invalidSpinner: Int) {
        validation.addValidation(this, formSpinner,
            { validationHolder ->
                (validationHolder.view as Spinner).selectedItemPosition != 0
            },
            { validationHolder ->
                val textViewError = (validationHolder.view as Spinner).selectedView as TextView
                textViewError.error = validationHolder.errMsg
                textViewError.setTextColor(Color.RED)
            }, { validationHolder ->
                val textViewError = (validationHolder.view as Spinner).selectedView as TextView
                textViewError.error = null
                textViewError.setTextColor(Color.BLACK)
            }, invalidSpinner
        )
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
    private fun confSpinnerAdapter(kindOfList: String): ArrayAdapter<Any?> {
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_style_employee, selectList(kindOfList))
        arrayAdapter.setDropDownViewResource(R.layout.spinner_style_employee)
        return arrayAdapter
    }

    //Carga al spinner con los elementos del string_array y lo configura
    private fun loadSpinnersData() {
        jobSpinner.adapter = confSpinnerAdapter("jobs")
        departmentSpinner.adapter = confSpinnerAdapter("departments")
    }



    //Guardar empleado
    fun saveEmployee (view: View) {
        if(validation.validate()) {

//            viewModel.createEmployee(
//                intent.extras?.getString("email"),
//                idEmpEditText.text.toString(),
//                nameEmpEditText.text.toString(),
//                emailEmpEditText.text.toString(),
//                jobSpinner.selectedItem.toString(),
//                departmentSpinner.selectedItem.toString()
//            )
            Toast.makeText(applicationContext,"GUARDADO",Toast.LENGTH_LONG).show()
        } else {

            Toast.makeText(applicationContext,"NO GUARDA",Toast.LENGTH_LONG).show()
        }
    }

    //Llama a la vista lista de empleados y envía el email del usuario logueado
    fun showListEmployees(view: View) {
        val employeeListIntent = Intent(this, EmployeesListActivity::class.java).apply {
            putExtra("email", intent.extras?.getString("email"))
        }
        startActivity(employeeListIntent)
    }
}