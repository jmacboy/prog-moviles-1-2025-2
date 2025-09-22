package com.example.practicacalculadora.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.sp
import com.example.practicacalculadora.ui.theme.PracticaCalculadoraTheme
import com.example.practicacalculadora.viewmodels.CalculatorViewModel

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    vm: CalculatorViewModel = viewModel(),
) {

    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Row {
            Text(
                text = vm.result.value.ifEmpty { "0" },
                fontSize = 24.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
        NumberPanel(
            onNumberClick = { num ->
                vm.addNumber(num)
            }
        )
        OperationsPanel(
            selectOperationType = { theOperation ->
              vm.selectOperation(theOperation)
            },
            onEqualsClick = {
                vm.performOperation()
            }
        )
        ClearOperationsPanel(
            onClearOneClick = {
             vm.clearOne()
            },
            onClearAllClick = {
                vm.clearAll()
            }
        )
    }
}

@Composable
fun ClearOperationsPanel(
    onClearOneClick: () -> Unit = {},
    onClearAllClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "C",
            onClick = {
                onClearOneClick()
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "CE",
            onClick = {
                onClearAllClick()
            },
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun OperationsPanel(
    selectOperationType: (operation: String) -> Unit,
    onEqualsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TextButton(
            text = "+",
            onClick = {
                selectOperationType("+")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "-",
            onClick = {
                selectOperationType("-")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "x",
            onClick = {
                selectOperationType("x")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "/",
            onClick = {
                selectOperationType("/")
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
                onEqualsClick()
            },
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun NumberPanel(
    onNumberClick: (num: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            text = "1",
            onClick = {
                onNumberClick("1")
            },
            modifier = Modifier
                .weight(1f),
        )
        TextButton(
            text = "2",
            onClick = {
                onNumberClick("2")

            },
            modifier = Modifier
                .weight(1f),
        )
        TextButton(
            text = "3",
            onClick = {
                onNumberClick("3")

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
                onNumberClick("4")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "5",
            onClick = {
                onNumberClick("5")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "6",
            onClick = {
                onNumberClick("6")
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
                onNumberClick("7")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "8",
            onClick = {
                onNumberClick("8")
            },
            modifier = Modifier
                .weight(1f)
        )
        TextButton(
            text = "9",
            onClick = {
                onNumberClick("9")
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
                onNumberClick("0")
            },
            modifier = Modifier
                .weight(1f)
        )

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

@Preview
@Composable
fun ClearOperationsPanelPreview() {
    PracticaCalculadoraTheme {
        Column {
            ClearOperationsPanel(onClearOneClick = {}, onClearAllClick = {})
        }
    }
}

@Preview
@Composable
fun OperationsPanelPreview() {
    PracticaCalculadoraTheme {
        Column {
            OperationsPanel(selectOperationType = {}, onEqualsClick = {})
        }
    }
}


@Preview
@Composable
fun NumberPanelPreview() {
    PracticaCalculadoraTheme {
        Column {
            NumberPanel(onNumberClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    PracticaCalculadoraTheme {
        Calculator()
    }
}
