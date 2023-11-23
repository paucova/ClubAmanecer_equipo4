package com.example.appfinal.screens.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import com.example.appfinal.model.User
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun VerInformacion (navController: NavHostController, context: Context, tarjetasViewModel: TarjetasViewModel){
    val azulClaro = Color(173, 216, 230)
    val tarjetasViewModel: TarjetasViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Botón de regreso a login
        Column (modifier = Modifier.fillMaxSize()){
            // Regresar
            Button(
                onClick = {
                    navController.navigate("AgregarUsuario") {
                        popUpTo("AgregarUsuario") {
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

            // Obtener la lista de usuarios del ViewModel
            val users = tarjetasViewModel.users.value

            LazyColumn {
                items(users.chunked(1)){ rowUsers ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        for (user in rowUsers) {
                            Box(
                                modifier = Modifier
                                    .width(1500.dp)
                                    .height(200.dp)
                                    .padding(8.dp)
                            ) {
                                // Poner de fondo una imagen
                                Image(
                                    painter = painterResource(id = R.drawable.caja),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize()
                                )

                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row {
                                        Text(
                                            text = "Usuario: ${user.nombre}",
                                            color = Color.Black,
                                            fontSize = 35.sp
                                        )
                                    }

                                    Row {
                                        Text(
                                            text = "Grupo: ${user.grupo}",
                                            color = Color.Black,
                                            fontSize = 35.sp
                                        )
                                    }
                                }

                                Image(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clickable {
                                            tarjetasViewModel.borrarUsuario(user)
                                        }
                                )

                            }
                        }
                    }
                }
            }

        }
    }
}