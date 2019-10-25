package com.example.recyclerviewapp.adapter

enum class RecyclerType(val int: Int){
    SECTION(0),
    BODY(1),
    BODY_EXCUSE(2);

    companion object {
        // Intからenumへの変換
        fun fromInt(int: Int): RecyclerType{
            return values().firstOrNull { it.int == int }
                ?: RecyclerType.BODY
        }
    }
}