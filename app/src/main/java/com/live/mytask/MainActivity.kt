package com.live.mytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.viewmodel.NewViewModelProviderFactory
import com.live.mytask.databinding.ActivityMainBinding
import com.live.mytask.repository.MyRepository
import com.live.mytask.util.Resource
import com.live.mytask.viewmodel.MyViewmodel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MyViewmodel
    lateinit var tweetAdapter: MyAdapter
    lateinit var binding: ActivityMainBinding
    val TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.v(TAG,"git1")
        val tweetsRepository=MyRepository(this)
        val viewModelProviderFactory= NewViewModelProviderFactory(tweetsRepository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory).get(MyViewmodel::class.java)
        setupRecycler()
        viewModel.tweetList.observe(this, Observer {response->
            when(response){
                is Resource.Success->{
                    // hideProgresBar()
                    response.data?.let { newRes->
                        tweetAdapter.differ.submitList(newRes.rows)
                    }
                }
                is Resource.Error->{
                    response.message?.let {
                        Log.e(TAG,it + "- error occurred-")
                    }
                }
                is Resource.Loading->{
                    //    showProgresBar()
                }

            }
        })
    }
    private fun setupRecycler() {
        tweetAdapter=MyAdapter()
        binding.recylerv.apply {
            adapter=tweetAdapter
            layoutManager= LinearLayoutManager(this@MainActivity)
        }
    }
}