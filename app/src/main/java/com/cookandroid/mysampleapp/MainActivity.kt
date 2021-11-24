package com.cookandroid.mysampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cookandroid.mysampleapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth // FirebaseAuth
    private lateinit var binding: ActivityMainBinding //Data Binding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val joinBtnClicked = findViewById<Button>(R.id.joinBtn)
        joinBtnClicked.setOnClickListener {

            // 이메일과 비밀번호 값을 가져오는 첫번째 방법
//            val email = findViewById<EditText>(R.id.emailArea)
//            val pwd = findViewById<EditText>(R.id.pwdArea)

            // 이메일과 비밀번호 값을 가져오는 두번째 방법
            val email = binding.emailArea
            val pwd = binding.pwdArea

            // Create Account
            auth.createUserWithEmailAndPassword(
                email.text.toString(),
                pwd.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Sing in
        binding.loginBtn.setOnClickListener {
            val email = binding.emailArea
            val pwd = binding.pwdArea

            auth.signInWithEmailAndPassword(
                email.text.toString(),
                pwd.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "no", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Sign out
        binding.logoutBtn.setOnClickListener {

            auth.signOut()
            Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

        }
    }
}