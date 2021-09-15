package com.arsa.smscounter.datasource

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import java.util.*

class MessageDataSource(private val contentResolver:ContentResolver) {

    fun getCountOfTheNumberWithDays(phoneNumber: String, days: Int):Int{

        var calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        val fromDate:Long = calendar.time.time
        val toDate:Long = Date().time

        val uri: Uri = Uri.parse("content://sms/inbox")
        val selectionQuery =
            Telephony.Sms.ADDRESS + " = '$phoneNumber' AND ("+ Telephony.Sms.DATE + " BETWEEN  "+fromDate +" AND "+toDate+")"
        Log.e("Query", selectionQuery)

        val cursor: Cursor? = contentResolver.query(
            uri, null, selectionQuery,
            null, null
        )

        Log.e("Log", cursor!!.count.toString())
        return cursor!!.count
    }
}