package com.example.appfinal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appfinal.dao.CategoriasDao
import com.example.appfinal.dao.ImagesDao
import com.example.appfinal.dao.UsersDao
import com.example.appfinal.model.Categorias
import com.example.appfinal.model.Images
import com.example.appfinal.model.User

@Database(entities = [Images::class, Categorias::class, User::class], version = 2, exportSchema = false)
abstract class TarjetasDatabase : RoomDatabase() {
    abstract val daoImg: ImagesDao
    abstract val daoCat: CategoriasDao
    abstract val daoUser: UsersDao
}