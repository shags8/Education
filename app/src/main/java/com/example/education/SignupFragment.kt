package com.example.education

import android.app.Activity
import android.content.Intent
import android.icu.text.Transliterator
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.education.databinding.ActivityAftersignupBinding
import com.example.education.databinding.ActivityLoginSignupBinding
import com.example.education.databinding.FragmentSignupBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignupFragment : Fragment() {

    private lateinit var binding : FragmentSignupBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): RelativeLayout {
        // Inflate the layout for this fragment
          binding = FragmentSignupBinding.inflate(layoutInflater)
          return binding.root
      //  return inflater.inflate(R.layout.fragment_signup, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            //binding.signup.isEnabled = false
           // checkdetails()
        binding.signup.setOnClickListener {
            var name = binding.name.text.toString()
            var email = binding.email.toString()
            var Age = binding.age.text.toString()
            var phone = binding.phonenumber.text.toString()
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (Integer.parseInt(Age) <= 100)
                {
                    val intent = Intent(activity,UserDetails::class.java)
                    intent.putExtra("name" ,name )
                    intent.putExtra("email" ,email )
                    intent.putExtra("Age" ,Age )
                    intent.putExtra("phone" ,phone )
                    startActivity(intent)
                    activity?.finish()
                }
                else{
                    Toast.makeText(activity,"Enter Correct Age", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(activity,"Enter Correct Mail", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun checkdetails()
    {
        var name = binding.name.text.toString()
        var email = binding.email.toString()
        var Age = binding.age.text.toString()
        var phone = binding.phonenumber.text.toString()

        if (name.isNotBlank()&&email.isNotBlank()&&Age.isNotBlank()&&phone.isNotBlank()){
            binding.signup.isEnabled = true
        }
        else{
            Toast.makeText(activity,"Fill All Out",Toast.LENGTH_LONG).show()
        }


    }


}