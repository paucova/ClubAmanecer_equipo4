package com.example.appfinal.screens.aprender.categorias

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun ViewCategoriesScreen(
    navController: NavHostController,
    tarjetasViewModel: TarjetasViewModel,
    grupo: String,
    onItemClick: (String) -> Unit = {}
) {
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
                            .clickable {
                                onItemClick(categoria.text)
                                navController.navigate("ColumnasScreen/${categoria.text}/$grupo")
                            }
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
                    }
                }
            }
        }
    }
}