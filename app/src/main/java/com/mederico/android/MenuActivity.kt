package com.mederico.android

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import com.mederico.android.firstapp.FirstAppActivity
import com.mederico.android.imcapp.ImcActivity
import com.mederico.android.todoapp.ToDoAppActivity


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSaludarApp = findViewById<Button>(R.id.btnSaludarApp)
        btnSaludarApp.setOnClickListener { navigateToSaludarApp() }
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        val btnToDoApp = findViewById<Button>(R.id.btnToDoApp)
        btnToDoApp.setOnClickListener { navigatetoDoApp() }
    }

    fun navigateToSaludarApp(): Unit {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    fun navigateToIMCApp(): Unit {
        val intent = Intent(this, ImcActivity::class.java)
        startActivity(intent)
    }

    fun navigatetoDoApp(): Unit {
        val intent = Intent(this, ToDoAppActivity::class.java)
        startActivity(intent)
    }
}