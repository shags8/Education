package com.example.education

import android.content.Intent
import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.education.databinding.ActivityAftersignupBinding
import com.example.education.databinding.ActivityUserDetailsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserDetails : AppCompatActivity() {

    private lateinit var binding : ActivityUserDetailsBinding
    private lateinit var database : DatabaseReference
    var personNames = arrayOf("Choose Education","Pre-Primary", "Primary", "Vocational Courses", "Mentorship")
    var Position : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupSpinner()
        binding.button.isEnabled = false
       binding.button.setOnClickListener {
            var passcode = binding.passcode.text.toString()
            var Username = binding.UserName.text.toString()
            var repasscode = binding.repasscode.text.toString()
            var name = intent.getStringExtra("name")
            var email = intent.getStringExtra("email")
            var age = intent.getStringExtra("Age")
            var phoneNumber = intent.getStringExtra("phone")
            database = FirebaseDatabase.getInstance().getReference("Users")
           val User = DataClassProfile(name, email, age, phoneNumber, Username , passcode , Position )
            database.child(Username).setValue(User).addOnSuccessListener {
                val intent = Intent(this@UserDetails,HomePage::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@UserDetails,"ERROR",Toast.LENGTH_SHORT)
            }
        }

    }

    private fun setupSpinner() {

        var passcode = binding.passcode.text
        var Username = binding.UserName.text
        var repascode = binding.repasscode.text
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
                Position = parent.getItemAtPosition(position).toString()
                var selecteditem = parent.getItemAtPosition(position)
                if (passcode.isNotBlank()&&Username.isNotBlank()&&repascode.isNotBlank()){
                    binding.button.isEnabled = parent.getItemAtPosition(0) != selecteditem
                }
                else{
                    Toast.makeText(this@UserDetails,"Fill All Out", Toast.LENGTH_LONG).show()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(this@UserDetails,"need to select something", Toast.LENGTH_SHORT).show()
            }
        }

    }

}