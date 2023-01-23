package com.example.education

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.education.databinding.ActivityEmailBinding
import com.example.education.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Email : AppCompatActivity() {
    lateinit private var binding : ActivityEmailBinding

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        binding.signup.setOnClickListener {
            var email = binding.email.text.toString()
            var password = binding.passcode.text.toString()
            var repassword = binding.repasscode.text.toString()

            if (email.isNotEmpty()&&password.isNotEmpty()&&repassword.isNotEmpty()){
                if (password == repassword){
                    CreateUser(email,password)
                }
                else{
                    Toast.makeText(this ,"password doesnot match",Toast.LENGTH_SHORT)
                }
            }
            else{
                Toast.makeText(this ,"Enter all details",Toast.LENGTH_SHORT)
            }
        }
    }

    private fun CreateUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this , Aftersignup::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, Aftersignup::class.java))
            finish()
        }
    }
}