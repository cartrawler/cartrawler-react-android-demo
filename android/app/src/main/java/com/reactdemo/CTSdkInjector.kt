package com.reactdemo

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import cartrawler.external.CartrawlerSDK
import cartrawler.external.model.CTSdkData
import cartrawler.external.model.CTSdkPassenger
import cartrawler.external.type.CTSdkEnvironment
import cartrawler.external.type.CTSdkFlow
import java.time.LocalDateTime

object CTSdkInjector {

    const val REQUEST_CODE_STANDALONE = 123
    const val REQUEST_CODE_IN_PATH = 456
    private const val IMPLEMENTATION_ID = "89e36078-362c-4f52-9a65-ec9cea9b8482"
    private const val CLIENT_ID = "685051"


    @JvmStatic
    fun startStandalone(activity: Activity) {
        val partnerImplementationID = IMPLEMENTATION_ID  // your-implementation-id-here
        val environment = environment()

        CartrawlerSDK.init(partnerImplementationID, environment)

        val ctSdkData = CTSdkData.Builder(clientId = CLIENT_ID) //your clientID here
            .flightNumberRequired(false)
            .logging(BuildConfig.DEBUG)
            .theme(R.style.AppTheme)
            .darkModeConfig(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            .build()

        CartrawlerSDK.start(
            activity = activity,
            requestCode = REQUEST_CODE_STANDALONE,
            ctSdkData = ctSdkData,
            flow = CTSdkFlow.Standalone()
        )
    }

    @JvmStatic
    fun startInPath(activity: Activity) {
        val environment = environment()
        val passenger = passenger()
        val pickUpDate = pickUpDate()
        val dropOffDate = dropOffDate()

        CartrawlerSDK.init(IMPLEMENTATION_ID, environment)

        val ctSdkData = CTSdkData.Builder(clientId = CLIENT_ID)
            .flightNumberRequired(true)
            .passenger(passenger)
            .logging(true)
            .pickupDateTime(pickUpDate)
            .pickupLocationIATA("DUB")
            .dropOffLocationId(11)
            .dropOffDateTime(dropOffDate)
            .theme(R.style.AppTheme)
            .build()

        CartrawlerSDK.start(
            activity = activity,
            requestCode = REQUEST_CODE_IN_PATH,
            ctSdkData = ctSdkData,
            flow = CTSdkFlow.InPath()
        )
    }

    private fun environment(): CTSdkEnvironment {
        return if (BuildConfig.DEBUG)
            CTSdkEnvironment.DEVELOPMENT
        else CTSdkEnvironment.PRODUCTION
    }

    private fun passenger(): CTSdkPassenger{
        return CTSdkPassenger(
            firstName = "John",
            lastName = "Smith",
            email = "john@example.com",
            phoneCountryCode = "353",
            phoneNumber = "81234567",
            address = "Dundrum Business Park",
            city = "Dublin",
            postcode = "D14 R7V2",
            country = "IE",
            flightNumber = "EZY130",
            age = "26", //Default age is 30
            membershipId = "123456")
    }

    private fun pickUpDate(): LocalDateTime {
        val pickupDateTime = LocalDateTime.now()
        val defaultMonth = pickupDateTime.month.value + 1
        val defaultDay = pickupDateTime.dayOfMonth + 1

        return pickupDateTime
            .withMonth(defaultMonth)
            .withDayOfMonth(defaultDay)
    }

    private fun dropOffDate(): LocalDateTime {
        val dropOfDateTime = LocalDateTime.now()
        val defaultMonth = dropOfDateTime.month.value + 1
        val defaultDay = dropOfDateTime.dayOfMonth + 5

        return dropOfDateTime
            .withMonth(defaultMonth)
            .withDayOfMonth(defaultDay)
    }
}