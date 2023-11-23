package com.example.appfinal.screens.aprender.viewImages

import android.content.Context
import android.graphics.BitmapFactory
import android.provider.MediaStore.Images.Media.getBitmap
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.model.Images
import com.example.appfinal.viewModel.TarjetasViewModel
import java.util.Locale

@Composable
fun ViewImagesScreen(
    navController: NavHostController,
    tarjetasViewModel: TarjetasViewModel,
    context: Context,
    categoria: String? = "N/A",
    numero: String?,
    grupo: String
){
    var numColumnas = numero?.toInt()
    val azulClaro = Color(173, 216, 230)

    val imagenesFiltradas = tarjetasViewModel.images.value.filter { it.category == categoria }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        Column{
            // botón para regresar
            Button(
                onClick = {
                    navController.navigate("ColumnasScreen/${categoria}/$grupo") {}
                },
                modifier = Modifier.padding(8.dp),
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

            // display de las tarjetas
            numColumnas?.let { GridCells.Fixed(it) }?.let {
                LazyVerticalGrid(
                    columns = it,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(imagenesFiltradas) { imagen ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    horizontal = if (numColumnas > 1) 16.dp else 300.dp,
                                    vertical = 20.dp
                                )
                                .aspectRatio(1f)
                        ) {
                            val bitmap = categoria?.let {
                                getBitmap(
                                    name = imagen.name,
                                    category = imagen.category,
                                    text = imagen.text,
                                    filepath = imagen.filePath,
                                    context = context,

                                    )
                            }
                            if (bitmap != null) {
                                Box(modifier = Modifier.fillMaxSize()
                                    .clickable { processTTS(context, imagen.text) }) {
                                    Image(
                                        bitmap = bitmap,
                                        contentDescription = "Tarjeta",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            } else if (imagen.filePath != -1) {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { processTTS(context, imagen.text) }) {
                                    Image(
                                        painter = painterResource(id = imagen.filePath),
                                        contentDescription = "Tarjeta",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun getBitmap(name: String, category: String?, text: String, filepath:Int, context: Context): ImageBitmap? {
    val bitmap = remember {
        try {
            context.openFileInput(name).use { inputStream ->
                if (inputStream != null) {
                    BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
                }
                else {
                    null
                }
            }
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
            null
        }
    }
    return bitmap
}
fun processTTS(context: Context, word: String){
    var  textToSpeech: TextToSpeech? = null
    textToSpeech = TextToSpeech(context){
        if (it == TextToSpeech.SUCCESS){
            textToSpeech?.let {txtToSpeech ->
                val locSpanish = Locale("spa", "MEX")
                txtToSpeech.language = locSpanish
                txtToSpeech.setSpeechRate(1.0f)
                txtToSpeech.speak(
                    word,
                    TextToSpeech.QUEUE_ADD,
                    null,
                    null
                )
            }
        }
    }
}