package com.arsa.smscounter.repository

import androidx.lifecycle.LiveData
import com.arsa.smscounter.datasource.MessageDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MessageRepository(private val source: MessageDataSource, private val dispatcher: CoroutineDispatcher) {
    lateinit var job:Job
    suspend fun getMessageCounts(phoneNumber: String, days: Int):Int{

        job = Job()
        val i = CoroutineScope(dispatcher+job).async {
            return@async source.getCountOfTheNumberWithDays(phoneNumber,days)
        }
        return i.await()
    }

    fun cancelJob(){
        if(job.isActive){
            job.cancel()
        }
    }

}