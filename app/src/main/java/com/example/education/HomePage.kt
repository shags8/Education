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
import com.google.firebase.database.*

class HomePage : AppCompatActivity() {

    private lateinit var binding : ActivityHomePageBinding
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var courseview : RecyclerView
    lateinit var educatorview : RecyclerView
    private lateinit var database : DatabaseReference
    private lateinit var  courselist : ArrayList<dataclassCourse>
    private lateinit var  educatorlist : ArrayList<dataclasseducators>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val toolbar = binding.toolbar
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


        courseview = binding.recyclecourses
        courseview.layoutManager = LinearLayoutManager(this)
        courselist = arrayListOf()
        educatorview = binding.reycleeducators
        educatorview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        educatorlist = arrayListOf()
        readData()





    }

    private fun readData() {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(dataclassCourse::class.java)
                        if (user != null) {
                            courselist.add(user)
                        }


                    }
                    courseview.adapter = ADAPTER(courselist)
                }
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(dataclasseducators::class.java)
                        if (user != null) {
                            educatorlist.add(user)
                        }


                    }
                    educatorview.adapter = ADAPTER_educcators(educatorlist)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}