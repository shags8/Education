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
import androidx.activity.result.contract.ActivityResultContracts
import com.example.education.databinding.ActivityAftersignupBinding
import com.example.education.databinding.ActivityLoginSignupBinding
import com.example.education.databinding.FragmentSignupBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class SignupFragment : Fragment() {

    private lateinit var binding : FragmentSignupBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googlesignin  : GoogleSignInClient

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

        auth= Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googlesignin = activity?.let { GoogleSignIn.getClient(it,gso) }!!


        binding.google.setOnClickListener {
            signInGoogle()
        }



            //binding.signup.isEnabled = false
           // checkdetails()
        binding.signup.setOnClickListener {
            var name = binding.name.text.toString()
            var email = binding.email.text.toString()
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
        var email = binding.email.text.toString()
        var Age = binding.age.text.toString()
        var phone = binding.phonenumber.text.toString()

        if (name.isNotBlank()&&email.isNotBlank()&&Age.isNotBlank()&&phone.isNotBlank()){
            binding.signup.isEnabled = true
        }
        else{
            Toast.makeText(activity,"Fill All Out",Toast.LENGTH_LONG).show()
        }


    }



    private fun signInGoogle(){
        val signInIntent = googlesignin.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(activity, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                FirebaseAuth.getInstance().currentUser
                val intent : Intent = Intent(activity , UserDetails::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(activity, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }
    }


}