package com.example.appfinal.screens.jugar.juegos.juego4

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.R

@Composable
fun Resta (navController: NavHostController, grupo: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_resta),
            contentDescription = null, // Puedes proporcionar una descripción si es necesario
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Botón de regreso a HomeScreen
        Button(
            onClick = {
                navController.navigate("Juego4Screen/$grupo") {
                    popUpTo("Juego4Screen/$grupo") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = "Regresar",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        QuizGame2()

    }
}

@Composable
fun QuizGame2() {
    var score2 by remember { mutableStateOf(0) }
    var currentQuestion2 by remember { mutableStateOf(generateQuestion2()) }
    var correctAnswer2 by remember { mutableStateOf(currentQuestion2.correctAnswer2) }
    var wrongAnswer2 by remember { mutableStateOf(currentQuestion2.wrongAnswer2) }

    fun onAnswerSelected2(selectedAnswer2: Int) {
        if (selectedAnswer2 == currentQuestion2.correctAnswer2) {
            score2++
        }
        currentQuestion2 = generateQuestion2()
        correctAnswer2 = currentQuestion2.correctAnswer2
        wrongAnswer2 = currentQuestion2.wrongAnswer2
    }

    val swap = (0..1).random() == 1

    // Columna para que se separe
    Column {
        // Puntos
        Row (
            modifier = Modifier.offset(x = 1100.dp, y = 650.dp)
        ) {
            Text("Puntos: $score2", style = MaterialTheme.typography.headlineMedium)
        }

        // La pregunta
        Row (
            modifier = Modifier.offset(x = 500.dp, y = 3.dp)
        ){
            Box(
                modifier = Modifier
                    .width(700.dp)
                    .height(250.dp)
                    .background(
                        color = Color(65, 82, 179),
                        shape = RoundedCornerShape(8.dp)
                    )
            ){
                Text(text = "${currentQuestion2.num12} - ${currentQuestion2.num22}",
                    color = Color.White,
                    fontSize = 200.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize())
            }
        }

        // Botones con opciones
        if (swap) {
            Column (
                modifier = Modifier.offset(x = 295.dp, y = 120.dp)
            ) {
                Box (
                    modifier = Modifier
                        .width(250.dp)
                        .height(200.dp)
                        .clickable { onAnswerSelected2(correctAnswer2) }
                ) {
                    Text(correctAnswer2.toString(),
                        fontSize = 150.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize())
                }
            }

            Column (
                modifier = Modifier.offset(x = 770.dp, y = -80.dp)
            ) {
                Box (
                    modifier = Modifier
                        .width(250.dp)
                        .height(200.dp)
                        .clickable { onAnswerSelected2(wrongAnswer2) }
                ) {
                    Text(wrongAnswer2.toString(),
                        fontSize = 150.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize())
                }
            }
        }

        else {
            Column (
                modifier = Modifier.offset(x = 295.dp, y = 120.dp)
            ) {
                Box (
                    modifier = Modifier
                        .width(250.dp)
                        .height(200.dp)
                        .clickable { onAnswerSelected2(wrongAnswer2) }
                ) {
                    Text(wrongAnswer2.toString(),
                        fontSize = 150.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize())
                }
            }

            Column (
                modifier = Modifier.offset(x = 770.dp, y = -80.dp)
            ) {
                Box (
                    modifier = Modifier
                        .width(250.dp)
                        .height(200.dp)
                        .clickable { onAnswerSelected2(correctAnswer2) }
                ) {
                    Text(correctAnswer2.toString(),
                        fontSize = 150.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize())
                }
            }
        }

    }
}

data class Question2(val num12: Int, val num22: Int, val correctAnswer2: Int, val wrongAnswer2: Int)

fun generateQuestion2(): Question2 {
    val num12 = (1..10).random()
    val num22 = (1..num12).random()
    val correctAnswer2 = num12 - num22
    var wrongAnswer2 = (1..10).random()
    if(correctAnswer2 == wrongAnswer2){
        wrongAnswer2 += 1
    }
    return Question2(num12, num22, correctAnswer2, wrongAnswer2)
}