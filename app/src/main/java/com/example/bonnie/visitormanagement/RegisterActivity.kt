package com.example.bonnie.visitormanagement

//import android.app.ProgressDialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {

    private lateinit var userDatabaseReference: DatabaseReference

    var mAuth = FirebaseAuth.getInstance()
    lateinit var btnRegister : Button
    lateinit var registerEmailInput : EditText
    lateinit var registerPasswordInput : EditText
    lateinit var loginLink : TextView
//    lateinit var loadingBar : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userDatabaseReference = FirebaseDatabase.getInstance().getReference("users")

        btnRegister = findViewById<Button>(R.id.btnRegister)
        registerEmailInput = findViewById<EditText>(R.id.registerEmailInput)
        registerPasswordInput = findViewById<EditText>(R.id.registerPasswordInput)

        loginLink = findViewById<TextView>(R.id.loginLink)

        setUpUI()

        btnRegister.setOnClickListener {
            registerUser()
        }

        loginLink.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

    private fun setUpUI() {
        btnRegister.setOnClickListener {

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun registerUser() {

        val email = registerEmailInput.text.toString()
        val password = registerPasswordInput.text.toString()


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

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(registerEmailInput.text.toString(), registerPasswordInput.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val firebaseUser : FirebaseUser = task.result!!.user!!

                        Toast.makeText(this, "You are registered successfully", Toast.LENGTH_SHORT).show()

                        // Sign in success, update UI with the signed-in user's information

                        val intent = Intent(Intent(this, LoginActivity::class.java))
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_id", firebaseUser.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Error Signing user", Toast.LENGTH_LONG).show()

                    }


                }

        }

    }



}