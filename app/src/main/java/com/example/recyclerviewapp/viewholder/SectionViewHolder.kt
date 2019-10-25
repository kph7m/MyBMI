package com.example.recyclerviewapp.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.R

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val monthTextView: TextView = view.findViewById(R.id.month)
    }
