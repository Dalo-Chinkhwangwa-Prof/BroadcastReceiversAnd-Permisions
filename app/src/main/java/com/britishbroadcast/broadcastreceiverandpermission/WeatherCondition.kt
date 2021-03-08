package com.britishbroadcast.broadcastreceiverandpermission

import android.os.Parcelable
import com.britishbroadcast.broadcastreceiverandpermission.util.Weather
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherCondition(var date: String, var weatherCondition: Weather): Parcelable