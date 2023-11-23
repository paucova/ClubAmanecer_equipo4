package com.example.appfinal.screens.tarjetas.nuevaCategoria


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun AgregarCategoriaScreen(navController: NavHostController, grupo: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_coleccion),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Botón de regreso a HomeScreen
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

        Row (
            modifier = Modifier.offset(x = 375.dp, y = 260.dp)
        ){
            Text(text = "Categoría",
                color = Color.Black,
                fontSize = 20.sp)
        }

        CategoryPicker()

    }

}

@Composable
fun CategoryPicker() {
    val tarjetasViewModel: TarjetasViewModel = viewModel()
    var category by remember{ mutableStateOf("") }

    Column {
        // Textflied para ingresar categoría
        Row (
            modifier = Modifier.offset(x = 375.dp, y = 300.dp)
        ){
            OutlinedTextField(
                value = category,
                onValueChange = {category = it},
                modifier = Modifier
                    .height(100.dp)
                    .width(550.dp)
                    .clip(RoundedCornerShape(35.dp))
                    .background(color = Color.White),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                textStyle = LocalTextStyle.current.copy(fontSize = 45.sp)
            )
        }

        // Botón
        Row (
            modifier = Modifier.offset(x = 550.dp, y = 350.dp)
        ){
            Button(
                modifier = Modifier
                    .height(60.dp)
                    .width(225.dp),
                onClick = {
                    tarjetasViewModel.nuevaCategoria(category)
                    category = ""
                }
            ){
                Text(text = "Agregar categoría",
                    color = Color.White,
                    fontSize = 20.sp)
            }
        }

    }
}
