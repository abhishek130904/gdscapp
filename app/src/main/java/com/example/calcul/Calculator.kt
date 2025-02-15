package com.example.calcul
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.calcul.CalculatorViewModel

val basicButtons = listOf(
    "AC", "C", "%", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "More", "0", ".", "="
)

@Composable
fun Calculator(modifier: Modifier = Modifier, calculatorViewModel: CalculatorViewModel) {
    val equationText = calculatorViewModel.equationText.observeAsState()
    val resultText = calculatorViewModel.resultText.observeAsState()
    val angleMode = calculatorViewModel.angleMode.observeAsState("Rad")
    var showAdvanced by remember { mutableStateOf(false) }

    val advancedButtons = listOf(
        "^", "√", "sin", "cos", "tan",
        "log", "ln", angleMode.value
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = equationText.value ?: "",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = resultText.value ?: "",
                style = TextStyle(
                    fontSize = 60.sp,
                    color = Color.White,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                items(basicButtons) {
                    CalculatorButton(btn = it, onClick = {
                        if (it == "More") {
                            showAdvanced = !showAdvanced
                        } else {
                            calculatorViewModel.onButtonClick(it)
                        }
                    })
                }
            }
            if (showAdvanced) {
                Spacer(modifier = Modifier.height(10.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                ) {
                    items(advancedButtons) {
                        CalculatorButton(btn = it, onClick = {
                            calculatorViewModel.onButtonClick(it)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(4.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            contentColor = Color.White,
            containerColor = getColor(btn)
        ) {
            Text(text = btn, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun getColor(btn: String): Color {
    return when (btn) {
        "C", "AC", "=", "/", "*", "-", "+", "%", "More" -> Color(0xFFD32F2F)
        else -> Color(0xFF424242)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    val calculatorViewModel = CalculatorViewModel()
    Calculator(calculatorViewModel = calculatorViewModel)
}
