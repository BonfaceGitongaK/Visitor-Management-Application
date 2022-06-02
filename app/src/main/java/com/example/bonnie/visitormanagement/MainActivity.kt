package com.example.bonnie.visitormanagement

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var registerActivityBtn : Button
    lateinit var loginActivityBtn : Button

    lateinit var adminLogin:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerActivityBtn = findViewById<Button>(R.id.registerActivityBtn)
        registerActivityBtn.setOnClickListener(clickListener)

        loginActivityBtn = findViewById<Button>(R.id.loginActivityBtn)
        loginActivityBtn.setOnClickListener(listener)

        adminLogin = findViewById<Button>(R.id.adminLogin)


        adminLogin.setOnClickListener {
            val intent = Intent(this,AdminLoginActivity::class.java)
            startActivity(intent)
        }


    }

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.registerActivityBtn -> {
                val intent = Intent(this,RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    val listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.loginActivityBtn -> {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}