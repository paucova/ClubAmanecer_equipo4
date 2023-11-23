package com.example.appfinal.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.viewModel.TarjetasViewModel

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun AgregarUsuario (navController: NavHostController) {
    // Variables
    val tarjetasViewModel: TarjetasViewModel = viewModel()
    var nombre = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var grupo = remember { mutableStateOf("") }

    // Box para poner imagen de fondo
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Botón de regreso a login
        Button(
            onClick = {
                navController.navigate("LoginScreen") {
                    popUpTo("LoginScreen") {
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

        // Una columna para que se separe
        Column {
            // Primera parte: Mensajes
            Column(
                modifier = Modifier.offset(x = 215.dp, y = 70.dp)
            ) {
                Text(
                    text = "Agregar Usuario",
                    color = Color.White,
                    fontSize = 60.sp)

                Text(text = "Bienvenido",
                    color = Color.White,
                    fontSize = 30.sp)
            }

            // Segunda parte: Ingresar informacion
            Column(
                modifier = Modifier.offset(x = 215.dp, y = 120.dp)
            ) {
                Text(text = "Usuario",
                    color = Color.White,
                    fontSize = 15.sp)

                TextField(value = nombre.value, onValueChange = {
                    nombre.value = it
                }, placeholder = {
                    Text("Email")
                })

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Contraseña",
                    color = Color.White,
                    fontSize = 15.sp)

                Row {
                    TextField(
                        value = password.value,
                        onValueChange = {
                            password.value = it
                        },
                        placeholder = {
                            Text("Contraseña")
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .height(60.dp)
                            .width(280.dp)
                    )

                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                //*************
            }

            Column (
                modifier = Modifier.offset(x = 215.dp, y = 115.dp)
            ) {
                Text(text = "Grupo",
                    color = Color.White,
                    fontSize = 15.sp)

                dropDownMenu(grupo = grupo.value, changeValueGrupo = { newGrupo ->
                    grupo.value = newGrupo})
            }

            // Tercera parte: Botones
            Column(
                modifier = Modifier.offset(x = 205.dp, y = 170.dp)
            ) {
                // Visualizar información
                Box(
                    modifier = Modifier
                        .height(47.dp)
                        .width(300.dp)
                        .clickable {
                            navController.navigate("VerInformacion")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ver_informacion),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Agregar usuario
                Box(
                    modifier = Modifier
                        .height(47.dp)
                        .width(300.dp)
                        .clickable {
                            tarjetasViewModel.agregarUsuario(
                                nombre.value,
                                password.value,
                                grupo.value
                            )
                            nombre.value = ""
                            password.value = ""
                            grupo.value = ""
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.agregar_usuario_2),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth() // Asegura que la imagen llene el espacio del botón
                    )
                }

            }

        }

    }

}

@Composable
fun dropDownMenu(grupo: String, changeValueGrupo: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val list = listOf("1", "2", "3", "4")

    var textFiledSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    OutlinedTextField(
        value = grupo,
        onValueChange = { changeValueGrupo(it) },
        modifier = Modifier
            .background(color = Color.White)
            .height(60.dp)
            .onGloballyPositioned { coordinates ->
                textFiledSize = coordinates.size.toSize()
            },
        readOnly = true,
        trailingIcon = {
            Icon(icon, "", Modifier.clickable { expanded = !expanded })
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { textFiledSize.width.toDp() })
    ) {
        list.forEach { label ->
            DropdownMenuItem(text = { Text(text = label) }, onClick = {
                changeValueGrupo(label)
                expanded = false
            })
        }
    }
}