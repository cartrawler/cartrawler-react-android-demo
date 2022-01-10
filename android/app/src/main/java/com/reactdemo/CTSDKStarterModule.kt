package com.reactdemo

import android.content.Intent
import com.facebook.react.bridge.*

/**
 * Expose Java to JavaScript. Methods annotated with [ReactMethod] are exposed.
 */
class CTSDKStarterModule(reactContext: ReactApplicationContext?) :
    ReactContextBaseJavaModule(reactContext) {

    /**
     * @return the name of this module. This will be the name used to `require()` this module
     * from JavaScript.
     */
    override fun getName(): String {
        return "CTSDKStarterModule"
    }

    @ReactMethod
    fun startBookingFlow(inPath: Boolean = false) {
        val activity = currentActivity
        if (activity != null) {
            val intent = Intent(activity, CTFlowActivity::class.java)
            intent.putExtra("IN-PATH", inPath)
            activity.startActivity(intent)
        }
    }

    @ReactMethod
    fun finishBookingFlow() {
        val activity = currentActivity
        activity?.finish()
    }
}