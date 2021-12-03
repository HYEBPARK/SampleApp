package com.cookandroid.mysampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        val writeBtn:Button = findViewById(R.id.writeUploadBtn)
        writeBtn.setOnClickListener {

            val WriteText:EditText = findViewById(R.id.writeTextArea)
            val database = Firebase.database
            val myRef = database.getReference("board")

            myRef.push().setValue(
                Model(WriteText.text.toString())
            )
        }
    }
}