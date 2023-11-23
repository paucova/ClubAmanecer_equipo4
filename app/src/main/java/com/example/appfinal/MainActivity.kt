package com.example.appfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appfinal.screens.navigation.NavigationScreen
import com.example.appfinal.ui.theme.AppFinalTheme
import com.example.appfinal.viewModel.TarjetasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val tarjetasViewModel : TarjetasViewModel = viewModel()
                    val context = LocalContext.current
                    //LoadCategories()
                    //LoadImages()
                    NavigationScreen(tarjetasViewModel, context)
                }
            }
        }
    }
}




