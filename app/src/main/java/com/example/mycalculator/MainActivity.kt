package com.example.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var textViewResult: TextView? = null
    private var isDotAdded: Boolean = false
    private var isNumberAdded: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textViewResult)
    }

    fun onButtonClick(view: View) {
        textViewResult?.append((view as Button).text)
        isNumberAdded = true
    }

    fun onClear(view: View) {
        textViewResult?.text = ""
        isDotAdded = false
    }

    fun onClickDecimal(view: View) {
        if (isNumberAdded && !isDotAdded) {
            isDotAdded = true
            textViewResult?.append(".")
        } else if (!isNumberAdded && !isDotAdded) {
            textViewResult?.append("0.")
        }
    }

    fun onClickOperator(view: View) {
        textViewResult?.text?.let {
            if (isNumberAdded && !isOperatorAdded(it.toString())) {
                textViewResult?.append((view as Button).text)
                isNumberAdded = false
                isDotAdded = false
            } else {

            }
        }
    }

    fun onClickEqual(view: View) {
        if (isNumberAdded) {
            var textViewValue = textViewResult?.text.toString()
            var prefix = ""
            try {
                if (textViewValue.startsWith("-")) {
                    prefix = "-"
                    textViewValue = textViewValue.substring(1)
                }
                if (textViewValue.contains("-")) {
                    val splitValue = textViewValue.split("-")
                    var first = splitValue[0]
                    val second = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() - second.toDouble()
                    textViewResult?.text = removeZeroAfterDot(result.toString())
                } else if (textViewValue.contains("+")) {
                    val splitValue = textViewValue.split("+")
                    var first = splitValue[0]
                    val second = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() + second.toDouble()
                    textViewResult?.text = removeZeroAfterDot(result.toString())
                } else if (textViewValue.contains("*")) {
                    val splitValue = textViewValue.split("*")
                    var first = splitValue[0]
                    val second = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() * second.toDouble()
                    textViewResult?.text = removeZeroAfterDot(result.toString())
                } else if (textViewValue.contains("/")) {
                    val splitValue = textViewValue.split("/")
                    var first = splitValue[0]
                    val second = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        first = prefix + first
                    }
                    val result = first.toDouble() / second.toDouble()
                    textViewResult?.text = removeZeroAfterDot(result.toString())
                }
            } catch (e: java.lang.ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result

        if (value.contains(".0"))
            value = value.substring(0, value.length - 2)
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            return false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }
    }
}