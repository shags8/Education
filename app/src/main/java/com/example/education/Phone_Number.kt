package com.example.education

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.education.databinding.ActivityAftersignupBinding
import com.example.education.databinding.ActivityPhoneNumberBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class Phone_Number : AppCompatActivity() {

    private lateinit var binding : ActivityPhoneNumberBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var OTP: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        binding.resendotp.visibility = View.INVISIBLE

        binding.getotp.setOnClickListener {
            var Number = binding.number.text.toString()
            binding.ccp.registerCarrierNumberEditText(binding.number)
            var phoneNumber = binding.ccp.fullNumber
            if (Number.length == 10) {
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber("+$phoneNumber")
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(this)
                    .setCallbacks(callbacks)
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
                binding.getotp.visibility = View.INVISIBLE
                binding.resendotp.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "ENTER CORRECT NUMBER", Toast.LENGTH_SHORT).show()
            }
        }
        binding.resendotp.setOnClickListener {
            binding.ccp.registerCarrierNumberEditText(binding.number)
            var phoneNumber = binding.ccp.fullNumber
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+$phoneNumber")
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

        binding.login.setOnClickListener{
           val otp =  binding.otp.text.toString()
            binding.ccp.registerCarrierNumberEditText(binding.number)
            var phoneNumber = binding.ccp.fullNumber
            if (otp.length == 6)
            {
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(OTP,otp)
                signInWithPhoneAuthCredential(credential)
            }
            else
            {
                Toast.makeText(this,phoneNumber,Toast.LENGTH_SHORT).show()
            }
        }
    }


    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            Log.d("TAG", "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w("TAG", "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d("TAG", "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            OTP = verificationId
            resendToken = token
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Sign in success",Toast.LENGTH_SHORT)
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    startActivity(Intent(this,Aftersignup::class.java))
                    val user = task.result?.user
                    finish()
                } else {
                    Toast.makeText(this,"Sign in failed",Toast.LENGTH_SHORT)
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this,"The verification code entered was invalid",Toast.LENGTH_SHORT)
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}