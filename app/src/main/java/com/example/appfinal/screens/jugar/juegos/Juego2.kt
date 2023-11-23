package com.example.appfinal.screens.jugar.juegos

import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.screens.home.noRippleClickable
import kotlin.random.Random

@Composable
fun Juego2 (navController: NavHostController, grupo: String){
    // Establecer el fondo azul claro
    val azulClaro = Color(173, 216, 230)

    // Variables
    var images by remember { mutableStateOf(generateImages()) }
    var visibleImages by remember { mutableStateOf(images.filter { it.isVisible }) }
    var deletedImages by remember { mutableStateOf(mutableListOf<DraggableImage>()) }
    var deletedImageCount by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Botón de regreso a HomeScreen
        Button(
            onClick = {
                navController.navigate("JugarScreen/$grupo") {
                    popUpTo("JugarScreen/$grupo") {
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

        // El texto que aparece
        Column (
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
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

        // Logica del juego
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (visibleImages.isNotEmpty()) {
                visibleImages.forEach { image ->
                    DraggableImage(image = image) {
                        deletedImages.add(image)
                        deletedImageCount++
                        visibleImages = visibleImages.filter { it != image }
                    }
                }
            } else {
                val randomImageCount = Random.nextInt(1, 11) // Genera un número aleatorio entre 1 y 20
                addNewImages(images, randomImageCount)
                visibleImages = images
            }
        }
    }
}

data class DraggableImage(
    val id: Int,
    var offset: IntOffset,
    val drawableResId: Int,
    var isVisible: Boolean = true,
    val size: Int
)

fun addNewImages(images: MutableList<DraggableImage>, imageCount: Int) {
    images.clear()

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

    for (id in 1..imageCount) {
        val xOffset = Random.nextInt(100, 1500)
        val yOffset = Random.nextInt(100, 1000)
        val drawableResId = drawableIds.random()
        val isVisible = true
        val size = Random.nextInt(100, 300)

        images.add(
            DraggableImage(
                id = images.size + 1,
                offset = IntOffset(xOffset, yOffset),
                drawableResId = drawableResId,
                isVisible = isVisible,
                size = size
            )
        )
    }
}

@Composable
fun DraggableImage(image: DraggableImage, onDeleteClick: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .offset { image.offset }
            .size(width = image.size.dp, height = image.size.dp)
            .fillMaxSize()
            .noRippleClickable {
                image.isVisible = !image.isVisible
                audioBurbuja(context)
                onDeleteClick()
            }
    ) {
        // Load the image using the drawable resource ID
        Image(
            painter = painterResource(id = image.drawableResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun generateImages(): MutableList<DraggableImage> {
    val images = mutableListOf<DraggableImage>()

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

    for (id in 1..10) {
        val xOffset = Random.nextInt(100, 2000)
        val yOffset = Random.nextInt(100, 1000)
        val drawableResId = drawableIds.random()
        val size = Random.nextInt(100, 300)

        images.add(
            DraggableImage(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                drawableResId = drawableResId,
                size = size
            )
        )
    }

    return images
}