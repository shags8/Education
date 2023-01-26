package com.example.education

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.education.databinding.FragmentLoginBinding
import com.example.education.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      binding.login.setOnClickListener {
            var Email = binding.email.text.toString()
            var passcode = binding.passcode.text.toString()
        if (Email.isNotBlank()&&passcode.isNotBlank()){

            FirebaseAuth.getInstance().signInWithEmailAndPassword(Email,passcode).addOnCompleteListener {
                if (it.isSuccessful){
                    val intent = Intent(activity,HomePage::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                else{
                    Toast.makeText(activity, "Signin Failed" , Toast.LENGTH_SHORT).show()
                }
            }
        }
        }

    }



}