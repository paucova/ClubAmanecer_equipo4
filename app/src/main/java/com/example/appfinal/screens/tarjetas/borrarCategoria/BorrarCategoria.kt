package com.example.appfinal.screens.tarjetas.borrarCategoria

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun BorrarCategoria(navController: NavHostController,
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
            // Regresar
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

            // Ver las colecciones
            val categories = tarjetasViewModel.categories.value

            LazyColumn {
                // Dividir la lista en grupos de tres
                items(categories.chunked(3)) { rowCategories ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Renderizar hasta tres categorías en esta fila
                        for (categoria in rowCategories) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .height(300.dp)
                                    .width(300.dp)
                            ) {
                                // Poner de fondo la burbuja
                                Image(
                                    painter = painterResource(id = R.drawable.burbuja),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize() // Asegura que la imagen llene el espacio del botón
                                )

                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "${categoria.text}",
                                        color = Color.Black,
                                        fontSize = 40.sp
                                    )
                                }

                                IconButton(onClick = {
                                    tarjetasViewModel.deleteCategoryByName(categoria.text)
                                }) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}