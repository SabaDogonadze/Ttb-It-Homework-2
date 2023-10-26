package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException
import java.util.Scanner
//import kotlin.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var editText = findViewById<EditText>(R.id.et)
        val button = findViewById<Button>(R.id.btn)
        var textView = findViewById<TextView>(R.id.tv)

        button.setOnClickListener {
            val input = editText.text.toString()
            var errorMessage: String? = null
            if (input.isNotEmpty()) {
                try {
                    var inputText = input.toLong()
                    if (inputText in 1..1000) {
                        textView.text = numberToWords(inputText)
                    } else {
                        Toast.makeText(this, "გთხოვთ შეიყვანოთ რიცხვი 1..1000", Toast.LENGTH_SHORT).show()
                        editText.error = "გთხოვთ შეიყვანოთ რიცხვი 1..1000"
                    }
                }catch(e:NumberFormatException){
                    Toast.makeText(this, "შეიყვანეთ რიცხვი და არა რაიმე სხვა", Toast.LENGTH_SHORT).show()
                    editText.error = "შეიყვანეთ რიცხვი და არა რაიმე სხვა"
                }

            } else {
                Toast.makeText(this, "უჯრა ცარიელია, შეიყვანეთ რიცხვი 1..1000", Toast.LENGTH_SHORT).show()
                editText.error = "უჯრა ცარიელია,გთხოვთ შეიყვანოთ რიცხვი 1..1000"
            }

        }

    }


    private fun numberToWords(input: Long): String {

        val reader = Scanner(System.`in`)
        var number = input

                val lastDigit = number % 10
                val secondDigit = number / 10
                val secondDigitIs = secondDigit % 10
                val firstDigit = number / 100

                if (secondDigitIs.toInt() == 0 && lastDigit.toInt() == 0) {

                    return Lists.wordsHundred[firstDigit.toInt()] + "ი"
                }
                if (number < 20) {
                    return Lists.listOneUntilTwelve[number.toInt()]
                }
                if (number in 20..99) {
                    if (lastDigit.toInt() == 0) {

                        return Lists.wordsDecimalExceptions[secondDigitIs.toInt()]
                    }
                    if (secondDigit.toInt() % 2 != 0) {

                        return Lists.wordsDecimal[secondDigit.toInt()] + Lists.words2[lastDigit.toInt()]
                    }
                    return Lists.wordsDecimal[secondDigit.toInt()] + Lists.listOneUntilTwelve[lastDigit.toInt()]
                }
                if (number in 100..1000) {
                    if (secondDigit.toInt() > 0 && lastDigit.toInt() == 0){
                        return Lists.wordsHundred[firstDigit.toInt()] + " " + Lists.wordsDecimalExceptions[secondDigitIs.toInt()]
                    }
                    if (secondDigit.toInt() % 2 != 0) {
                        return Lists.wordsHundred[firstDigit.toInt()] + " " + Lists.wordsDecimal[secondDigitIs.toInt()] + Lists.words2[lastDigit.toInt()]
                    }

                    return Lists.wordsHundred[firstDigit.toInt()] + " " + Lists.wordsDecimal[secondDigitIs.toInt()] + Lists.words[lastDigit.toInt()]

                }

            return "დასრულდა"
        }


}

