package com.karmakarin.chatu.data.model



data class CM(
    val id: Long,
    val sender: Boolean? = null,
    val content: String? = null,
    val timestamp: Long? = null,
    val gd: String? = null
)