package com.live.mytask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.live.mytask.databinding.CustomRowBinding
import com.live.mytask.model.Row


class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding : CustomRowBinding):RecyclerView.ViewHolder(binding.root) {
    }
    private val differCallBack=object : DiffUtil.ItemCallback<Row>(){
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
           return oldItem.imageHref==newItem.imageHref
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=CustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
     //   return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val tweet= differ.currentList.get(position)

    with(tweet){
        with(holder.binding){
        Glide.with(holder.itemView.context)
                .load(imageHref)
                .into(imageView)
        titleTv.text=title
            descripTv.text=description
    }

        }

    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
}