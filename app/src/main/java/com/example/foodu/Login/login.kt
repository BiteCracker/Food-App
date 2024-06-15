package com.example.foodu.Login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.foodu.R

class login : AppCompatActivity() {
    private lateinit var signup:Button
    private lateinit var sigin:Button
    private lateinit var siguptxt:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signup=findViewById(R.id.appCompatButton3)
        sigin=findViewById(R.id.appCompatButton2)
        siguptxt=findViewById(R.id.signupid)
        signup.setOnClickListener {
            val obj=Intent(this,createaccount::class.java)
            startActivity(obj)
            Toast.makeText(this,"start creating you account!",Toast.LENGTH_SHORT)
        }
        sigin.setOnClickListener {
            val intent=Intent(this,loginaccount::class.java)
            startActivity(intent)
        }
        siguptxt.setOnClickListener {
            val objj=Intent(this,createaccount::class.java)
            startActivity(objj)
        }
    }
}