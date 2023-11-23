package com.example.appfinal.screens.jugar.juegos.juego4

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.screens.home.noRippleClickable
import com.example.appfinal.screens.jugar.juegos.audioBurbuja
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    val context = LocalContext.current

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

    // Lógica del juego
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

                        audioBurbuja(context)
                    }

                    if (visibleImages.isEmpty()){
                        // Llamar a la función audio para reproducir el sonido
                        audioYay2(context)
                    }

                }
            }
        } else {
            currentNumber = 1 // Restablecer el número actual a 1
            // Retraso de 2 segundos antes de generar nuevas imágenes
            LaunchedEffect(Unit) {
                delay(1500)
                val randomImageCount = Random.nextInt(1, 11)
                val minDistance = 250 // Define la distancia mínima aquí
                addNewImages3(images, randomImageCount, minDistance)
                visibleImages = images.sortedBy { it.number }
            }
        }
    }
}

data class DraggableImage3(
    val id: Int,
    var offset: IntOffset,
    val drawable: Int,
    var isVisible: Boolean = true,
    val number: Int
)

fun addNewImages3(images: MutableList<DraggableImage3>, imageCount: Int, minDistance: Int) {
    images.clear()

    val drawableIds = mutableListOf(
        R.drawable.burbuja_roja,
        R.drawable.burbuja_rosa,
        R.drawable.burbuja_morada,
        R.drawable.burbuja_azul,
        R.drawable.burbuja_cian,
        R.drawable.burbuja_verde,
        R.drawable.burbuja_bosque,
        R.drawable.burbuja_amarilla,
        R.drawable.burbuja_naranja,
        R.drawable.burbuja_negra
    )

    val usedDrawables = mutableSetOf<Int>()

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

        val availableDrawables = drawableIds.filter { it !in usedDrawables }
        if (availableDrawables.isEmpty()) {
            // Si ya se han utilizado todas las imágenes, reinicia el conjunto
            usedDrawables.clear()
            drawableIds.shuffle()
        }

        val drawable = availableDrawables.random()

        val number = id
        usedDrawables.add(drawable)

        images.add(
            DraggableImage3(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                drawable = drawable,
                isVisible = true,
                number = number
            )
        )
    }
}

@Composable
fun DraggableImage3(image: DraggableImage3, onDeleteClick: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .offset { image.offset }
            .size(200.dp) // Tamaño fijo para las imágenes
            .fillMaxSize()
            .noRippleClickable {
                image.isVisible = !image.isVisible
                onDeleteClick()
            }
    ) {
        // Mostrar la imagen en lugar de texto
        Image(
            painter = painterResource(id = image.drawable),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // Agregar el número sobre la imagen
        Text(
            text = image.number.toString(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 65.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun generateImages3(): MutableList<DraggableImage3> {
    val images = mutableListOf<DraggableImage3>()

    for (id in 1..10) {
        val xOffset = Random.nextInt(100, 2000)
        val yOffset = Random.nextInt(100, 1000)
        val drawableIds = listOf(
            R.drawable.burbuja_roja,
            R.drawable.burbuja_rosa,
            R.drawable.burbuja_morada,
            R.drawable.burbuja_azul,
            R.drawable.burbuja_cian,
            R.drawable.burbuja_verde,
            R.drawable.burbuja_bosque,
            R.drawable.burbuja_amarilla,
            R.drawable.burbuja_naranja,
            R.drawable.burbuja_negra
        )
        val drawable = drawableIds.random()

        images.add(
            DraggableImage3(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                drawable = drawable,
                number = id
            )
        )
    }

    return images
}

fun audioYay2(context: Context) {
    GlobalScope.launch {
        delay(500)

        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.yay)
        mediaPlayer.start()
    }
}