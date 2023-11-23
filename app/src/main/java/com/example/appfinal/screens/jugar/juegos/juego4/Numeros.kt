package com.example.appfinal.screens.jugar.juegos.juego4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.screens.home.noRippleClickable
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun Numeros(navController: NavHostController, grupo: String) {
    // Fondo azul
    val azulClaro = Color(173, 216, 230)

    // Otras variables
    var images by remember { mutableStateOf(generateImages3()) }
    var visibleImages by remember { mutableStateOf(listOf<DraggableImage3>()) }
    var deletedImages by remember { mutableStateOf(mutableListOf<DraggableImage3>()) }
    var deletedImageCount by remember { mutableStateOf(0) }
    var currentNumber by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
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
                fontWeight = FontWeight.Bold)
        }

        // El texto que aparece
        Column (
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(
                text = "Número de imágenes visibles: ${visibleImages.size}",
                fontSize = 20.sp,
            )

            Text(
                text = "Número de imágenes eliminadas: $deletedImageCount",
                fontSize = 20.sp,
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (visibleImages.isNotEmpty()) {
            val sortedImages = visibleImages.sortedBy { it.number }
            sortedImages.forEach { image ->
                DraggableImage3(image = image) {
                    if (image.number == currentNumber) {
                        deletedImages.add(image)
                        deletedImageCount++
                        visibleImages = visibleImages.filter { it != image }
                        currentNumber++
                    }
                }
            }
        } else {
            currentNumber = 1 // Restablecer el número actual a 1
            val randomImageCount = Random.nextInt(1, 11)
            val minDistance = 250 // Define la distancia mínima aquí
            addNewImages3(images, randomImageCount, minDistance)
            visibleImages = images.sortedBy { it.number }
        }
    }
}

data class DraggableImage3(
    val id: Int,
    var offset: IntOffset,
    val color: Color,
    val radius: Int,
    var isVisible: Boolean = true,
    val number: Int
)

fun addNewImages3(images: MutableList<DraggableImage3>, imageCount: Int, minDistance: Int) {
    images.clear()

    val numbers = (1..imageCount).shuffled() // Lista de números aleatorios
    val usedNumbers = mutableSetOf<Int>()

    for (id in 1..imageCount) {
        var xOffset: Int
        var yOffset: Int

        do {
            xOffset = Random.nextInt(100, 1500)
            yOffset = Random.nextInt(100, 1000)

            // Asegúrate de que la nueva imagen tenga una distancia mínima con las imágenes existentes
            val tooClose = images.any { existingImage ->
                val distance = sqrt((xOffset - existingImage.offset.x).toDouble().pow(2) +
                        (yOffset - existingImage.offset.y).toDouble().pow(2))
                distance < minDistance
            }
        } while (tooClose)

        val color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
        val radius = Random.nextInt(150, 200)

        val number = numbers.first { it !in usedNumbers } // Obtener el próximo número no utilizado
        usedNumbers.add(number)

        images.add(
            DraggableImage3(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                color = color,
                radius = radius,
                isVisible = true,
                number = number
            )
        )
    }
}

@Composable
fun DraggableImage3(image: DraggableImage3, onDeleteClick: () -> Unit) {
    Box(
        modifier = Modifier
            .offset { image.offset }
            .size(image.radius.dp)
            .fillMaxSize()
            .background(color = image.color, shape = CircleShape)
            .noRippleClickable {
                image.isVisible = !image.isVisible
                onDeleteClick()
            }
    ) {
        // Agrega el contenido de la imagen aquí, si es necesario
        Text(
            text = image.number.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 100.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun generateImages3(): MutableList<DraggableImage3> {
    val images = mutableListOf<DraggableImage3>()

    for (id in 1..10) {
        val xOffset = Random.nextInt(100, 2000)
        val yOffset = Random.nextInt(100, 1000)
        val color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
        val radius = Random.nextInt(150, 200)

        images.add(
            DraggableImage3(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                color = color,
                radius = radius,
                number = id
            )
        )
    }

    return images
}