package com.example.appfinal.screens.jugar.juegos.juego4

import android.content.Context
import android.media.MediaPlayer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.screens.jugar.juegos.audio

@Composable
fun Suma (navController: NavHostController, grupo: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_suma),
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

        QuizGame()

    }
}

@Composable
fun QuizGame() {
    var score by remember { mutableStateOf(0) }
    var currentQuestion by remember { mutableStateOf(generateQuestion()) }
    var correctAnswer by remember { mutableStateOf(currentQuestion.correctAnswer) }
    var wrongAnswer by remember { mutableStateOf(currentQuestion.wrongAnswer) }

    val context = LocalContext.current

    val onAnswerSelected: (Int) -> Unit = { selectedAnswer ->
        if (selectedAnswer == currentQuestion.correctAnswer) {
            audioCorrecto(context)
            score++
        } else {
            audioIncorrecto(context)
        }

        currentQuestion = generateQuestion()
        correctAnswer = currentQuestion.correctAnswer
        wrongAnswer = currentQuestion.wrongAnswer
    }

    val swap = (0..1).random() == 1

    // Columna para que se separe
    Column {
        // Puntos
        Row (
            modifier = Modifier.offset(x = 1100.dp, y = 650.dp)
        ) {
            Text("Puntos: $score", style = MaterialTheme.typography.headlineMedium)
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
                Text(text = "${currentQuestion.num1} + ${currentQuestion.num2}",
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
                        .clickable { onAnswerSelected(correctAnswer) }
                ) {
                    Text(correctAnswer.toString(),
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
                        .clickable { onAnswerSelected(wrongAnswer) }
                ) {
                    Text(wrongAnswer.toString(),
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
                        .clickable { onAnswerSelected(wrongAnswer) }
                ) {
                    Text(wrongAnswer.toString(),
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
                        .clickable { onAnswerSelected(correctAnswer) }
                ) {
                    Text(correctAnswer.toString(),
                        fontSize = 150.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize())
                }
            }
        }

    }
}

data class Question(val num1: Int, val num2: Int, val correctAnswer: Int, val wrongAnswer: Int)

fun generateQuestion(): Question {
    val num1 = (1..10).random()
    val num2 = (1..10).random()
    val correctAnswer = num1 + num2
    var wrongAnswer = (1..10).random()
    if(correctAnswer == wrongAnswer){
        wrongAnswer += 1
    }
    return Question(num1, num2, correctAnswer, wrongAnswer)
}

fun audioCorrecto(context: Context) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.correcto)
    mediaPlayer.start()
}

fun audioIncorrecto(context: Context) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.incorrecto)
    mediaPlayer.start()
}