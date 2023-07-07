package com.example.memoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.memoapp.databinding.MemoContentBinding

class MemoRecyclerViewAdapter(val dataList : MutableList<Memo>, val mainActivity: MainActivity) : RecyclerView.Adapter<MemoRecyclerViewAdapter.MemoRecyclerViewHolder>() {

    inner class MemoRecyclerViewHolder(private val binding: MemoContentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position : Int){
            binding.title.text = dataList[position].title
            binding.date.text = dataList[position].date

            itemView.setOnClickListener {
                //Toast.makeText(mainActivity.applicationContext, "$position 번째 뷰 선택", Toast.LENGTH_SHORT ).show()
                val intent = Intent(mainActivity.applicationContext, DetailMemo::class.java)
                intent.putExtra("title", dataList[position].title)
                intent.putExtra("content", dataList[position].content)
                intent.putExtra("date", dataList[position].date)
                mainActivity.startIntent(intent)
            }
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