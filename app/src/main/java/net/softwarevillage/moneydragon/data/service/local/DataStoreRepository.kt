package net.softwarevillage.moneydragon.data.service.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private object PreferencesKeys {
        val isOnboardingComplete = booleanPreferencesKey("onboard_complete")
    }

    suspend fun setOnboardState(isComplete: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.isOnboardingComplete] = isComplete
        }
    }

    val getOnboardState: Flow<Boolean?> = dataStore.data.catch { e ->
        if (e is IOException) {
            emit(emptyPreferences())
        } else {
            throw e
        }
    }.map {
        it[PreferencesKeys.isOnboardingComplete] ?: false
    }

}