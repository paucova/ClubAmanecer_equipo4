package com.example.appfinal

import android.app.Application
import androidx.room.Room
import com.example.appfinal.database.TarjetasDatabase

class NuevoAmanecerApp : Application() {
    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            TarjetasDatabase::class.java,
            "na_db"
        ).build()
    }
}
