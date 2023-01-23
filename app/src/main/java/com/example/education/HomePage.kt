package com.example.education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.education.databinding.ActivityAftersignupBinding
import com.example.education.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding : ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}