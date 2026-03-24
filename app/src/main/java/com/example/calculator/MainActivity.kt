package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {

    private lateinit var tvResult: TextView
    private var currentInput =""
    private var operator = ""
    private var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        val grid = findViewById<GridLayout>(R.id.gridLayout)

        for (i in 0 until grid.childCount){
            val button = grid.getChildAt(i) as Button
            button.setOnClickListener {
                handleInput(button.text.toString())
            }

        }

        }

    private fun handleInput(value: String){
        when (value){
            in "0".."9" ->{
                currentInput += value
                tvResult.text = currentInput
            }
            "+", "-","*","/"-> {
                if (currentInput.isNotEmpty()){
                    firstNumber = currentInput.toDouble()
                    operator = value
                    currentInput = ""
                }
            }
            "="-> {
                if(currentInput.isNotEmpty()){
                    val secondNumber = currentInput.toDouble()
                    val result = when (operator){
                        "+"-> firstNumber + secondNumber
                        "-"-> firstNumber - secondNumber
                        "*"-> firstNumber * secondNumber
                        "/"-> if (secondNumber != 0.0) firstNumber / secondNumber else 0.0
                        else -> 0.0
                    }
                    tvResult.text = result.toString()
                    currentInput = result.toString()
                }
            }
            "C"-> {
                currentInput = ""
                operator = ""
                firstNumber = 0.0
                tvResult.text = "0"
            }
        }
    }
    }
