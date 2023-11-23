package com.example.appfinal.screens.aprender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.screens.aprender.categorias.ViewCategoriesScreen
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun AprenderScreen (navController: NavHostController, tarjetasViewModel: TarjetasViewModel, grupo: String) {
    // Establecer el fondo azul claro
    val azulClaro = Color(173, 216, 230)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Dentro de la misma página
        Column {
            // Boton de regreso a HomeScreen
            Row {
                Button(
                    onClick = {
                        navController.navigate("HomeScreen/$grupo") {
                            popUpTo("HomeScreen/$grupo") {
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
            }

            // Categorías
            Row {
                // Llamamos a la función que crea las tarjetas de selección de categorías
                ViewCategoriesScreen(navController = navController, tarjetasViewModel = tarjetasViewModel, grupo= grupo)
            }

        }

    }

}