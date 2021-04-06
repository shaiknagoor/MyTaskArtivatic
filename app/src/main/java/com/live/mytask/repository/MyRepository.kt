package com.live.mytask.repository

import android.content.Context
import com.live.mytask.api.ApiClient

class MyRepository(context: Context) {
    suspend fun getTweets()=
        ApiClient.api.getTweets()

}