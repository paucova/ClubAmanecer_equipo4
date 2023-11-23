package com.example.appfinal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appfinal.model.Categorias
import com.example.appfinal.model.Images
import com.example.appfinal.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Insert
    suspend fun saveImageReference(image: Images)

    @Query("Select * from images")
    fun getAllImages(): Flow<List<Images>>

    @Query("SELECT * FROM images WHERE category = :category")
    fun getImagesByCategory(category: String): Flow<List<Images>>

    @Delete
    suspend fun deleteImage(image: Images)
}

@Dao
interface CategoriasDao {
    @Insert
    suspend fun insertCategoria(textEntity: Categorias)

    @Query("SELECT * FROM Categorias")
    fun getAllCategorias(): Flow<List<Categorias>>

    @Query("DELETE FROM Categorias WHERE text = :name")
    suspend fun deleteCategoryByName(name: String)
}

@Dao
interface UsersDao {
    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM Users WHERE nombre = :nombre AND contrasena = :contrasena")
    suspend fun encontrarUsuario(nombre: String, contrasena: String): User?
}