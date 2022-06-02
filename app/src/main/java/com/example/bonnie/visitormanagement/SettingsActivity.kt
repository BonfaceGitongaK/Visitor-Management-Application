package com.example.bonnie.visitormanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingsActivity : AppCompatActivity() {

    private lateinit var database:FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var settingsPhoneNumber:EditText
    private lateinit var settingsFullName:EditText
    private lateinit var settingsEmail:EditText
    private lateinit var btnSave:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        database= FirebaseDatabase.getInstance()
        reference=database.getReference("Users")

        settingsPhoneNumber = findViewById<EditText>(R.id.settingsPhoneNumber)
        settingsFullName = findViewById<EditText>(R.id.settingsFullName)
        settingsEmail = findViewById<EditText>(R.id.settingsEmail)
        btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            sendData()
        }



    }

    private fun sendData(){
        var phone=settingsPhoneNumber.text.toString()
        var name=settingsFullName.text.toString()
        var email=settingsEmail.text.toString()

        if (phone.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()){
            var model=DatabaseModel(phone, name, email)
            var id=reference.push().key

            //Here we can send data to firebase
            reference.child(id!!).setValue(model)
            Toast.makeText(this,"Data saved successfully",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"All fields required",Toast.LENGTH_SHORT).show()
        }
    }
}