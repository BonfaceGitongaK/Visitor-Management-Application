package com.example.bonnie.visitormanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var userDatabaseReference: DatabaseReference

    var mAuth = FirebaseAuth.getInstance()
    lateinit var btnLogin : Button
    lateinit var loginEmailInput : EditText
    lateinit var loginPasswordInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        userDatabaseReference = FirebaseDatabase.getInstance().getReference("users")

        btnLogin = findViewById<Button>(R.id.btnLogin)
        loginEmailInput = findViewById<EditText>(R.id.loginEmailInput)
        loginPasswordInput = findViewById<EditText>(R.id.loginPasswordInput)



        btnLogin.setOnClickListener {
            loginAdmin()
        }

        setUpUI()
    }

    private fun setUpUI() {
        btnLogin.setOnClickListener {

            val intent = Intent(applicationContext, AdminHomeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loginAdmin() {

        val email = loginEmailInput.text.toString()
        val password = loginPasswordInput.text.toString()


        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "please write your email address...", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "please write your password...", Toast.LENGTH_SHORT).show();
        }

        else{
//            loadingBar.setTitle("Create Account");
//            loadingBar.setMessage("Please wait while we are checking credentials.");
//            loadingBar.setCanceledOnTouchOutside(false);
//            loadingBar.show();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(loginEmailInput.text.toString(), loginPasswordInput.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {



                        Toast.makeText(this, "You are Logged in successfully", Toast.LENGTH_SHORT).show()

                        // Sign in success, update UI with the signed-in user's information

                        val intent = Intent(Intent(this, AdminHomeActivity::class.java))
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()


                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Error logging in user", Toast.LENGTH_SHORT).show()

                    }


                }

        }

    }
}