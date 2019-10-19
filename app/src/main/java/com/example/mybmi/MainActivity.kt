package com.example.mybmi

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_input.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class MainActivity : FragmentActivity() {
//inputアイコンは、MITライセンス　：https://icons-for-free.com/user+icon-1320166904740390564/
//履歴アイコンは、CCライセンス　：https://icon-icons.com/ja/%E3%82%A2%E3%82%A4%E3%82%B3%E3%83%B3/%E5%B1%A4/64757

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //INPUT項目の初期値登録
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val inputDate:String = SimpleDateFormat("yyyyMMdd").format(Date())
        inputHeight.setText(pref.getString(inputDate+"Height",""))
        inputWeight.setText(pref.getString(inputDate+"Weight",""))
        inputExcuse.setText(pref.getString(inputDate+"Excuse",""))
        //yourBMI.setText((round((weight/(height/100)/(height/100)*10))/10).toString())

        //ボタン処理追加
        buttonCalculateBMI.setOnClickListener { calculateBMI() }
        buttonSave.setOnClickListener { save() }
    }

    //BMIの計算・保存処理
    private fun calculateBMI() {
        val height:Double = inputHeight.text.toString().toDouble()
        val weight:Double = inputWeight.text.toString().toDouble()
        this.yourBMI.text = (round((weight/(height/100)/(height/100)*10))/10).toString()

        save()
    }

    //保存処理
    private fun save(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        pref.edit {
            val inputDate:String = SimpleDateFormat("yyyyMMdd").format(Date())
            putString(inputDate+"Height",inputHeight.text.toString())
            putString(inputDate+"Weight",inputWeight.text.toString())
            putString(inputDate+"Excuse",inputExcuse.text.toString())
        }
    }
}
