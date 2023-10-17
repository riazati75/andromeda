package ir.farsroidx.m31

import ir.farsroidx.m31.additives.koinInjector
import ir.farsroidx.m31.app.App
import ir.farsroidx.m31.cache.Cache
import ir.farsroidx.m31.crypto.Crypto
import ir.farsroidx.m31.database.Database
import ir.farsroidx.m31.dispatcher.Dispatcher
import ir.farsroidx.m31.download.Download
import ir.farsroidx.m31.memory.Memory
import ir.farsroidx.m31.network.Network
import ir.farsroidx.m31.preference.Preference
import ir.farsroidx.m31.utils.Utils

object Andromeda { // Annotation (P)

    val app       : App        by koinInjector()

    val cache     : Cache      by koinInjector()

    val crypto    : Crypto     by koinInjector()

    val database  : Database   by koinInjector()

    val dispatcher: Dispatcher by koinInjector()

    val download  : Download   by koinInjector()

    val memory    : Memory     by koinInjector()

    val network   : Network    by koinInjector()

    val preference: Preference by koinInjector()

    val utils     : Utils      by koinInjector()

}