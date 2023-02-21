package com.example.education

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.education.databinding.FragmentHomeTabBinding
import com.example.education.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeTab : Fragment() {
    private lateinit var binding : FragmentHomeTabBinding
    lateinit var courseview : RecyclerView
    lateinit var educatorview : RecyclerView
    private lateinit var database : DatabaseReference
    private lateinit var  courselist : ArrayList<dataclassCourse>
    private lateinit var  educatorlist : ArrayList<dataclasseducators>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeTabBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseview = binding.recyclecourses
        courseview.layoutManager = LinearLayoutManager(context)
        courselist = arrayListOf()
        educatorview = binding.reycleeducators
        educatorview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        educatorlist = arrayListOf()
        readData()


    }


    private fun readData() {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.addValueEventListener(object : ValueEventListener {
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

}