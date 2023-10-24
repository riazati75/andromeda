@file:Suppress("UNCHECKED_CAST")

package ir.farsroidx.m31.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.additives.isExpired
import ir.farsroidx.m31.additives.toExpirationTime
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

internal class PreferenceImpl(
    context: Context, private val gson: Gson, private val provider: AndromedaProvider.Preference
) : Preference {

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(provider.name)

    private val dataStore: DataStore<Preferences> = context._dataStore

    override suspend fun store(key: String, value: String) =
        storeDataToPreferences(key, value)

    override suspend fun store(key: String, value: Int) =
        storeDataToPreferences(key, value)

    override suspend fun store(key: String, value: Float) =
        storeDataToPreferences(key, value)

    override suspend fun store(key: String, value: Double) =
        storeDataToPreferences(key, value)

    override suspend fun store(key: String, value: Long) =
        storeDataToPreferences(key, value)

    override suspend fun store(key: String, value: Boolean) =
        storeDataToPreferences(key, value)

    override suspend fun store(key: String, value: Set<String>) =
        storeDataToPreferences(key, value)

    override suspend fun get(key: String, alternate: String): String =
        getDataToPreferences(key, alternate)

    override suspend fun get(key: String, alternate: Int): Int =
        getDataToPreferences(key, alternate)

    override suspend fun get(key: String, alternate: Float): Float =
        getDataToPreferences(key, alternate)

    override suspend fun get(key: String, alternate: Double): Double =
        getDataToPreferences(key, alternate)

    override suspend fun get(key: String, alternate: Long): Long =
        getDataToPreferences(key, alternate)

    override suspend fun get(key: String, alternate: Boolean): Boolean =
        getDataToPreferences(key, alternate)

    override suspend fun get(key: String, alternate: Set<String>): Set<String> =
        getDataToPreferences(key, alternate)

    private suspend fun <T> storeDataToPreferences(key: String, value: T) {
        dataStore.edit { transform ->
            transform[stringPreferencesKey(key)] = gson.toJson(
                PreferenceModel(
                    value, provider.expTime.toExpirationTime(
                        provider.expUnit
                    )
                )
            )
        }
    }

    private suspend fun <T> getDataToPreferences(key: String, alternate: T): T {

        val dataFlow = dataStore.data
            .catch { throwable ->
                if (throwable is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw throwable
                }
            }
            .map { preferences ->
                preferences[stringPreferencesKey(key)]
            }

        val value = dataFlow.first() ?: return alternate

        val deserialize = gson.fromJson(value, PreferenceModel::class.java)

        return if (deserialize.expirationTime.isExpired()) {
            alternate
        } else deserialize.value as T
    }

    override suspend fun isKeyStored(key: String): Boolean {
        return dataStore.data
            .map { preference ->
                preference.contains(
                    stringPreferencesKey(key)
                )
            }
            .first()
    }

    override suspend fun remove(vararg keys: String) {

        dataStore.edit { preferences ->

            keys.forEach { key ->

                val dataStoreKey = stringPreferencesKey(key)

                if (preferences.contains(dataStoreKey)) {
                    preferences.remove(dataStoreKey)
                }
            }
        }
    }

    override suspend fun clear() {
        dataStore.edit { transform ->
            transform.clear()
        }
    }

    class PreferenceModel<T>(val value: T, val expirationTime: Long? = null)
}