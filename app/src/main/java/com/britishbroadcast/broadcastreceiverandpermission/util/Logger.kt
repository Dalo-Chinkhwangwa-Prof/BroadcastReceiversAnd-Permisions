package com.britishbroadcast.broadcastreceiverandpermission.util

import android.util.Log

class Logger {

    companion object{

        const val TAG = "TAG_X"

        fun logDebug(message: String){
            Log.d(TAG, message)
        }

        fun logError(message: String){
            Log.e(TAG, message)
        }
    }
}