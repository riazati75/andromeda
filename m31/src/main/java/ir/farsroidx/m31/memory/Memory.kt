package ir.farsroidx.m31.memory

interface Memory {

    fun <T : Any> store(key: String, value: T)

    fun <T : Any> get(key: String, alternate: T? = null): T?

    fun isKeyStored(key: String): Boolean

    fun remove(vararg keys: String)

    fun clear()

}