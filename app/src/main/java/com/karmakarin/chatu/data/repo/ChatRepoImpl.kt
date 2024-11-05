package com.karmakarin.chatu.data.repo

import com.karmakarin.chatu.data.model.CM
import com.karmakarin.chatu.data.model.ChatEntity
import com.karmakarin.chatu.data.model.ChatEntity.Companion.toChats
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random

class ChatRepoImpl @Inject constructor(private val realm: Realm) : ChatRepository {

    override fun getChats(): Flow<List<CM>> {
        return realm.query<ChatEntity>().find().asFlow().map {
            it.list.toChats()
        }
    }

    override suspend fun insertChat(chat: CM) {
        realm.write {
            copyToRealm(
                ChatEntity(
                    _id = chat.id,
                    sender = chat.sender,
                    content = chat.content,
                    timestamp = chat.timestamp,
                    gd = chat.gd
                )
            )
        }
    }

    override suspend fun insertChat(chats: ArrayList<CM>) {
        chats.map {
            realm.write {
                copyToRealm(
                    ChatEntity(
                        _id = it.id,
                        sender = it.sender,
                        content = it.content,
                        timestamp = it.timestamp,
                        gd = it.gd
                    )
                )
            }
        }
    }

    override suspend fun deleteChat(chat: CM) {
        realm.write {
            val noteEntity = realm.query<ChatEntity>("_id == ${chat.id}", chat.id).find().first()

            findLatest(noteEntity).also {
                it?.let { delete(it) }
            }
        }
    }

    override suspend fun updateChat(chat: CM) {
        realm.write {
            val chatEntity = realm.query<ChatEntity>("_id == ${chat.id}", chat.id).find().first()

            findLatest(chatEntity).also {
                it?.let { it.content = Random.nextInt(1000).toString() }
            }
        }
    }

    override suspend fun clearAllChat() {
        realm.write {
            val chatEntity = query<ChatEntity>().find()

            delete(chatEntity)
        }
    }

}