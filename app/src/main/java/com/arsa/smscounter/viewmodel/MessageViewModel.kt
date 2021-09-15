package com.arsa.smscounter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.arsa.smscounter.repository.MessageRepository

class MessageViewModel (context: Application,
                        private val messageRepository: MessageRepository) : AndroidViewModel(context) {

    fun messageCount(phoneNumber:String,days:Int): LiveData<Int> = liveData {
        emit(messageRepository.getMessageCounts(phoneNumber,days))
    }



}