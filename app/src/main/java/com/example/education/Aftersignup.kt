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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Aftersignup : AppCompatActivity() {

    private lateinit var binding : ActivityAftersignupBinding
    private lateinit var database : DatabaseReference
    var personNames = arrayOf("Choose Education","Pre-Primary", "Primary", "Vocational Courses", "Mentorship")
    var Position : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAftersignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupSpinner()
        binding.button.isEnabled = false
        binding.button.setOnClickListener {
            checkdetails()
        }
    }

    private fun setupSpinner() {

        var name = binding.Name.text
        var Username = binding.UserName.text
        var Age = binding.Age.text
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
                if (name.isNotBlank()&&Username.isNotBlank()&&Age.isNotBlank()){
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
        var name = binding.Name.text.toString()
        var Username = binding.UserName.text.toString()
        var Age = binding.Age.text.toString()



                if (Integer.parseInt(Age) <= 100)
                {
                        database = FirebaseDatabase.getInstance().getReference("Users")
                        val User = DataClassProfile(Username,name,Age.toString(),Position)
                        database.child(Username).setValue(User).addOnSuccessListener {
                            val intent = Intent(this@Aftersignup,MainActivity::class.java)
                            startActivity(intent)
                        }.addOnFailureListener {
                            Toast.makeText(this@Aftersignup,"ERROR",Toast.LENGTH_SHORT)
                        }
                }
                else{
                    Toast.makeText(this@Aftersignup,"Enter Correct Age",Toast.LENGTH_SHORT).show()
                }


    }
}