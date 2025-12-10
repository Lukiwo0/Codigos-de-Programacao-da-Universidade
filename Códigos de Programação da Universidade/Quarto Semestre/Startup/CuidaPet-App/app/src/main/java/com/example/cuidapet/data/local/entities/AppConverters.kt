package com.example.cuidapet.data.local.entities

import androidx.room.TypeConverter
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import com.example.cuidapet.ui.screens.user.models.Gender
import java.time.LocalDate

class AppConverters {

    @TypeConverter
    fun fromData(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toData(dateString: String?): LocalDate? {
        return if (dateString.isNullOrBlank()) null else LocalDate.parse(dateString)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        // Transforma ["Asma", "Gripe"] em "Asma;Gripe"
        return list?.joinToString(separator = ";") ?: ""
    }

    @TypeConverter
    fun toList(data: String?): List<String> {
        // Transforma "Asma;Gripe" em ["Asma", "Gripe"]
        if (data.isNullOrBlank()) return emptyList()
        return data.split(";").map { it.trim() }
    }

    @TypeConverter
    fun fromSex(sex: Sex): String = sex.name
    @TypeConverter
    fun toSex(value: String): Sex = Sex.valueOf(value)

    @TypeConverter
    fun fromSpecies(species: Species): String = species.name
    @TypeConverter
    fun toSpecies(value: String): Species = Species.valueOf(value)

    @TypeConverter
    fun fromNeutered(status: NeuteredStatus): String = status.name
    @TypeConverter
    fun toNeutered(value: String): NeuteredStatus = NeuteredStatus.valueOf(value)

    @TypeConverter
    fun fromGender(gender: Gender): String = gender.name
    @TypeConverter
    fun toGender(value: String): Gender = Gender.valueOf(value)

}