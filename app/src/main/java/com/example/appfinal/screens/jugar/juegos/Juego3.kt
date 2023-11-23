package com.example.appfinal.screens.jugar.juegos

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.screens.jugar.juegos.juego4.DraggableImage3
import com.example.appfinal.screens.jugar.juegos.juego4.generateImages3
import kotlin.math.roundToInt

@Composable
fun Juego3(navController: NavHostController, grupo: String) {
    val azulClaro = Color(173, 216, 230)
    var images by remember { mutableStateOf(generateImages3()) }
    var visibleImages by remember { mutableStateOf(listOf<DraggableImage3>()) }
    var deletedImages by remember { mutableStateOf(mutableListOf<DraggableImage3>()) }
    var deletedImageCount by remember { mutableStateOf(0) }
    var currentNumber by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Botón de regreso a HomeScreen


        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    fontWeight = FontWeight.Bold)
            }
                Surface(
                    modifier = Modifier.size(2000.dp, 1500.dp)
                        .background(color = azulClaro),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Drag()
                }
        }
    }
}

@Composable
private fun Drag() {
    val azulClaro = Color(173, 216, 230)
    val arreglopeces = arrayListOf(
        arrayListOf(R.drawable.pez_rojo, "rojo"),
        arrayListOf(R.drawable.pez_naranja, "naranja"),
        arrayListOf(R.drawable.pez_amarillo, "amarillo"),
        arrayListOf(R.drawable.pez_verde, "verde"),
        arrayListOf(R.drawable.pez_azul, "azul"),
        arrayListOf(R.drawable.pez_morado, "morado")
    )

    val arreglocolores = arrayListOf(
        arrayListOf(R.drawable.colorrojo, "rojo"),
        arrayListOf(R.drawable.colornaranja, "naranja"),
        arrayListOf(R.drawable.coloramarillo, "amarillo"),
        arrayListOf(R.drawable.colorverde, "verde"),
        arrayListOf(R.drawable.colorazul, "azul"),
        arrayListOf(R.drawable.colormorado, "morado"))

    var imagen by remember { mutableStateOf(R.drawable.gris2) }
    var imagen2 by remember { mutableStateOf(R.drawable.gris2) }
    var imagen3 by remember { mutableStateOf(R.drawable.gris2) }

    var color1String by remember { mutableStateOf("nada") }
    var color2String by remember { mutableStateOf("nada") }
    var color3String by remember { mutableStateOf("nada") }

    val coloresSeleccionados = remember {
        arreglocolores.shuffled().take(3)
    }
    val colores_nuevos = remember {
        coloresSeleccionados.shuffled().take(3)
    }

    Box(modifier = Modifier.fillMaxSize().background(color = azulClaro)) {
        var color1OffsetX by remember { mutableStateOf(0f) }
        var color1OffsetY by remember { mutableStateOf(0f) }

        var color2OffsetX by remember { mutableStateOf(0f) }
        var color2OffsetY by remember { mutableStateOf(0f) }

        var color3OffsetX by remember { mutableStateOf(0f) }
        var color3OffsetY by remember { mutableStateOf(0f) }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            val toPaint1 = coloresSeleccionados[0]
            val toPaint2 = coloresSeleccionados[1]
            val toPaint3 = coloresSeleccionados[2]

            Row(horizontalArrangement = Arrangement.Center) {
                Column {
                    Image(
                        painter = painterResource(id = toPaint1[0] as Int),
                        contentDescription = "color",
                        modifier = Modifier.size(100.dp)
                    )
                }
                Column {
                    Image(
                        painter = painterResource(id = toPaint2[0] as Int),
                        contentDescription = "pez",
                        modifier = Modifier.size(100.dp)
                    )
                }
                Column {
                    Image(
                        painter = painterResource(id = toPaint3[0] as Int),
                        contentDescription = "pez",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.Center) {
                Column {
                    Image(
                        painter = painterResource(id = imagen),
                        contentDescription = "pez",
                        modifier = Modifier.size(300.dp)
                    )
                }
                Column {
                    Image(
                        painter = painterResource(id = imagen2),
                        contentDescription = "pez",
                        modifier = Modifier.size(300.dp)
                    )
                }
                Column {
                    Image(
                        painter = painterResource(id = imagen3),
                        contentDescription = "pez",
                        modifier = Modifier.size(300.dp)
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.height(100.dp)){
            }
            Row {
                val color1 = colores_nuevos[0]
                val color2 = colores_nuevos[1]
                val color3 = colores_nuevos[2]
                Image(
                    painter = painterResource(id = color1[0] as Int),
                    contentDescription = null,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                color1OffsetX.roundToInt(),
                                color1OffsetY.roundToInt()
                            )
                        }
                        .size(300.dp)
                        .padding(16.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                color1OffsetX += dragAmount.x
                                color1OffsetY += dragAmount.y
                                //Log.d("imagen", "$color1OffsetX, $color1OffsetY")
                                if (color1OffsetX < -8 && color1OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color1[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color1[1] }
                                        ?.get(0) as? Int
                                    imagen = colorearPez as Int
                                    color1String = colorSeleccionado as String
                                }
                                if (color1OffsetX > 600 && color1OffsetX < 700 && color1OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color1[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color1[1] }
                                        ?.get(0) as? Int
                                    imagen2 = colorearPez as Int
                                    color2String = colorSeleccionado as String
                                }
                                if (color1OffsetX > 1200 && color1OffsetX < 1300 && color1OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color1[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color1[1] }
                                        ?.get(0) as? Int
                                    imagen3 = colorearPez as Int
                                    color3String = colorSeleccionado as String
                                }

                            }
                        }
                )

                Image(
                    painter = painterResource(id = color2[0] as Int),
                    contentDescription = null,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                color2OffsetX.roundToInt(),
                                color2OffsetY.roundToInt()
                            )
                        }
                        .size(300.dp)
                        .padding(16.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                color2OffsetX += dragAmount.x
                                color2OffsetY += dragAmount.y
                                //Log.d("imagen", "$color2OffsetX, $color2OffsetY")
                                if (color2OffsetX < -550 && color2OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color2[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color2[1] }
                                        ?.get(0) as? Int
                                    imagen = colorearPez as Int
                                    color1String = colorSeleccionado as String

                                }
                                if (color2OffsetX < 100 && color2OffsetX > -100 && color2OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color2[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color2[1] }
                                        ?.get(0) as? Int
                                    imagen2 = colorearPez as Int
                                    color2String = colorSeleccionado as String
                                }
                                if (color2OffsetX < 700 && color2OffsetX > 500 && color2OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color2[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color2[1] }
                                        ?.get(0) as? Int
                                    imagen3 = colorearPez as Int
                                    color3String = colorSeleccionado as String
                                }
                            }
                        }
                )

                Image(
                    painter = painterResource(id = color3[0] as Int),
                    contentDescription = null,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                color3OffsetX.roundToInt(),
                                color3OffsetY.roundToInt()
                            )
                        }
                        .size(300.dp)
                        .padding(16.dp)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                color3OffsetX += dragAmount.x
                                color3OffsetY += dragAmount.y
                                //Log.d("imagen", "$color3OffsetX, $color3OffsetY")
                                if (color3OffsetX < -1100 && color3OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color3[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color3[1] }
                                        ?.get(0) as? Int
                                    imagen = colorearPez as Int
                                    color1String = colorSeleccionado as String
                                }
                                if (color3OffsetX < -500 && color3OffsetX > -600 && color3OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color3[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color3[1] }
                                        ?.get(0) as? Int
                                    imagen2 = colorearPez as Int
                                    color2String = colorSeleccionado as String
                                }
                                if (color3OffsetX < 200 && color3OffsetX > 0 && color3OffsetY < -600) {
                                    var colorSeleccionado: String? = arreglopeces
                                        .find { it[1] == color3[1] }
                                        ?.get(1) as? String
                                    var colorearPez: Int? = arreglopeces
                                        .find { it[1] == color3[1] }
                                        ?.get(0) as? Int
                                    imagen3 = colorearPez as Int
                                    color3String = colorSeleccionado as String
                                }

                            }
                        }
                )
            }
        }
    }


    if (color1String == coloresSeleccionados[0][1] && color2String == coloresSeleccionados[1][1] &&
        color3String == coloresSeleccionados[2][1]){
        Log.d("imagen", "Entro")
        Box(modifier = Modifier.fillMaxSize()
        ){
            Image(painter = painterResource(id = R.drawable.ganaste),
                contentDescription = null,
                modifier = Modifier.size(800.dp)
                    .align(Alignment.Center)
            )
        }
    }
}