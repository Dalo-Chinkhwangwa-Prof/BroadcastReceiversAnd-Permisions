package com.britishbroadcast.broadcastreceiverandpermission.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.britishbroadcast.broadcastreceiverandpermission.util.Logger.Companion.logDebug
import com.britishbroadcast.broadcastreceiverandpermission.util.Logger.Companion.logError

class BootUpReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action

        if(action == "android.intent.action.BOOT_COMPLETED") {
            logDebug("Boot up completed!")
            Toast.makeText(context, "Boot up completed!", Toast.LENGTH_SHORT).show()
        }
         else
            logError("Not boot up completed.")
    }
}