package com.arsa.smscounter.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arsa.smscounter.datasource.MessageDataSource
import com.arsa.smscounter.repository.MessageRepository
import kotlinx.coroutines.Dispatchers

class MessageViewModelFactory (private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            val source = MessageDataSource(application.contentResolver)
            MessageViewModel(application, MessageRepository(source, Dispatchers.IO)) as T
        } else
            throw IllegalArgumentException("Unknown ViewModel class")
    }
}