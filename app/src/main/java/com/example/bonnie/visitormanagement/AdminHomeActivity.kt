package com.example.bonnie.visitormanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AdminHomeActivity : AppCompatActivity() {

    lateinit var btnGetDetails : Button
    lateinit var btnGuestList : Button
    lateinit var btnLogout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        btnGetDetails = findViewById<Button>(R.id.btnGetDetails)
        btnGuestList = findViewById<Button>(R.id.btnGuestList)
        btnLogout = findViewById<Button>(R.id.btnLogout)

        btnGetDetails.setOnClickListener {
            val intent = Intent(applicationContext, AdminGetDetailsActivity::class.java)
            startActivity(intent)
        }

        btnGuestList.setOnClickListener {
            val intent = Intent(applicationContext, GuestListActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()

        }

    }
}