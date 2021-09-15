package com.arsa.smscounter.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageCount(val messageCount: Int): Parcelable {
    fun getMessageOfTheCount():String{
        if(messageCount>0)  return "“${messageCount}” number of messages found" else return "Sorry, no messages found"
    }

}
