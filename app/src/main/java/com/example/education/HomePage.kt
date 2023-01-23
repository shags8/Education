package com.example.education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.education.databinding.ActivityAftersignupBinding
import com.example.education.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding : ActivityHomePageBinding
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toggle = ActionBarDrawerToggle(this , binding.Homepage , R.string.open , R.string.close)
        binding.Homepage.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.side.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show()
            }
            true

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}