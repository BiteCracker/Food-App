package com.example.foodu.Login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.foodu.R
import com.google.firebase.auth.FirebaseAuth

class loginaccount : AppCompatActivity() {
    private lateinit var eml: EditText
    private lateinit var pass: EditText
    private lateinit var btn: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginaccount)

        eml = findViewById(R.id.emailadd)
        pass = findViewById(R.id.passswordd)
        btn = findViewById(R.id.appCompatButton6)
        btn.setOnClickListener {
            var email = eml.text.toString()
            var password = pass.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter Data", Toast.LENGTH_SHORT).show()
            } else if (!email.contains("@gmail.com")) {
                Toast.makeText(this, "Enter correct Email", Toast.LENGTH_SHORT).show()

            } else if (password.length < 7) {
                Toast.makeText(this, "Enter strong passsword", Toast.LENGTH_SHORT).show()

            } else {
                Login(email, password)
            }
        }
    }
    private fun Login(useremail: String, userpass: String) {
        dialog=ProgressDialog(this)
        dialog.setTitle("Please wait")
        dialog.setMessage("Signing you in!")
        dialog.show()
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(useremail, userpass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dialog.dismiss()
                val obj = Intent(this, login::class.java)
                startActivity(obj)
                Toast.makeText(this, "Welcome back user", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }

    }


    }
