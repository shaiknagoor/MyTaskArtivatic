package com.androiddevs.mvvmnewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.live.mytask.repository.MyRepository
import com.live.mytask.viewmodel.MyViewmodel

class NewViewModelProviderFactory (val tweetsRepository: MyRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewmodel(tweetsRepository) as T
    }

}