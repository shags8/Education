package com.example.education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.education.databinding.ActivityAftersignupBinding

class Aftersignup : AppCompatActivity() {

    private lateinit var binding : ActivityAftersignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAftersignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}