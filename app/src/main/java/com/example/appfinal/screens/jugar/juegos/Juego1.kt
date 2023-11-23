package com.example.appfinal.screens.jugar.juegos

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.screens.aprender.viewImages.processTTS
import com.example.appfinal.viewModel.TarjetasViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun Juego1(
    navController: NavHostController,
    tarjetasViewModel: TarjetasViewModel,
    context: Context,
    grupo: String,

    ) {
    val azulClaro = Color(173, 216, 230)
    var imagen by remember { mutableStateOf(tarjetasViewModel.images.value[Random.nextInt(tarjetasViewModel.images.value.size)])}
    var visual by remember{ mutableStateOf(false)}
    val coroutineScope = rememberCoroutineScope()
    val bitmap: ImageBitmap? by remember(imagen) {
        mutableStateOf(getBitmap2(imagen.name, imagen.category, imagen.text, imagen.filePath, context))
    }

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

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

                Box(modifier = Modifier
                    .size(width = 500.dp, height = 500.dp)
                    .clickable {
                        visual = true
                        processTTS(context, imagen.text)
                        coroutineScope.launch {
                            delay(3000)
                            visual = false
                            imagen = tarjetasViewModel.images.value[Random.nextInt(tarjetasViewModel.images.value.size)]
                        }
                    }) {

                    Image(
                        painter = if (visual && bitmap != null && imagen.filePath == -1) BitmapPainter(
                            bitmap!!
                        ) else painterResource(id = if (visual) imagen.filePath else R.drawable.burbuja),
                        contentDescription = "Tarjeta",
                        modifier = Modifier.fillMaxSize()
                            )}

                }
            }

        }





fun getBitmap2(name: String, category: String?, text: String, filepath: Int, context: Context): ImageBitmap? {
    return try {
        context.openFileInput(name).use { inputStream ->
            if (inputStream != null) {
                BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
            } else {
                null
            }
        }
    } catch (e: Exception) {
        Log.d("ERROR", e.message.toString())
        null
    }
}
