package com.mederico.android.imcapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mederico.android.R
import com.mederico.android.imcapp.ImcActivity.Companion.IMC_RESULT

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var textResult: TextView
    private lateinit var textIMC: TextView
    private lateinit var textDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result = intent.extras?.getDouble(IMC_RESULT) ?: -1.0
        initComponents()
        initUi(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initComponents() {
        textResult = findViewById(R.id.textResult)
        textIMC = findViewById(R.id.textICM)
        textDescription = findViewById(R.id.textDescription)
        btnRecalculate = findViewById(R.id.btnReCalculate)
    }

    private fun initUi(result: Double) {
        when (result) {
            in 0.0..18.5 -> {
                textResult.text = getString(R.string.title_bajo_peso)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                textIMC.text = result.toString()
                textDescription.text = getString(R.string.descripcione_bajo_peso)
            }
            in 18.5..24.9 -> {
                textResult.text = getString(R.string.title_peso_normal)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                textIMC.text = result.toString()
                textDescription.text = getString(R.string.descripcione_peso_normal)
            }
            in 25.0..29.9 -> {
                textResult.text = getString(R.string.title_sobrepeso)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                textIMC.text = result.toString()
                textDescription.text = getString(R.string.descripcione_sobrepeso)
            }
            in 30.0..99.0 -> {
                textResult.text = getString(R.string.title_obesidad)
                textResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                textIMC.text = result.toString()
                textDescription.text = getString(R.string.descripcione_obesidad)
            }
            else -> {
                textResult.text = getString(R.string.error)
                textIMC.text = getString(R.string.error)
                textDescription.text = getString(R.string.error)
                return
            }
        }
    }

}