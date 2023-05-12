package com.reactdemo

import android.content.Intent
import com.facebook.react.ReactActivity
import androidx.annotation.CallSuper
import android.os.Bundle
import android.view.View
import cartrawler.core.data.external.ReservationDetails
import cartrawler.external.CartrawlerSDK
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.reactdemo.CTSdkInjector.REQUEST_CODE_IN_PATH
import com.reactdemo.CTSdkInjector.REQUEST_CODE_STANDALONE
import com.reactdemo.CTSdkInjector.startInPath
import com.reactdemo.CTSdkInjector.startStandalone



class CTFlowActivity : ReactActivity() {

    private var reservation = ""

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.ctflow_activity)

        val inPath: Boolean? = this.intent.extras?.getBoolean("IN-PATH")
        init(inPath)
    }

    private fun init(inPath: Boolean?){
        if (inPath == true) {
            startInPath(this@CTFlowActivity)
        } else {
            startStandalone(this@CTFlowActivity)
        }

        findViewById<View>(R.id.call_javascript_button).setOnClickListener{
            sendBookingResult()
        }

        findViewById<View>(R.id.go_back_button).setOnClickListener { onBackPressed() }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val gson = GsonBuilder().setPrettyPrinting().create()


        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_STANDALONE) {
            val reservationExtra =
                data?.getParcelableExtra<ReservationDetails>(CartrawlerSDK.RESERVATION_DETAILS)
            if (reservationExtra != null) {
                reservation = gson.toJson(reservationExtra)
                sendBookingResult()
            }
        }else if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_IN_PATH){
            val sampleData = data?.getStringExtra(CartrawlerSDK.PAYLOAD)
            if (sampleData != null) {
                val jsonElement = gson.fromJson(sampleData, JsonElement::class.java)
                reservation = gson.toJson(jsonElement)

                sendBookingResult()
            }
        }else if(resultCode == RESULT_CANCELED){
            this@CTFlowActivity.finish()
        }


    }

    private fun sendBookingResult(){
        EventEmitterModule.emitEvent(reservation)
    }

}