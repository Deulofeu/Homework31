package com.example.homework31

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counter = findViewById<DialView>(R.id.counter)
        findViewById<Button>(R.id.button).setOnClickListener {
            counter.change()
        }
    }
}