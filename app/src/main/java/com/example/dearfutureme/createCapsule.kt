package com.example.dearfutureme

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dearfutureme.databinding.ActivityCreateCapsuleBinding
import java.util.Calendar

class createCapsule : AppCompatActivity() {

    lateinit var binding: ActivityCreateCapsuleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCapsuleBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this@createCapsule, MyCapsuleList::class.java))
        }

        val editTextDate = binding.dateFormat
        editTextDate.setOnClickListener {
            // Get current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Show DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    // Format date as dd/MM/yyyy
                    val selectedDate = "${monthOfYear + 1}/$dayOfMonth/$year"
                    editTextDate.setText(selectedDate)  // Set date to EditText
                },
                year, month, day
            )
            datePickerDialog.show()
        }

    }
}