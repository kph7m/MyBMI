package com.example.recyclerviewapp.model

import com.example.recyclerviewapp.adapter.RecyclerType

data class RecyclerState(val type: RecyclerType,
                         val month: String,
                         val itemDate: String,
                         val itemHeight: String,
                         val itemWeight: String,
                         val itemBMI: String,
                         val itemExcuse: String){

}