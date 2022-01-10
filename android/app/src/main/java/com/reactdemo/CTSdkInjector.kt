package com.reactdemo

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import cartrawler.core.engine.CartrawlerSDK
import cartrawler.core.engine.CartrawlerSDKPassenger
import java.util.*

object CTSdkInjector {

    const val REQUEST_CODE_STANDALONE = 123
    const val REQUEST_CODE_IN_PATH = 456

    @JvmStatic
    fun startStandalone(activity: Activity) {
        val environment = environment()

        CartrawlerSDK.Builder()
            .setRentalStandAloneClientId("685051")
            .setEnvironment(environment)
            .setFlightNumberRequired(false)
            .setLogging(BuildConfig.DEBUG)
            .setTheme(R.style.AppTheme)
            .setDarkModeConfig(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            .startRentalStandalone(activity, REQUEST_CODE_STANDALONE)
    }

    @JvmStatic
    fun startInPath(activity: Activity) {
        val environment = environment()
        val passenger = passenger()
        val pickUpDate = pickUpDate()
        val dropOffDate = dropOffDate()

        CartrawlerSDK.Builder()
            .setRentalInPathClientId("685051")
            .setEnvironment(environment)
            .setFlightNumberRequired(true)
            .setPassenger(passenger)
            .setAccountId("123")
            .setLogging(true)
            .setPickupTime(pickUpDate)
            .setPickupLocation("DUB")
            .setDropOffLocationId(11)
            .setDropOffTime(dropOffDate)
            .setTheme(R.style.AppTheme)
            .startRentalInPath(activity, REQUEST_CODE_IN_PATH)
    }


    private fun environment(): String {
        return if (BuildConfig.DEBUG)
            CartrawlerSDK.Environment.STAGING
        else CartrawlerSDK.Environment.PRODUCTION
    }

    private fun passenger(): CartrawlerSDKPassenger{
        return CartrawlerSDKPassenger(
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

    private fun pickUpDate(): GregorianCalendar {
        val pickupDateTime = GregorianCalendar.getInstance() as GregorianCalendar
        val defaultMonth = pickupDateTime.get(Calendar.MONTH) + 1
        val defaultDay = pickupDateTime.get(Calendar.DAY_OF_MONTH) + 1

        pickupDateTime.set(GregorianCalendar.MONTH, defaultMonth)
        pickupDateTime.set(GregorianCalendar.DAY_OF_MONTH, defaultDay)
        return pickupDateTime
    }

    private fun dropOffDate(): GregorianCalendar {
        val dropOfDateTime = GregorianCalendar.getInstance() as GregorianCalendar
        val defaultMonth = dropOfDateTime.get(Calendar.MONTH) + 1
        val defaultDay = dropOfDateTime.get(Calendar.DAY_OF_MONTH) + 1

        dropOfDateTime.set(GregorianCalendar.MONTH, defaultMonth)
        dropOfDateTime.set(GregorianCalendar.DAY_OF_MONTH, defaultDay)
        dropOfDateTime.add(GregorianCalendar.DAY_OF_MONTH, 5)
        return dropOfDateTime
    }
}