package com.example.education

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.OneShotPreDrawListener.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.education.databinding.ActivityHomePageBinding
import com.google.android.gms.common.util.WorkSourceUtil.add
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomePage : AppCompatActivity() {

    private lateinit var binding : ActivityHomePageBinding
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var courseview : RecyclerView
    lateinit var adapter : ADAPTER
    private lateinit var database : DatabaseReference
    private lateinit var  courselist : ArrayList<dataclassCourse>
    lateinit var courses : Array<String>
    lateinit var authors : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toolbar = binding.toolbar
        val collapasable = binding.collapsing
        setSupportActionBar(toolbar)

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

        courses = arrayOf("ssdda","dsdsa","sdadsad","sdaddassda")
        authors = arrayOf("safa","fasfs","sfasffsa","safsasafa")
        courselist = arrayListOf<dataclassCourse>()
        for(i in courses.indices){
            val assa = dataclassCourse(courses[i],authors[i])
            courselist.add(assa)
        }



        courseview = binding.recyclecourses
        courseview.layoutManager = LinearLayoutManager(this)
        courseview.adapter = ADAPTER(courselist)




    }

    private fun readData() {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.get().addOnSuccessListener {
            val Name = it.child("course").value
            val Passcode = it.child("passcode").value
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}