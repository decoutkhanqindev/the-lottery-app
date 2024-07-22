package com.example.thelotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var luckyNumbers: TextView
    private lateinit var shareBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        luckyNumbers = findViewById(R.id.luckyNumbers)
        shareBtn = findViewById(R.id.shareBtn)

        luckyNumbers.text = generateLuckyNumbers(7)

        shareBtn.setOnClickListener {
            shareResult(receiverUsername(), luckyNumbers.text.toString())
        }
    }

    private fun generateLuckyNumbers(count: Int): String =
        List(count) { (0..9).random() }.joinToString(" ")

    private fun receiverUsername(): String = intent.extras?.getString("userName").toString()
//    private fun receiverUsername (): String {
//        var bundle: Bundle? = intent.extras
//        return bundle?.getString("userName").toString()
//    }

    private fun shareResult(userName: String, luckyNumbers: String) =
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain" // loại dữ liệu là văn bản thuần túy (plain text).
            putExtra(Intent.EXTRA_SUBJECT, "$userName generates these numbers")
            putExtra(Intent.EXTRA_TEXT, "The Lottery Numbers are: $luckyNumbers")
        })
}