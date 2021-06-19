package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Clear Tv
        buttonClear.setOnClickListener {
            inputTV.setText("")
            lastNumeric = false
            lastDot = false
        }
    }

    fun onDigitClick(view: View){
        lastNumeric = true
        inputTV.append((view as Button).text)
    }
    fun onDecimalClick(view: View){
        if (lastNumeric && !lastDot){
            inputTV.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperatorClick(view: View){
        if (lastNumeric && !isOperatorAdded(inputTV.text.toString())){
            inputTV.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }
    fun onEqualClick(view: View){
        if (lastNumeric){
            var tvValue = inputTV.text.toString()
            var prefix = ""
            try {

                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var num1 = splitValue[0]
                    var num2 = splitValue[1]

                    if (!prefix.isEmpty()){
                        num1 = prefix + num1
                    }

                    inputTV.text = removeZeroFromResult((num1.toDouble() - num2.toDouble()).toString())
                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var num1 = splitValue[0]
                    var num2 = splitValue[1]

                    if (!prefix.isEmpty()){
                        num1 = prefix + num1
                    }

                    inputTV.text = removeZeroFromResult((num1.toDouble() + num2.toDouble()).toString())
                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var num1 = splitValue[0]
                    var num2 = splitValue[1]

                    if (!prefix.isEmpty()){
                        num1 = prefix + num1
                    }

                    inputTV.text = removeZeroFromResult((num1.toDouble() / num2.toDouble()).toString())
                }else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var num1 = splitValue[0]
                    var num2 = splitValue[1]

                    if (!prefix.isEmpty()){
                        num1 = prefix + num1
                    }

                    inputTV.text = removeZeroFromResult((num1.toDouble() * num2.toDouble()).toString())
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroFromResult(string: String): String{
        var value = ""
        if (string.contains(".0"))
            value = string.substring(0,string.length-2)
        return value
    }
    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}