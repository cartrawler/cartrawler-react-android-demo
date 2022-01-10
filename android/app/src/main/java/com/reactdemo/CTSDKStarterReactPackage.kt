package com.reactdemo

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.NativeModule
import com.facebook.react.uimanager.ViewManager
import java.util.ArrayList

/**
 * Exposes [ActivityStarterModule] and [EventEmitterModule]  to JavaScript.
 * One [ReactPackage] can expose any number of [NativeModule]s.
 */
internal class CTSDKStarterReactPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        val modules: MutableList<NativeModule> = ArrayList()
        modules.add(CTSDKStarterModule(reactContext))
        modules.add(EventEmitterModule(reactContext))
        return modules
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}