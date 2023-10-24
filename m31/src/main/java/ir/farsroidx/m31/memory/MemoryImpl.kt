@file:Suppress("UNCHECKED_CAST")

package ir.farsroidx.m31.memory

import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.additives.isExpired
import ir.farsroidx.m31.additives.toExpirationTime

internal class MemoryImpl(
    private val provider: AndromedaProvider.Memory
) : Memory {

    private val runtimeMemory = mutableMapOf<String, MemoryModel>()

    override fun <T : Any> store(key: String, value: T) {
        runtimeMemory[key] = MemoryModel(
            value, provider.expTime.toExpirationTime(provider.expUnit)
        )
    }

    override fun <T : Any> get(key: String, alternate: T?): T? {
        return if (isKeyStored(key)) {
            return (runtimeMemory[key]!!.value as T)
        } else alternate
    }

    override fun isKeyStored(key: String): Boolean {
        return if (runtimeMemory.containsKey(key)) {
            if (runtimeMemory[key]?.expirationTime.isExpired()) {
                runtimeMemory.remove(key)
                false
            } else true
        } else false
    }

    override fun remove(vararg keys: String) {
        keys.forEach {
            if (runtimeMemory.containsKey(it)) {
                runtimeMemory.remove(
                    it
                )
            }
        }
    }

    override fun clear() = runtimeMemory.clear()

    private data class MemoryModel(val value: Any, val expirationTime: Long? = null)
}