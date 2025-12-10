package com.example.cuidapet.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// O DataStore é uma forma moderna e melhor para armazenar dados
// Aqui é criado um arquivo e o seu nome é "user_prefs"
val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    // Criamos a chave única chamada "onboarding_done"
    companion object{
        private val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")
    }

    // Isso aqui procura a chave que criamos, se não existir é false.
    // A vantagem de usar o Flow é que ele é atualiza automaticamente o valor, caso ele mude
    val onboardingDone: Flow<Boolean> = context.dataStore.data.map {
        prefs -> prefs[ONBOARDING_DONE] ?: false
    }

    // Essa função ela edita no datastore que o onboarding (apresentação inicial) foi concluida
    suspend fun setOnboardingDone(value: Boolean) {
        context.dataStore.edit {
            prefs -> prefs[ONBOARDING_DONE] = value
        }
    }
}

