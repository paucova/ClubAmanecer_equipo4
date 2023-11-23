package com.example.appfinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Images (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val category: String,
    val text: String,
    val filePath: Int
)

@Entity(tableName = "Categorias")
data class Categorias (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)

@Entity(tableName = "Users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val contrasena: String,
    val grupo: String
)