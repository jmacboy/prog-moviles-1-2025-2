package com.example.practicacalculadora

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicacalculadora.ui.theme.PracticaCalculadoraTheme

@Composable
fun Calculator(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf("") }
    var prevNumber by remember { mutableIntStateOf(0) }
    var currentOperation by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Row {
            Text(
                text = result.ifEmpty { "0" },
                fontSize = 24.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                text = "1",
                onClick = {
                    result = result + "1"
                },
                modifier = Modifier
                    .weight(1f),
            )
            TextButton(
                text = "2",
                onClick = {
                    result = result + "2"

                },
                modifier = Modifier
                    .weight(1f),
            )
            TextButton(
                text = "3",
                onClick = {
                    result = result + "3"

                },
                modifier = Modifier
                    .weight(1f),
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                text = "4",
                onClick = {
                    result = result + "4"
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "5",
                onClick = {
                    result = result + "5"
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "6",
                onClick = {
                    result = result + "6"
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                text = "7",
                onClick = {
                    result = result + "7"
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "8",
                onClick = {
                    result = result + "8"
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "9",
                onClick = {
                    result = result + "9"
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                text = "0",
                onClick = {
                    result = result + "0"
                },
                modifier = Modifier
                    .weight(1f)
            )

        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                text = "+",
                onClick = {
                    if (result.isNotEmpty()) {
                        prevNumber = result.toInt()
                        result = ""
                        currentOperation = "+"
                    }
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "-",
                onClick = {
                    if (result.isNotEmpty()) {
                        prevNumber = result.toInt()
                        result = ""
                        currentOperation = "-"

                    }
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "x",
                onClick = {
                    if (result.isNotEmpty()) {
                        prevNumber = result.toInt()
                        result = ""
                        currentOperation = "x"

                    }
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "/",
                onClick = {
                    if (result.isNotEmpty()) {
                        prevNumber = result.toInt()
                        result = ""
                        currentOperation = "/"
                    }
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                text = "=",
                onClick = {
                    val currentNumber = result.toIntOrNull() ?: 0
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
                    result = operationResult.toString()
                    currentOperation = ""
                    prevNumber = 0
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButton(
                text = "C",
                onClick = {
                    result = result.substring(0, result.length - 1)
                },
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(
                text = "CE",
                onClick = {
                    result = ""
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun TextButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text,
            fontSize = 16.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    PracticaCalculadoraTheme {
        Calculator()
    }
}
