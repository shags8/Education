package com.example.education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.education.databinding.ActivityAftersignupBinding

class Aftersignup : AppCompatActivity() {

    private lateinit var binding : ActivityAftersignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAftersignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var name = binding.Name.text.toString()
        var Username = binding.UserName.text.toString()
        var PhNo = binding.PhNo.text.toString()
        var Otp = binding.Otp.text.toString()
        var Age = binding.Age.text.toString()
        setupSpinner()
    }

    private fun setupSpinner() {
        val personNames = arrayOf("Rahul", "Jack", "Rajeev", "Aryan", "Rashmi", "Jaspreet", "Akbar")
        val spinner = binding.spinner
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(this@Aftersignup,"need to select something",Toast.LENGTH_SHORT)
            }
        }

    }
}