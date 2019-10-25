package com.example.recyclerviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.R
import com.example.recyclerviewapp.model.Memo
import com.example.recyclerviewapp.viewholder.BodyViewHolder

class MemoViewAdapter(private val context: Context, private val memoList: List<Memo>) :
    RecyclerView.Adapter<BodyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyViewHolder =
        BodyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_body, parent, false))

    override fun getItemCount(): Int = memoList.size

    override fun onBindViewHolder(holder: BodyViewHolder, position: Int) {
        holder.itemDateTextView.text = memoList[position].itemDate
        holder.itemHeightTextView.text = memoList[position].itemHeight
        holder.itemWeightTextView.text = memoList[position].itemWeight
        holder.itemBMITextView.text = memoList[position].itemBMI
        holder.itemExcuseTextView.text = memoList[position].itemExcuse
    }
}