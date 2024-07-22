package com.example.thelotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var userName: EditText
    private lateinit var generateNumbersBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userName = findViewById(R.id.inputUserName)
        generateNumbersBtn = findViewById(R.id.generateNumbersBtn)

        generateNumbersBtn.setOnClickListener {
            if (userName.text.isEmpty()) {
                Toast.makeText(this, "Username is not empty!!!", Toast.LENGTH_LONG).show()
            } else {
                val moveToSecondActivity = Intent(this, SecondActivity::class.java).putExtra(
                    "userName",
                    userName.text.toString()
                )
                startActivity(moveToSecondActivity)
            }
        }
    }
}