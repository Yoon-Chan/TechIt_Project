package com.example.memoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memoapp.databinding.MemoContentBinding

class MemoRecyclerViewAdapter(val dataList : MutableList<Memo>) : RecyclerView.Adapter<MemoRecyclerViewAdapter.MemoRecyclerViewHolder>() {

    inner class MemoRecyclerViewHolder(private val binding: MemoContentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position : Int){
            binding.title.text = dataList[position].title
            binding.date.text = dataList[position].date
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoRecyclerViewHolder {
        return MemoRecyclerViewHolder(MemoContentBinding.inflate(LayoutInflater.from(parent.context), parent, false ))
    }

    override fun onBindViewHolder(holder: MemoRecyclerViewHolder, position: Int) {
        holder.bind(position)
    }
}