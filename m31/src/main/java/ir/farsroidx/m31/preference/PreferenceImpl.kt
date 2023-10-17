package ir.farsroidx.m31.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import ir.farsroidx.m31.AndromedaException
import ir.farsroidx.m31.additives.isCasted
import ir.farsroidx.m31.additives.isExpired
import ir.farsroidx.m31.additives.toExpirationTime
import kotlinx.coroutines.flow.first

internal class PreferenceImpl constructor(
    context: Context, private val gson: Gson, private val config: PreferenceConfig
) : Preference {

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(
        name = config.preferenceName
    )

    private val dataStore: DataStore<Preferences> = context._dataStore

    override suspend fun <T> store(key: String, value: T) {
        dataStore.edit { transform ->
            transform[
                getStringPreferenceKey(
                    key
                )
            ] = gson.toJson(
                PreferenceModel(
                    value, getType(value),
                    config.expirationTime.toExpirationTime(
                        config.expirationUnit
                    )
                )
            )
        }
    }

    override suspend fun <T> get(key: String, alternate: T): T {

        val preferences = dataStore.data.first()

        val value = preferences[getStringPreferenceKey(key)] ?: return alternate

        val deserialize = gson.fromJson(value, PreferenceModel::class.java)

        if (deserialize.expirationTime.isExpired()) return alternate

        return alternate
    }

    override suspend fun remove(vararg keys: String) {

        keys.forEach { key ->

            dataStore.edit { transform ->

                var dataStoreKey: Preferences.Key<*>? = null

                transform.asMap().forEach {
                    if (it.key.name == key) {
                        dataStoreKey = it.key
                    }
                }

                if (dataStoreKey != null) {
                    transform.remove(dataStoreKey!!)
                }
            }
        }
    }

    override suspend fun clear() {
        dataStore.edit { transform ->
            transform.clear()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getPreferenceKey(key: String, typeHolder: T): Preferences.Key<T> {

        val generatedKey: Preferences.Key<*>

        when {
            typeHolder is String -> {
                generatedKey = stringPreferencesKey(key)
            }

            typeHolder is Int -> {
                generatedKey = intPreferencesKey(key)
            }

            typeHolder is Float -> {
                generatedKey = floatPreferencesKey(key)
            }

            typeHolder is Double -> {
                generatedKey = doublePreferencesKey(key)
            }

            typeHolder is Long -> {
                generatedKey = longPreferencesKey(key)
            }

            typeHolder is Boolean -> {
                generatedKey = booleanPreferencesKey(key)
            }

            isCasted<Set<String>>(typeHolder) -> {
                generatedKey = stringSetPreferencesKey(key)
            }

            else -> {
                throw AndromedaException(
                    "Value type is not supported. Use only types [String,Int,Float,Double,Long,Boolean,Set<String>]."
                )
            }
        }

        return (generatedKey as Preferences.Key<T>)
    }

    private fun getStringPreferenceKey(key: String): Preferences.Key<String> {
        return stringPreferencesKey(key)
    }

    private fun <T> getType(value: T): String {
        return when {
            value is String -> {
                "string"
            }

            value is Int -> {
                "int"
            }

            value is Float -> {
                "float"
            }

            value is Double -> {
                "double"
            }

            value is Long -> {
                "long"
            }

            value is Boolean -> {
                "boolean"
            }

            isCasted<Set<String>>(value) -> {
                "set"
            }

            else -> {
                throw AndromedaException(
                    "Value type is not supported. Use only types [String,Int,Float,Double,Long,Boolean,Set<String>]."
                )
            }
        }
    }
}