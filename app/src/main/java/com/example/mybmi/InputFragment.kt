package com.example.mybmi


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import kotlinx.android.synthetic.main.fragment_input.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

/**
 * A simple [Fragment] subclass.
 */
class InputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //INPUT項目の初期値登録
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val inputDate:String = SimpleDateFormat("yyyyMMdd").format(Date())
        inputHeight.setText(pref.getString(inputDate+"Height",""))
        inputWeight.setText(pref.getString(inputDate+"Weight",""))
        inputExcuse.setText(pref.getString(inputDate+"Excuse",""))
        //yourBMI.setText((round((weight/(height/100)/(height/100)*10))/10).toString())

        //ボタン押下時の処理追加
        buttonCalculateBMI.setOnClickListener { calculateBMI() }
        buttonSave.setOnClickListener { save() }
    }

    //BMIの計算・保存処理
    private fun calculateBMI() {
        val height:Double = inputHeight.text.toString().toDouble()
        val weight:Double = inputWeight.text.toString().toDouble()
        this.yourBMI.text = (round((weight/(height/100)/(height/100)*10)) /10).toString()

        save()
    }

    //保存処理
    private fun save(){
        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        pref.edit {
            val inputDate:String = SimpleDateFormat("yyyyMMdd").format(Date())
            putString(inputDate+"Height",inputHeight.text.toString())
            putString(inputDate+"Weight",inputWeight.text.toString())
            putString(inputDate+"Excuse",inputExcuse.text.toString())
        }
    }
}
