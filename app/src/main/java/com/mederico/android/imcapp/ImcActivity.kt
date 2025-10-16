package com.mederico.android.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.mederico.android.R
import java.text.DecimalFormat


class ImcActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentHeight: Int = 120
    private var currentAge: Int = 30


    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var textHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var textWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var textAge: TextView
    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_RESULT = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()
        initListeners()
        initUI()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        textHeight = findViewById(R.id.textHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        textWeight = findViewById(R.id.textWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        textAge = findViewById(R.id.textAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners(){
        viewMale.setOnClickListener{ setGenderColor() }
        viewFemale.setOnClickListener{ setGenderColor() }
        rsHeight.addOnChangeListener { _, value, _ ->
            textHeight.text = "${value.toInt()} cm"
            currentHeight = value.toInt()
        }
        btnAddWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            if (currentWeight < 20) {
                currentWeight = 20
            }else {
                currentWeight -= 1
            }
            setWeight()
        }
        btnSubtractAge.setOnClickListener {
            if (currentAge < 20) {
                currentAge = 20
            }else {
                currentAge -= 1
            }
            setAge()
        }
        btnAddAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_RESULT, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        textAge.text = currentAge.toString()
    }

    private fun setWeight() {
        textWeight.text = currentWeight.toString()
    }

    private fun setHeight() {
        textHeight.text = currentHeight.toString()
    }
    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        changeGender()
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
            } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setHeight()
        setWeight()
        setAge()
    }

}