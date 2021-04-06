package com.live.mytask.api

import com.live.mytask.model.ServerResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {
    companion object{
        val BaseURL="https://run.mocky.io/v3/"
    }
    @GET("c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getTweets(
    ): Response<ServerResponse>
}