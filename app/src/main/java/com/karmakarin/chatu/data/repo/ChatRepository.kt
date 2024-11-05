package com.karmakarin.chatu.data.repo

import com.karmakarin.chatu.data.model.CM
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChats(): Flow<List<CM>?>
    suspend fun insertChat(chat: ArrayList<CM>)
    suspend fun insertChat(chat: CM)
    suspend fun deleteChat(chat: CM)
    suspend fun updateChat(chat: CM)
    suspend fun clearAllChat()
}