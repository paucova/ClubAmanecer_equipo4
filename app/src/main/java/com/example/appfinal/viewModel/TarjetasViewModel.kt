package com.example.appfinal.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinal.model.Categorias
import com.example.appfinal.NuevoAmanecerApp
import com.example.appfinal.model.Images
import com.example.appfinal.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TarjetasViewModel(application: Application) : AndroidViewModel(application) {
    val images = mutableStateOf<List<Images>>(emptyList())
    val categories = mutableStateOf<List<Categorias>>(emptyList())
    val users = mutableStateOf<List<User>>(emptyList())
    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    private val database = (application as NuevoAmanecerApp).database
    private val daoImagenes = database.daoImg
    private val daoCategorias = database.daoCat
    private val daoUsuario = database.daoUser

    init{
        viewModelScope.launch {
            daoImagenes.getAllImages().collect() {
                images.value = it
            }
        }
        viewModelScope.launch {
            daoCategorias.getAllCategorias().collect(){
                categories.value = it
            }
        }
        viewModelScope.launch {
            daoUsuario.getAllUsers().collect(){
                users.value = it
            }
        }
    }
    fun saveImageReference(name: String, category: String, text: String, filePath:Int) {
        val image = Images(0,name, category, text, filePath)
        viewModelScope.launch {
            daoImagenes.saveImageReference(image)
        }
    }
    fun nuevaCategoria(text: String){
        val categ = Categorias(0,text)
        viewModelScope.launch {
            daoCategorias.insertCategoria(categ)
        }
    }

    fun agregarUsuario(nombre: String, contrasena: String, grupo: String){
        val usuario = User(0, nombre, contrasena, grupo)
        viewModelScope.launch {
            daoUsuario.addUser(usuario)
        }
    }
    fun autenticar(nombre: String, contrasena: String){
        viewModelScope.launch {
            val user: User? = daoUsuario.encontrarUsuario(nombre, contrasena)
            _userState.value = user
        }
    }
    fun deleteImage(id: Int, name: String, category: String, text: String, filePath: Int ){
        val image = Images(id, name, category, text, filePath)
        viewModelScope.launch {
            daoImagenes.deleteImage(image)
        }
    }

    fun deleteCategoryByName(name: String) {
        viewModelScope.launch {
            daoCategorias.deleteCategoryByName(name)
        }
    }

    fun obtenerCategorias(): List<Categorias> {
        return categories.value
    }

    fun borrarUsuario(user: User) {
        viewModelScope.launch {
            daoUsuario.borrarUsuario(user)
        }
    }
}