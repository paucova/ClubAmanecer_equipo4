package com.example.appfinal.screens.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfinal.screens.aprender.AprenderScreen
import com.example.appfinal.screens.aprender.categorias.ViewCategoriesScreen
import com.example.appfinal.screens.aprender.columnas.ColumnasScreen
import com.example.appfinal.screens.aprender.viewImages.ViewImagesScreen
import com.example.appfinal.screens.home.HomeScreen
import com.example.appfinal.screens.jugar.JugarScreen
import com.example.appfinal.screens.jugar.juegos.Juego1
import com.example.appfinal.screens.jugar.juegos.Juego2
import com.example.appfinal.screens.jugar.juegos.Juego3
import com.example.appfinal.screens.jugar.juegos.juego4.Colores
import com.example.appfinal.screens.jugar.juegos.juego4.Juego4Screen
import com.example.appfinal.screens.jugar.juegos.juego4.Numeros
import com.example.appfinal.screens.jugar.juegos.juego4.Resta
import com.example.appfinal.screens.jugar.juegos.juego4.Suma
import com.example.appfinal.screens.login.AgregarUsuario
import com.example.appfinal.screens.login.LoginScreen
import com.example.appfinal.screens.login.VerInformacion
import com.example.appfinal.screens.tarjetas.TarjetasScreen
import com.example.appfinal.screens.tarjetas.borrarCategoria.BorrarCategoria
import com.example.appfinal.screens.tarjetas.borrarTarjeta.BorrarTarjetaScreen
import com.example.appfinal.screens.tarjetas.nuevaCategoria.AgregarCategoriaScreen
import com.example.appfinal.screens.tarjetas.nuevaTarjeta.AgregarTarjetaScreen
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun NavigationScreen(tarjetasViewModel: TarjetasViewModel, context: Context){
    // #1  Crear el objeto de NavController
    val navController = rememberNavController()

    // #2 Crear el contenedor y definir las rutas
    NavHost(navController = navController, startDestination = "LoginScreen"){
        // Lo del login
        composable("LoginScreen"){
            LoginScreen(navController)
        }

        // Agregar a un usuario
        composable("AgregarUsuario"){
            AgregarUsuario(navController)
        }

        // Ver información del usuario
        composable("VerInformacion") {
            VerInformacion(navController, context, tarjetasViewModel)
        }

        // Paginas dentro de HomeScreen
        composable("HomeScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                HomeScreen(navController, grupo)
            }
        }

        composable("AprenderScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                AprenderScreen(navController, tarjetasViewModel, grupo)
            }

        }

        composable("JugarScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                JugarScreen(navController, grupo)
            }
        }

        composable("TarjetasScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                TarjetasScreen(navController, grupo)
            }
        }

        // Agregar categoria y tarejetas (en aprender)
        composable("ViewCategoriesScreen/{grupo}"){
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                ViewCategoriesScreen(navController, tarjetasViewModel, grupo)
            }
        }

        composable("ColumnasScreen/{text}/{grupo}"){
            val grupo = it.arguments?.getString("grupo")
            val categoria = it.arguments?.getString("text")
            ColumnasScreen(navController, categoria, grupo)
        }

        composable("ViewImagesScreen/{numero}/{text}/{grupo}"){
            val categoria = it.arguments?.getString("text")
            val numero = it.arguments?.getString("numero")
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                ViewImagesScreen(navController, tarjetasViewModel, context, categoria, numero, grupo)
            }
        }

        // Agregar tarejta y categoría (en tarjetas)
        composable("AgregarTarjetaScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                AgregarTarjetaScreen(navController, grupo)
            }
        }
        composable("AgregarCategoriaScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                AgregarCategoriaScreen(navController, grupo)
            }
        }

        composable("BorrarTarjetaScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                BorrarTarjetaScreen(navController, context, grupo)
            }
        }

        composable("BorrarCategoriaScreen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                BorrarCategoria(navController, context, grupo)
            }
        }

        // Videojuegos
        composable("Juego1/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Juego1(navController,tarjetasViewModel, context, grupo)
            }
        }

        composable("Juego2/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Juego2(navController, grupo)
            }
        }

        composable("Juego3/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Juego3(navController, grupo)
            }
        }

        composable("Juego4Screen/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Juego4Screen(navController, grupo)
            }
        }

        // Juego 4 opciones
        composable("Juego4.1Colores/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Colores(navController, grupo)
            }
        }

        composable("Juego4.2Numeros/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Numeros(navController, grupo)
            }
        }

        composable("Juego4.3Resta/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Resta(navController, grupo)
            }
        }

        composable("Juego4.4Suma/{grupo}") {
            val grupo = it.arguments?.getString("grupo")
            if (grupo != null) {
                Suma(navController, grupo)
            }
        }

    }

}