package com.example.practicacalculadora.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var result = mutableStateOf("")

    var prevNumber = 0
    var currentOperation = ""
    fun addNumber(num: String) {
        result.value += num
    }

    fun selectOperation(theOperation: String) {
        if (result.value.isNotEmpty()) {
            prevNumber = result.value.toInt()
            result.value = ""
            currentOperation = theOperation
        }
    }

    fun performOperation() {
        val currentNumber = result.value.toIntOrNull() ?: 0
        var operationResult = 0
        when (currentOperation) {
            "+" -> operationResult = prevNumber + currentNumber
            "-" -> operationResult = prevNumber - currentNumber
            "x" -> operationResult = prevNumber * currentNumber
            "/" -> {
                if (currentNumber != 0) {
                    operationResult = prevNumber / currentNumber
                } else {
                    operationResult = 0 // Avoid division by zero
                }
            }
        }
        result.value = operationResult.toString()
        currentOperation = ""
        prevNumber = 0
    }

    fun clearOne() {
        result.value = result.value.substring(0, result.value.length - 1)
    }

    fun clearAll() {
        result.value = ""
    }
}
