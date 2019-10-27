package com.example.recyclerviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.R
import com.example.recyclerviewapp.model.RecyclerState
import com.example.recyclerviewapp.viewholder.BodyExcuseViewHolder
import com.example.recyclerviewapp.viewholder.BodyViewHolder
import com.example.recyclerviewapp.viewholder.SectionViewHolder

class RecyclerAdapter(
    private val context: Context,
    private val states: List<RecyclerState>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(RecyclerType.fromInt(viewType)){
            RecyclerType.SECTION -> {
                val view = LayoutInflater.from(context).inflate(R.layout.list_section, parent, false)
                SectionViewHolder(view)
            }
            RecyclerType.BODY -> {
                val view = LayoutInflater.from(context).inflate(R.layout.list_body, parent, false)
                BodyViewHolder(view)
            }
            //TODO　検討中：RecyclerType.BODY_EXCUSEじゃなくてitemExcuseがブランクかどうかで判断した方が良い？  RecyclerTypeで判断できるのはそれはそれでメリットな気もするけど…。
            RecyclerType.BODY_EXCUSE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.list_body_excuse, parent, false)
                BodyExcuseViewHolder(view)
            }
            //存在しないケース
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.list_body, parent, false)
                BodyViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when(viewHolder){
            is SectionViewHolder ->{
                viewHolder.monthTextView.text = states[position].month
            }
            is BodyViewHolder ->{
                viewHolder.itemDateTextView.text = states[position].itemDate
                viewHolder.itemHeightTextView.text = states[position].itemHeight
                viewHolder.itemWeightTextView.text = states[position].itemWeight
                viewHolder.itemBMITextView.text = states[position].itemBMI
            }
            is BodyExcuseViewHolder ->{
                viewHolder.itemDateTextView.text = states[position].itemDate
                viewHolder.itemHeightTextView.text = states[position].itemHeight
                viewHolder.itemWeightTextView.text = states[position].itemWeight
                viewHolder.itemBMITextView.text = states[position].itemBMI
                viewHolder.itemExcuseTextView.text = states[position].itemExcuse
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return states[position].type.int
    }

    override fun getItemCount(): Int {
        return states.count()
    }
}
