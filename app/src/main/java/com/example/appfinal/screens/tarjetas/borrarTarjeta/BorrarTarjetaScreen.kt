package com.example.appfinal.screens.tarjetas.borrarTarjeta

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appfinal.screens.aprender.viewImages.getBitmap
import com.example.appfinal.screens.aprender.viewImages.processTTS
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun BorrarTarjetaScreen(
    navController: NavHostController,
    context: Context,
    grupo: String
) {
    val tarjetasViewModel: TarjetasViewModel = viewModel()
    val azulClaro = Color(173, 216, 230)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Button(
                onClick = {
                    navController.navigate("TarjetasScreen/$grupo") {
                        popUpTo("TarjetasScreen/$grupo") {
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(tarjetasViewModel.images.value) { imagen ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = 30.dp,
                                vertical = 20.dp
                            )
                            .aspectRatio(1f)
                    ) {
                        val bitmap =
                            getBitmap(
                                name = imagen.name,
                                category = imagen.category,
                                text = imagen.text,
                                filepath = imagen.filePath,
                                context = context,
                            )
                        if (bitmap != null) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clickable { processTTS(context, imagen.text) }) {
                                Image(
                                    bitmap = bitmap,
                                    contentDescription = "Tarjeta"
                                )
                            }
                        } else if (imagen.filePath != -1) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = imagen.filePath),
                                    contentDescription = "Tarjeta",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        IconButton(onClick = {
                            tarjetasViewModel.deleteImage(
                                imagen.id,
                                imagen.name,
                                imagen.category,
                                imagen.text,
                                imagen.filePath
                            )
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}
