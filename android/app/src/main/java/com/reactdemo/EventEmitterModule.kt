package com.reactdemo

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.reactdemo.EventEmitterModule
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter
import java.util.HashMap

/**
 * Supports sending events to JavaScript.
 */
class EventEmitterModule(reactContext: ReactApplicationContext?) :
    ReactContextBaseJavaModule(reactContext) {

    override fun initialize() {
        super.initialize()
        eventEmitter = reactApplicationContext.getJSModule(
            RCTDeviceEventEmitter::class.java
        )
    }

    /**
     * @return the name of this module. This will be the name used to `require()` this module
     * from JavaScript.
     */
    override fun getName(): String {
        return "EventEmitter"
    }

    override fun getConstants(): Map<String, Any>? {
        val constants: MutableMap<String, Any> = HashMap()
        constants["MyEventName"] = "BookingEvent"
        return constants
    }

    companion object {
        private lateinit var eventEmitter: RCTDeviceEventEmitter

        /**
         * To pass a JavaScript object instead of a simple string, create a [WritableNativeMap] and populate it.
         */
        fun emitEvent(message: String) {
            eventEmitter.emit("BookingEvent", message)
        }
    }
}