package ir.farsroidx.m31.model

import java.io.Serializable

data class SerializedData<T: Any>(val serialized: T): Serializable
