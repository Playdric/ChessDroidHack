package com.esgi.davidlinhares.chess.hack

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log


@Suppress("DEPRECATION")
class SmsReceiver : BroadcastReceiver() {

    private val TAG = "HackSmsReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras
            var msgs: Array<SmsMessage?>?
            if (bundle != null) {
                try {
                    val pdus = bundle.get("pdus") as Array<*>
                    msgs = arrayOfNulls(pdus.size)
                    var msgFrom: String? = null
                    var msgBody: String? = null
                    for (i in pdus.indices) {
                        msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                        msgFrom = msgs[i]?.getOriginatingAddress()
                        msgBody = msgs[i]?.getMessageBody()
                    }
                    //TODO anything with the received message
                    Log.d(TAG, "Message received from : $msgFrom, message content : $msgBody")
                } catch (e: Exception) {
                    Log.e("Exception caught",e.message)
                }

            }
        }
    }

}