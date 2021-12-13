package com.example.instagramkotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.instagramkotlin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var email : String
    private lateinit var password : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser!=null){
            val intent = Intent(this@MainActivity, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun signInClicked(view: View) {

        email = binding.textEmail.text.toString()
        password = binding.textPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    val intent = Intent(this@MainActivity, FeedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {  }
        }else{
            Toast.makeText(this@MainActivity,"Giriş bilgileri hatalı",Toast.LENGTH_SHORT).show()
        }

    }

    fun signUpClicked(view: View) {

        email = binding.textEmail.text.toString()
        password = binding.textPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { //success
                    val intent = Intent(this@MainActivity, FeedActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
                }

        }else{
           Toast.makeText(this@MainActivity,"Lütfen email ve şifre giriniz",Toast.LENGTH_SHORT).show()
        }

    }
}