package com.example.calcul

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel() {

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    private val _angleMode = MutableLiveData("Rad")
    val angleMode: LiveData<String> = _angleMode

    private var isRadianMode = true

    fun onButtonClick(btn: String) {
        Log.i("Clicked Button", btn)

        _equationText.value?.let {
            when (btn) {
                "AC" -> {
                    _equationText.value = ""
                    _resultText.value = "0"
                    return
                }
                "C" -> {
                    if (it.isNotEmpty()) {
                        _equationText.value = it.substring(0, it.length - 1)
                    }
                    return
                }
                "=" -> {
                    _equationText.value = _resultText.value
                    return
                }
                "√" -> {
                    _equationText.value = "sqrt($it)"
                }
                "^" -> {
                    if (it.isNotEmpty() && !isOperator(it.last())) {
                        _equationText.value = "$it^"
                    }
                }
                "sin", "cos", "tan" -> {
                    if (it.isEmpty() || isOperator(it.last())) {
                        val func = if (isRadianMode) btn else "degToRad($btn)"
                        _equationText.value = "$func("
                    }
                }
                "log", "ln" -> {
                    if (it.isEmpty() || isOperator(it.last())) {
                        _equationText.value = "$btn("
                    }
                }
                "Rad", "Deg" -> {
                    isRadianMode = !isRadianMode
                    _angleMode.value = if (isRadianMode) "Rad" else "Deg"
                    return
                }
                "+", "-", "*", "/", "%" -> {
                    if (it.isNotEmpty() && !isOperator(it.last())) {
                        _equationText.value = it + btn
                    }
                }
                else -> {
                    _equationText.value = it + btn
                }
            }


            try {
                _resultText.value = calculateResult(_equationText.value.toString())
            } catch (_: Exception) { }
        }
    }


    private fun isOperator(char: Char): Boolean {
        return char in listOf('+', '-', '*', '/', '^', '%')
    }

    fun calculateResult(equation: String): String {
        val context: Context = Context.enter()
        context.optimizationLevel = -1
        val scriptable: Scriptable = context.initStandardObjects()

        context.evaluateString(scriptable, """
        function degToRad(angle) {
            return angle * (Math.PI / 180);
        }
    """.trimIndent(), "JavaScript", 1, null)

        var processedEquation = equation
            .replace("sqrt", "Math.sqrt")
            .replace("log", "Math.log")
            .replace("^", "**")

        if (!isRadianMode) {
            processedEquation = processedEquation
                .replace("sin(", "Math.sin(degToRad(")
                .replace("cos(", "Math.cos(degToRad(")
                .replace("tan(", "Math.tan(degToRad(")
        } else {
            processedEquation = processedEquation
                .replace("sin(", "Math.sin(")
                .replace("cos(", "Math.cos(")
                .replace("tan(", "Math.tan(")
        }

        var finalResult = context.evaluateString(scriptable, processedEquation, "Javascript", 1, null).toString()
        if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "")
        }

        return finalResult
    }
}
