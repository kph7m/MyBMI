package com.example.recyclerviewapp.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.R

    class BodyExcuseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemDateTextView: TextView = view.findViewById(R.id.itemDate)
        val itemHeightTextView: TextView = view.findViewById(R.id.itemHeight)
        val itemWeightTextView: TextView = view.findViewById(R.id.itemWeight)
        val itemBMITextView: TextView = view.findViewById(R.id.itemBMI)
        val itemExcuseTextView: TextView = view.findViewById(R.id.itemExcuse)
    }
