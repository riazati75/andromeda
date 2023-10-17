package ir.farsroidx.m31.preference

interface Preference {

    suspend fun <T> store(key: String, value: T)

    suspend fun <T> get(key: String, alternate: T): T

    suspend fun remove(vararg keys: String)

    suspend fun clear()

}