package com.example.mybmi


import android.app.AlertDialog
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
        if(!pref.getString(inputDate+"Height","").isNullOrEmpty()) {
            this.yourBMI.text = calculateBMI()
        }

        //ボタン押下時の処理追加
        buttonCalculateBMI.setOnClickListener { this.yourBMI.text = calculateBMI() }
        buttonSave.setOnClickListener { save() }
        buttonDelete.setOnClickListener { delete() }
    }

    //BMIの計算
    private fun calculateBMI():String {
        var bmi = ""
        try {
            val height: Double = inputHeight.text.toString().toDouble()
            val weight: Double = inputWeight.text.toString().toDouble()
            if(numberDecimalPlaces(height)>1 || numberDecimalPlaces(weight)>1 ){
                AlertDialog.Builder(context)
                    .setMessage(getString(R.string.alert_numberDecimalPlaces))
                    .show()
                return bmi
            }
            bmi = (round((weight / (height / 100) / (height / 100) * 10)) / 10).toString()
        }catch(e:Exception){
            AlertDialog.Builder(context)
                .setMessage(getString(R.string.alert_calculateBMI))
                .show()
        }
        return bmi
    }

    //小数点以下の文字数取得
    private fun numberDecimalPlaces(double:Double):Int{
        val str = double.toString()
        if(!str.contains(".")){
            return 0
        }
        return str.split(".")[1].length
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

    //削除処理
    private fun delete(){
        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        pref.edit {
            val inputDate:String = SimpleDateFormat("yyyyMMdd").format(Date())
            remove(inputDate+"Height")
            remove(inputDate+"Weight")
            remove(inputDate+"Excuse")
        }

        inputHeight.setText("")
        inputWeight.setText("")
        inputExcuse.setText("")
    }
}
