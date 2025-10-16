package com.mederico.android.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mederico.android.R
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText



class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val editText = findViewById<AppCompatEditText>(R.id.editText)
        btnStart.setOnClickListener {
            val name = editText.text.toString()
            if (name.isNotEmpty()) {
                Log.i("FirstAppActivity", "Button clicked y el text es $name")
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }

    }
}