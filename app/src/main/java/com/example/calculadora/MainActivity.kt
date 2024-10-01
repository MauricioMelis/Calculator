package com.example.calculadora  // Reemplaza esto con el nombre de tu paquete

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.R

class MainActivity : AppCompatActivity() {
    private lateinit var etShowNumber: TextView
    private var isNewOperation = true
    private var oldNumber = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etShowNumber = findViewById(R.id.etShowNumber)

        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSub: Button = findViewById(R.id.buttonSub)
        val buttonMul: Button = findViewById(R.id.buttonMul)
        val buttonDiv: Button = findViewById(R.id.buttonDiv)
        val buttonAC: Button = findViewById(R.id.buttonAC)
        val buttonEqual: Button = findViewById(R.id.buttonEqual) // Añadir el botón igual

        // Configurar los listeners para los botones numéricos
        val buttons = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        buttons.forEach { button ->
            button.setOnClickListener {
                onNumberClick(button.text.toString())
            }
        }

        // Configurar listeners para los botones de operación
        buttonAdd.setOnClickListener { onOperatorClick("+") }
        buttonSub.setOnClickListener { onOperatorClick("-") }
        buttonMul.setOnClickListener { onOperatorClick("*") }
        buttonDiv.setOnClickListener { onOperatorClick("/") }

        // Listener para el botón igual
        buttonEqual.setOnClickListener {
            calculateResult()
        }

        // Listener para el botón AC
        buttonAC.setOnClickListener {
            clear()
        }

        // Listener para el botón punto
        buttonDot.setOnClickListener {
            if (isNewOperation) {
                etShowNumber.text = "0."
                isNewOperation = false
            } else if (!etShowNumber.text.contains(".")) {
                etShowNumber.append(".")
            }
        }
    }

    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            etShowNumber.text = number
            isNewOperation = false
        } else {
            etShowNumber.append(number)
        }
    }

    private fun onOperatorClick(op: String) {
        if (!isNewOperation) {
            oldNumber = etShowNumber.text.toString()
            operator = op
            isNewOperation = true
        }
    }

    private fun calculateResult() {
        if (oldNumber.isNotEmpty() && operator.isNotEmpty()) {
            val newNumber = etShowNumber.text.toString()
            val result = when (operator) {
                "+" -> oldNumber.toDouble() + newNumber.toDouble()
                "-" -> oldNumber.toDouble() - newNumber.toDouble()
                "*" -> oldNumber.toDouble() * newNumber.toDouble()
                "/" -> if (newNumber != "0") oldNumber.toDouble() / newNumber.toDouble() else "Error"
                else -> 0.0
            }
            etShowNumber.text = result.toString()
            oldNumber = ""
            operator = ""
            isNewOperation = true
        }
    }

    private fun clear() {
        etShowNumber.text = "0"
        oldNumber = ""
        operator = ""
        isNewOperation = true
    }
}
