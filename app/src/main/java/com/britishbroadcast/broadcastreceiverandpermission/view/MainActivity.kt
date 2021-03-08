package com.britishbroadcast.broadcastreceiverandpermission.view

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.britishbroadcast.broadcastreceiverandpermission.R
import com.britishbroadcast.broadcastreceiverandpermission.WeatherCondition
import com.britishbroadcast.broadcastreceiverandpermission.receiver.AirPlaneModeReceiver
import com.britishbroadcast.broadcastreceiverandpermission.util.Logger.Companion.logDebug
import com.britishbroadcast.broadcastreceiverandpermission.util.Weather

class MainActivity : AppCompatActivity() {

    private val airReceiver = AirPlaneModeReceiver()

    private val CUSTOM_FILTER = "106.8"

    private val customReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if(action == CUSTOM_FILTER){
                val weatherCondition = intent.getParcelableExtra<WeatherCondition>(CUSTOM_FILTER)
                logDebug(weatherCondition.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(airReceiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))

        //turn radio on
        registerReceiver(customReceiver, IntentFilter(CUSTOM_FILTER))

        //from radio station
        sendBroadcast(Intent(CUSTOM_FILTER).also {
            it.putExtra(CUSTOM_FILTER, WeatherCondition("03/08/21", Weather.SUNNY))
        } )

        //Requesting Runtime permissions
        //1st Step: Check if permission is enabled
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //Permission is already granted - continue with application flow
        } else {
            //2nd Step: If permission is denied, request permissions
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 707)
        }
    }

    //3rd Step: Override OnRequestPermissionResult -> activity
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show()

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airReceiver)
        unregisterReceiver(customReceiver)
    }

}