package com.live.mytask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.live.mytask.model.ServerResponse
import com.live.mytask.repository.MyRepository
import com.live.mytask.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewmodel(val repository: MyRepository) :ViewModel() {
    val tweetList: MutableLiveData<Resource<ServerResponse>> = MutableLiveData()
    init {
        getTweets()
    }
   fun getTweets(){
       viewModelScope.launch {
           val response=repository.getTweets()
           tweetList.postValue(handleTweetsResponse(response))

       }
   }
    private fun handleTweetsResponse(response: Response<ServerResponse>): Resource<ServerResponse>? {
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())

    }
}