package com.example.bonnie.visitormanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class GenerateCodeActivity : AppCompatActivity() {

    private lateinit var qrCode: ImageView
    private lateinit var inputName : EditText
    private lateinit var inputPhone : EditText
    private lateinit var inputEmail : EditText
    private lateinit var inputId : EditText
    private lateinit var generatorBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_code)

        qrCode = findViewById(R.id.qrCode)
        inputName = findViewById(R.id.inputName)
        inputPhone = findViewById(R.id.inputPhone)
        inputEmail = findViewById(R.id.inputEmail)
        inputId = findViewById(R.id.inputId)
        generatorBtn = findViewById(R.id.generatorBtn)

        generatorBtn.setOnClickListener{
            if (inputName.text.toString() != "" && inputPhone.text.toString() != ""
                && inputEmail.text.toString() != "" && inputId.text.toString() != ""){

                generateQrCode()

            }else{
                val toast = Toast.makeText(this, "Empty field", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }

    }

    private fun generateQrCode() {
        val multiFormatWriter = MultiFormatWriter()

        try {
            val bitMatrix = multiFormatWriter.encode(
                inputName.text.toString(),
                BarcodeFormat.QR_CODE, 300, 300)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            qrCode.setImageBitmap(bitmap)
        }catch (e: WriterException) {
            e.printStackTrace()
        }
    }

}