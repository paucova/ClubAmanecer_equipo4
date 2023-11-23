package com.example.appfinal.screens.aprender.columnas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.screens.home.noRippleClickable

@Composable
fun ColumnasScreen (
    navController: NavHostController,
    categoria: String? = "N/A",
    grupo: String?
) {
    // Establecer el fondo azul claro
    val azulClaro = Color(173, 216, 230)
    var numColumnas = 0;
    val grupoNum = grupo?.toInt()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Botón de regreso a HomeScreen
        Button(
            onClick = {
                navController.navigate("AprenderScreen/$grupo") {
                    popUpTo("AprenderScreen$grupo") {
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
        // Para que las dos rows no se overlap
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Row 1: burbuja 1 y 2
            Row() {
                // Burbuja 1
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp)
                            .noRippleClickable {
                                numColumnas = 1
                                navController.navigate("ViewImagesScreen/${numColumnas}/${categoria}/$grupo")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.columna1),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                // Burbuja 2
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp)
                            .noRippleClickable {
                                numColumnas = 2
                                if (grupoNum != null) {
                                    if (grupoNum > 1) {
                                        navController.navigate("ViewImagesScreen/${numColumnas}/${categoria}/$grupo")
                                    }
                                }
                            }
                    ) {
                        if (grupoNum != null) {
                            Image(
                                painter = painterResource(id = if (grupoNum > 1) R.drawable.columna2 else R.drawable.columna2_no),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }

            // Row 2: burbuja 3 y 4
            Row() {
                // Burbuja 3
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp)
                            .noRippleClickable {
                                numColumnas = 3
                                if (grupoNum != null) {
                                    if (grupoNum > 2) {
                                        navController.navigate("ViewImagesScreen/${numColumnas}/${categoria}/$grupo")
                                    }
                                }
                            }
                    ) {
                        if (grupoNum != null) {
                            Image(
                                painter = painterResource(id = if (grupoNum > 2) R.drawable.columna3 else R.drawable.columna3_no),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                // Burbuja 4
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .noRippleClickable {
                                numColumnas = 4
                                if (grupoNum != null) {
                                    if (grupoNum > 3) {
                                        navController.navigate("ViewImagesScreen/${numColumnas}/${categoria}/$grupo")
                                    }
                                }
                            }
                    ) {
                        if (grupoNum != null) {
                            Image(
                                painter = painterResource(id = if (grupoNum > 3) R.drawable.columna4 else R.drawable.columna4_no),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}