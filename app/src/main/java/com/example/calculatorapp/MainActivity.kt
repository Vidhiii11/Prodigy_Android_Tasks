package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

        val buttonIDs = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnDot, R.id.btnPercent
        )

        val operatorMap = mapOf(
            R.id.btnPlus to "+",
            R.id.btnMinus to "-",
            R.id.btnMultiply to "*",
            R.id.btnDivide to "/",
            R.id.btnPercent to "%"
        )

        buttonIDs.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val value = when (id) {
                    in operatorMap -> operatorMap[id]
                    else -> findViewById<Button>(id).text.toString()
                }
                expression += value
                tvInput.text = expression
            }
        }

        findViewById<Button>(R.id.btnAC).setOnClickListener {
            expression = ""
            tvInput.text = "0"
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            try {
                val result = ExpressionBuilder(expression).build().evaluate()
                tvInput.text = result.toString()
                expression = result.toString()
            } catch (e: Exception) {
                tvInput.text = "Error"
            }
        }

        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener {
            if (expression.isNotEmpty()) {
                if (expression.startsWith("-")) {
                    expression = expression.substring(1)
                } else {
                    expression = "-$expression"
                }
                tvInput.text = expression
            }
        }
    }
}
