package com.example.education

import android.graphics.Insets.add
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.Fragment
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
            it.isChecked = true
            when(it.itemId){
                R.id.home -> replacefragment(HomeTab(),it.title.toString())
            }
            true

        }



    }

    private fun replacefragment(fragment : Fragment , title: String) {
        val fragmentmanager = supportFragmentManager
        val fragmenttransaction = fragmentmanager.beginTransaction()
        fragmenttransaction.replace(R.id.frame,fragment)
        fragmenttransaction.commit()
        binding.Homepage.closeDrawers()
        setTitle(title)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}