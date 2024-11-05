package com.karmakarin.chatu.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karmakarin.chatu.data.model.CM
import com.karmakarin.chatu.data.repo.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val chatDefault: ArrayList<CM>?
) : ViewModel() {

    val _chatList = MutableLiveData<ArrayList<CM>?>()
    val chatList: LiveData<ArrayList<CM>?> get() = _chatList

    init {
        _chatList.value = ArrayList()
        chatRefresh()
    }

    val chat = chatRepository.getChats()

    fun insertChat(chats: ArrayList<CM>) = viewModelScope.launch(Dispatchers.IO) {
        chatRepository.insertChat(chats)
    }

    fun insertChat(chats: CM) = viewModelScope.launch(Dispatchers.IO) {
        chatRepository.insertChat(chats)
    }

    fun deleteChat(chat: CM) = viewModelScope.launch(Dispatchers.IO) {
        chatRepository.deleteChat(chat)
    }

    fun updateChat(chat: CM) = viewModelScope.launch(Dispatchers.IO) {
        chatRepository.updateChat(chat)
    }

    fun clearAllChats() = viewModelScope.launch(Dispatchers.IO) {
        chatRepository.clearAllChat()
    }

    private fun chatRefresh() {
        try {
            viewModelScope.launch {
                chatRepository.getChats().collect { cmList ->
                    _chatList.value = if (!cmList.isNullOrEmpty()) {
                        cmList as ArrayList<CM>
                    } else {
                        chatDefault?.let { insertChat(it) }
                        chatDefault
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}