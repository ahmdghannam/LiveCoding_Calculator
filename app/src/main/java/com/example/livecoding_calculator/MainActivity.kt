package com.example.livecoding_calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.livecoding_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var firstNumber: Double = 0.0
    var currentOperation: Operation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.btnClear.setOnClickListener {
            clearInput()
        }
        binding.btnPlus.setOnClickListener {
            prepareOperation(Operation.Plus)
        }
        binding.btnMinus.setOnClickListener {
            prepareOperation(Operation.Minus)
        }
        binding.btnMulti.setOnClickListener {
            prepareOperation(Operation.Multi)
        }
        binding.btnDiv.setOnClickListener {
            prepareOperation(Operation.Div)
        }
        binding.btnEqual.setOnClickListener {
            val result = doCurrentOperation()
            binding.textNumber.text = result.toString()
        }
    }

    private fun doCurrentOperation(): Double {
        val input=binding.textNumber.text.toString()
        if (!isValidInput(input)) return 0.0
        val secondNumber = input.toDouble()
        return when (currentOperation) {
            Operation.Plus -> firstNumber + secondNumber
            Operation.Minus -> firstNumber - secondNumber
            Operation.Multi -> firstNumber * secondNumber
            Operation.Div -> firstNumber / secondNumber
            null -> 0.0
        }
    }

    private fun clearInput() {
        binding.textNumber.text = ""
        binding.tvSavedNumber.text = ""
    }

    private fun prepareOperation(operation: Operation) {
        val input = binding.textNumber.text.toString()
        if (!isValidInput(input)) return

        firstNumber = input.toDouble()
        val savedNumber = firstNumber.toString() + operation.toString()
        binding.tvSavedNumber.text = savedNumber
        clearResult()
        currentOperation = operation
    }

    private fun isValidInput(input: String): Boolean {
        return if (input.isEmpty()) {
            showError("please enter a number first")
            return false
        }
        else true
    }

    private fun showError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun clearResult() {
        binding.textNumber.text = ""
    }

    fun onClickNumber(v: View) {
        val newDigit = (v as Button).text.toString()
        val oldDigits = binding.textNumber.text.toString()
        val newTextNumber = oldDigits + newDigit
        binding.textNumber.text = newTextNumber
    }
}