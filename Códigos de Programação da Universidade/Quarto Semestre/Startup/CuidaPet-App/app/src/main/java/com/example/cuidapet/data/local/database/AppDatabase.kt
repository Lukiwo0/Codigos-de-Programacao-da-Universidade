package com.example.cuidapet.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cuidapet.data.local.dao.PetDao
import com.example.cuidapet.data.local.dao.UserDao
import com.example.cuidapet.data.local.entities.AppConverters
import com.example.cuidapet.data.local.entities.PetEntity
import com.example.cuidapet.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class, PetEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AppConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun petDao(): PetDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cuidapet_database"
                )
                    // .fallbackToDestructiveMigration() // Descomente se mudar o banco e não quiser tratar migração (apaga dados)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}