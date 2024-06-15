package com.example.foodu.Login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.foodu.R
import com.example.foodu.modelclass.data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class createaccount : AppCompatActivity() {
    private lateinit var em: EditText
    private lateinit var ps: EditText
    private lateinit var cps: EditText
    private lateinit var signup: Button
    private lateinit var authh: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var dialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createaccount)

        em = findViewById(R.id.emailsigup)
        ps = findViewById(R.id.passsignup)
        cps = findViewById(R.id.confirpasssignup)
        signup = findViewById(R.id.appCompatButton6)
        signup.setOnClickListener {
            var emaail = em.text.toString()
            var passss = ps.text.toString()
            var confirmpass = cps.text.toString()
            if (emaail.isEmpty() || passss.isEmpty())  {
                Toast.makeText(this, "Enter Data", Toast.LENGTH_SHORT).show()
            } else if (!emaail.contains("@gmail.com")) {
                Toast.makeText(this, "Enter correct Email", Toast.LENGTH_SHORT).show()
            }else if(passss.length<7 && confirmpass.length<7){
                Toast.makeText(this, "Enter strong password", Toast.LENGTH_SHORT).show()
            }else if (!passss.equals(confirmpass)){
                Toast.makeText(this, "Password doesn't Matches", Toast.LENGTH_SHORT).show()
            }else{
                signup(emaail,passss)
            }
        }
    }
    private fun signup(emaail:String, passss:String){
        dialog=ProgressDialog(this)
        dialog.setTitle("Please wait")
        dialog.setMessage("Creating your Account!")
        dialog.show()
        authh=FirebaseAuth.getInstance()
        authh.createUserWithEmailAndPassword(emaail,passss).addOnCompleteListener(){
            if(it.isSuccessful){
                dialog.dismiss()
                val objj= Intent(this,login::class.java)
                startActivity(objj)
                Adddatatodatabase(emaail,passss)
                Toast.makeText(this,"Signup Complete", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener(){
            dialog.dismiss()

            Toast.makeText(this,"$it", Toast.LENGTH_SHORT).show()
        }
    }
    fun Adddatatodatabase(emaail: String, passss: String){
        database=FirebaseDatabase.getInstance()
        ref=database.getReference("Edify")
        val objj= data(emaail,passss,"12345678")
        var ID= ref.push().key.toString()
        ref.child("info").child(ID).setValue(objj).addOnCompleteListener {
            if (it.isSuccessful)
                Toast.makeText(this,"added", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"$it", Toast.LENGTH_SHORT).show()
        }
    }


    }
