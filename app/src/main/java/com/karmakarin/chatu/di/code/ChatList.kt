package com.karmakarin.chatu.di.code

import android.content.Context
import com.karmakarin.chatu.data.model.CM
import com.karmakarin.chatu.utils.Util
import com.karmakarin.chatu.utils.fromJson
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatList {

    @Provides
    @Singleton
    fun providerChatCustomList(@ApplicationContext context: Context): ArrayList<CM>? {
        return getList(context)
    }

    private fun getList(context: Context): ArrayList<CM>? {
        try {
            return Gson().fromJson(Util.loadJSONFromAsset(context, "chat.json"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList()
    }
}