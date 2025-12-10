package com.example.cuidapet.data.repository

import com.example.cuidapet.data.local.dao.PetDao
import com.example.cuidapet.data.local.entities.toEntity
import com.example.cuidapet.data.local.entities.toExternalModel
import com.example.cuidapet.ui.screens.pets.models.Pet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PetRepository(private val petDao: PetDao) {

    suspend fun savePet(pet: Pet) {
        petDao.insertPet(pet.toEntity())
    }

    suspend fun deletePet(pet: Pet) {
        petDao.deletePet(pet.toEntity())
    }

    fun getAllPets(): Flow<List<Pet>> {
        return petDao.getAllPets().map { listEntity ->
            listEntity.map { it.toExternalModel() }
        }
    }

    suspend fun getPetById(id: String): Pet? {
        return petDao.getPetById(id)?.toExternalModel()
    }
}