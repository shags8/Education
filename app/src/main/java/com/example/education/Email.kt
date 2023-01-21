package com.example.education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.education.databinding.ActivityEmailBinding
import com.example.education.databinding.ActivityMainBinding

class Email : AppCompatActivity() {
    lateinit private var binding : ActivityEmailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}