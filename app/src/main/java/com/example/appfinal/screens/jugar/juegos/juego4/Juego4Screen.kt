package com.example.appfinal.screens.jugar.juegos.juego4

import androidx.compose.foundation.Image
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
fun Juego4Screen (navController: NavHostController, grupo: String) {
    // Box para poner imagen de fondo
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_jugar2),
            contentDescription = null, // Puedes proporcionar una descripción si es necesario
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

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

        // Para que las dos rows no se overlap
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Row 1: burbuja suma y resta
            Row() {
                // Burbuja suma
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .noRippleClickable {
                                navController.navigate("Juego4.4Suma/$grupo")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_suma),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Burbuja resta
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .noRippleClickable {
                                navController.navigate("Juego4.3Resta/$grupo")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_resta),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // Row 2: burbuja colores y numeros
            Row() {
                // Burbuja colores
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .noRippleClickable {
                                navController.navigate("Juego4.1Colores/$grupo")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_colores),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Burbuja numeros
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .noRippleClickable {
                                navController.navigate("Juego4.2Numeros/$grupo")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_numeros),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }

    }

}