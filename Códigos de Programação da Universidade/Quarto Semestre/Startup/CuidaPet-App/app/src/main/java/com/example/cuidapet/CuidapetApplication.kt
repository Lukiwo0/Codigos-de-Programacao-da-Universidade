package com.example.cuidapet

import android.app.Application
import com.example.cuidapet.data.local.database.AppDatabase
import com.example.cuidapet.data.repository.PetRepository
import com.example.cuidapet.data.repository.UserRepository

class CuidapetApplication : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    val userRepository by lazy { UserRepository(database.userDao()) }
    val petRepository by lazy { PetRepository(database.petDao()) }
}