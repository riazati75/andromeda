package ir.farsroidx.app.view

import android.util.Log
import ir.farsroidx.app.databinding.ActivityMemoryBinding
import ir.farsroidx.m31.Andromeda
import ir.farsroidx.m31.AndromedaActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MemoryActivity : AndromedaActivity<ActivityMemoryBinding>() {

    override fun onInitialized() {

        binding {

            arrowBack.setOnClickListener {
                onBackPressed()
            }

            CoroutineScope(Andromeda.dispatcher.io).launch {

                Andromeda.memory.store("Key0", "value0")
                Andromeda.memory.store("Key1", 1)
                Andromeda.memory.store("Key2", 2f)
                Andromeda.memory.store("Key3", 3.3)
                Andromeda.memory.store("Key4", 4L)
                Andromeda.memory.store("Key5", false)

                Log.d("CentralCore", "memory0: " + Andromeda.memory.get<String>("Key0"))
                Log.d("CentralCore", "memory1: " + Andromeda.memory.get<Int>("Key1"))
                Log.d("CentralCore", "memory2: " + Andromeda.memory.get<Float>("Key2"))
                Log.d("CentralCore", "memory3: " + Andromeda.memory.get<Double>("Key3"))
                Log.d("CentralCore", "memory4: " + Andromeda.memory.get<Long>("Key4"))
                Log.d("CentralCore", "memory5: " + Andromeda.memory.get<Boolean>("Key5"))

                try {
                    Log.d(
                        "CentralCore",
                        "memory6: " + Andromeda.memory.get<String>("Key6", "Hi, i'm here!")
                    )
                } catch (e: Exception) {
                    Log.d("CentralCore", "Exception (memory6): " + e.message)
                }

//            Providers.preference.store("Key0", "value0")
//            Providers.preference.store("Key1", 1)
//            Providers.preference.store("Key2", 2f)
//            Providers.preference.store("Key3", 3.0)
//            Providers.preference.store("Key4", 4L)
//            Providers.preference.store("Key5", true)
//            Providers.preference.store("Key6", mutableSetOf("2", "4", "6"))
//
//            Log.d("CentralCore", "pref0: " + Providers.preference.get("Key0", "alternate"))
//            Log.d("CentralCore", "pref1: " + Providers.preference.get("Key1", -1))
//            Log.d("CentralCore", "pref2: " + Providers.preference.get("Key2", -1f))
//            Log.d("CentralCore", "pref3: " + Providers.preference.get("Key3", -1.0))
//            Log.d("CentralCore", "pref4: " + Providers.preference.get("Key4", -1L))
//            Log.d("CentralCore", "pref5: " + Providers.preference.get("Key5", false))
//            Log.d("CentralCore", "pref6: " + Providers.preference.get("Key6", mutableSetOf<String>()))
//
//            try {
//                Providers.preference.store("Key7", mutableSetOf(5, 7, 9))
//            } catch (e: Exception) {
//                Log.d("CentralCore", "Exception (pref-store-Key7): " + e.message)
//            }
            }
        }
    }
}