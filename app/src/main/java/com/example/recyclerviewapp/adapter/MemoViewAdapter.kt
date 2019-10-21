package com.example.recyclerviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.R
import com.example.recyclerviewapp.model.Memo

class MemoViewAdapter(private val context: Context, private val memoList: List<Memo>) :
    RecyclerView.Adapter<MemoViewAdapter.MemoViewHolder>() {

    class MemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemDateTextView: TextView = view.findViewById(R.id.itemDate)
        val itemHeightTextView: TextView = view.findViewById(R.id.itemHeight)
        val itemWeightTextView: TextView = view.findViewById(R.id.itemWeight)
        val itemBMITextView: TextView = view.findViewById(R.id.itemBMI)
        val itemExcuseTextView: TextView = view.findViewById(R.id.itemExcuse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder =
        MemoViewHolder(LayoutInflater.from(context).inflate(R.layout.list_memo, parent, false))

    override fun getItemCount(): Int = memoList.size

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.itemDateTextView.text = memoList[position].itemDate
        holder.itemHeightTextView.text = memoList[position].itemHeight
        holder.itemWeightTextView.text = memoList[position].itemWeight
        holder.itemBMITextView.text = memoList[position].itemBMI
        holder.itemExcuseTextView.text = memoList[position].itemExcuse
    }
}