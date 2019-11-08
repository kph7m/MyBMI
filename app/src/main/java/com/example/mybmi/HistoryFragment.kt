package com.example.mybmi


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.adapter.RecyclerAdapter
import com.example.recyclerviewapp.adapter.RecyclerType
import com.example.recyclerviewapp.model.RecyclerState
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.round


/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHistory()
    }

    //履歴情報の書き出し
    private fun setHistory() {
        val states = arrayListOf<RecyclerState>()

        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        val calendar = Calendar.getInstance()
        calendar.time = DATE_FORMAT.parse(APP_START_DATE)!!
        val today = Calendar.getInstance()
        while (calendar.compareTo(today) < 1) {

            //出力データの読み込み
            val inputHeight= pref.getString(DATE_FORMAT.format(calendar.time) + "Height", "")!!
            if (inputHeight.isEmpty()) {
                calendar.add(Calendar.DATE, 1)
                continue
            }
            val inputWeight = pref.getString(DATE_FORMAT.format(calendar.time) + "Weight", "")!!
            val inputExcuse = pref.getString(DATE_FORMAT.format(calendar.time) + "Excuse", "")!!

            //Section部の作成
            createSection(calendar, states)?.let{
                states.add(it)
            }

            //Body部の作成
            states.add(createBody(calendar, inputWeight, inputHeight, inputExcuse))

            calendar.add(Calendar.DATE, 1)
        }

        stateRecyclerView.also { recyclerView: RecyclerView ->
            recyclerView.adapter = RecyclerAdapter(context!!, states)
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    /**
     * Section部の作成
     */
    private fun createSection(
        calendar: Calendar,
        states: ArrayList<RecyclerState>
    ):RecyclerState? {
        //TODO　セクション部分は月だけじゃなくて年も入れた方がよいかも
        val month = MONTH_FORMAT.format(calendar.time)
        if (!states.any { it.type == RecyclerType.SECTION && it.month == month }) {
            return RecyclerState(RecyclerType.SECTION, month, "", "", "", "", "")
        }
        return null
    }

    /**
     * Body部の作成
     */
    private fun createBody(
        calendar: Calendar,
        inputWeight: String,
        inputHeight: String,
        inputExcuse: String
    ):RecyclerState {
        val day = DAY_FORMAT.format(calendar.time)
        val bmi =
            (round((inputWeight.toDouble() / (inputHeight.toDouble() / 100) / (inputHeight.toDouble() / 100) * 10)) / 10).toString()
        if (inputExcuse.isEmpty()) {
            return RecyclerState(
                    RecyclerType.BODY,
                    "",
                    day,
                    inputHeight,
                    inputWeight,
                    bmi,
                    ""
                )
        } else {
            return RecyclerState(
                    RecyclerType.BODY_EXCUSE,
                    "",
                    day,
                    inputHeight,
                    inputWeight,
                    bmi,
                    inputExcuse
                )
        }
    }

}
