package com.karmakarin.chatu.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class ChatEntity(
    @PrimaryKey
    private var _id: Long = System.currentTimeMillis(),
    var sender: Boolean? = null,
    var content: String? = null,
    var timestamp: Long? = null,
    var gd: String? = null
) : RealmObject {
    constructor() : this(
        _id = System.currentTimeMillis(),
        sender = null,
        content = null,
        timestamp = System.currentTimeMillis(),
        gd = null
    )

    companion object {
        fun ChatEntity.toChat(): CM {
            return CM(
                id = _id,
                sender = this.sender,
                content = this.content,
                timestamp = this.timestamp,
                gd = this.gd
            )
        }

        fun List<ChatEntity>.toChats(): List<CM> {
            return this.map {
                CM(
                    id = it._id,
                    sender = it.sender,
                    content = it.content,
                    timestamp = it.timestamp,
                    gd = it.gd
                )
            }
        }
    }
}