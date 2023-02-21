package com.example.education

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.education.databinding.ActivityUserDetailsBinding
import com.example.education.databinding.ActivityUserdetailsgoogleBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Userdetailsgoogle : AppCompatActivity() {

    private lateinit var binding : ActivityUserdetailsgoogleBinding
    private lateinit var database : DatabaseReference
    var personNames = arrayOf("Choose Education","Pre-Primary", "Primary", "Vocational Courses", "Mentorship")
    var Position : String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserdetailsgoogleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupSpinner()
        binding.button.isEnabled = false
        binding.button.setOnClickListener {

            var passcode = binding.passcode.text.toString()
            var Username = binding.UserName.text.toString()
            var repasscode = binding.repasscode.text.toString()
            var name = binding.Name.text.toString()
            var email = binding.email.text.toString()
            var Age = binding.Age.text.toString()
            var phone = binding.phonenumber.text.toString()

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (Integer.parseInt(Age) <= 100)
                {
                    if (passcode == repasscode){
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.toString(),passcode)
                        val userid = FirebaseAuth.getInstance().currentUser?.uid.toString()
                        database = FirebaseDatabase.getInstance().getReference("Users")
                        val User = DataClassProfile(name, email, Age, phone, Username , passcode , Position ,userid )
                        database.child(Username).setValue(User).addOnSuccessListener {
                            val intent = Intent(this,HomePage::class.java)
                            startActivity(intent)
                            finish()
                        }.addOnFailureListener {
                            Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this,"Password doesn't match",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"Enter Correct Age", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Enter Correct Mail", Toast.LENGTH_SHORT).show()
            }



        }
    }
    private fun setupSpinner() {

        var passcode = binding.passcode.text
        var Username = binding.UserName.text
        var repascode = binding.repasscode.text
        val spinner = binding.spinner
        val arrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, personNames)
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
                    Toast.makeText(this@Userdetailsgoogle,"Fill All Out", Toast.LENGTH_LONG).show()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(this@Userdetailsgoogle,"need to select something", Toast.LENGTH_SHORT).show()
            }
        }

    }
}