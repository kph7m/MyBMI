package com.example.mybmi


import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.adapter.RecyclerAdapter
import com.example.recyclerviewapp.adapter.RecyclerType
import com.example.recyclerviewapp.model.RecyclerState
import kotlinx.android.synthetic.main.fragment_history.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round


/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    //アプリ開始日　この日付から順に登録データを取得していく
    //TODO SharedPreferenceに初回データ登録日を持たせるべき？
    private val APP_START_DATE = "20190901"
    private val DATE_FORMAT = SimpleDateFormat("yyyyMMdd")
    private val MONTH_FORMAT = SimpleDateFormat("M")//前ゼロなし
    private val DAY_FORMAT = SimpleDateFormat("d")//前ゼロなし

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        setHistory()
//    }

    //履歴情報の書き出し
    public fun setHistory() {
        val states = arrayListOf<RecyclerState>()

        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        val calendar = Calendar.getInstance()
        calendar.time = DATE_FORMAT.parse(APP_START_DATE)
        val today = Calendar.getInstance()
        while (calendar.compareTo(today) < 1) {

            //出力データの読み込み
            val inputHeight = pref.getString(DATE_FORMAT.format(calendar.time) + "Height", "")!!
            if (inputHeight.isNullOrEmpty()) {
                //TODO Loopを抜けるための処理を２回書いてる…。やり方を変えたい
                calendar.add(Calendar.DATE, 1)
                continue
            }
            val inputWeight = pref.getString(DATE_FORMAT.format(calendar.time) + "Weight", "")!!
            val inputExcuse = pref.getString(DATE_FORMAT.format(calendar.time) + "Excuse", "")!!


            //Section
            //TODO　セクション部分は月だけじゃなくて年も入れた方がよいかも
            val month = MONTH_FORMAT.format(calendar.time)
            if (!states.any { it.type == RecyclerType.SECTION && it.month == month }) {
                states.add(RecyclerState(RecyclerType.SECTION, month, "", "", "", "", ""))
            }

            //Body
            val day = DAY_FORMAT.format(calendar.time)
            val bmi =
                (round((inputWeight.toDouble() / (inputHeight.toDouble() / 100) / (inputHeight.toDouble() / 100) * 10)) / 10).toString()
            if (inputExcuse.isNullOrEmpty()) {
                states.add(
                    RecyclerState(
                        RecyclerType.BODY,
                        "",
                        day,
                        inputHeight,
                        inputWeight,
                        bmi,
                        ""
                    )
                )
            } else {
                states.add(
                    RecyclerState(
                        RecyclerType.BODY_EXCUSE,
                        "",
                        day,
                        inputHeight,
                        inputWeight,
                        bmi,
                        inputExcuse
                    )
                )
            }

            calendar.add(Calendar.DATE, 1)
        }

        stateRecyclerView.also { recyclerView: RecyclerView ->
            recyclerView.adapter = RecyclerAdapter(context!!, states)
            recyclerView.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        }
    }

}
