package com.example.education

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.education.databinding.ActivityAftersignupBinding
import com.google.android.material.navigationrail.NavigationRailMenuView

class Aftersignup : AppCompatActivity() {

    private lateinit var binding : ActivityAftersignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAftersignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var name = binding.Name.text
        var Username = binding.UserName.text
        var PhNo = binding.PhNo.text
        var Otp = binding.Otp.text
        var Age = binding.Age.text
        setupSpinner()
        binding.button.isEnabled = false
        binding.button.setOnClickListener {
            checkdetails()
        }
    }

    private fun setupSpinner() {

        var name = binding.Name.text
        var Username = binding.UserName.text
        var PhNo = binding.PhNo.text
        var Otp = binding.Otp.text
        var Age = binding.Age.text
        val personNames = arrayOf("Choose Education","Pre-Primary", "Primary", "Vocational Courses", "Mentorship")
        val spinner = binding.spinner
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, personNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                var selecteditem = parent.getItemAtPosition(position)
                if (name.isNotBlank()&&Username.isNotBlank()&&PhNo.isNotBlank()&&Otp.isNotBlank()&&Age.isNotBlank()){
                    binding.button.isEnabled = parent.getItemAtPosition(0) != selecteditem
                }
                else{
                    Toast.makeText(this@Aftersignup,"Fill All Out",Toast.LENGTH_LONG).show()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(this@Aftersignup,"need to select something",Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun checkdetails()
    {
        var name = binding.Name.text
        var Username = binding.UserName.text
        var PhNo = binding.PhNo.text
        var Otp = binding.Otp.text
        var Age = binding.Age.text
            if (PhNo.toString().length == 10)
            {
                if (Integer.parseInt(Age.toString()) <= 100){
                    if (Otp.toString().length==6){
                        val intent = Intent(this@Aftersignup,MainActivity::class.java)
                        startActivity(intent)

                    }
                    else{
                        Toast.makeText(this@Aftersignup,"Enter Correct Otp",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this@Aftersignup,"Enter Correct Age",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this@Aftersignup,"Enter Correct Number",Toast.LENGTH_SHORT).show()
            }
    }
}