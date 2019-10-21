package com.example.mybmi


import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.adapter.MemoViewAdapter
import com.example.recyclerviewapp.model.Memo
import kotlinx.android.synthetic.main.fragment_history.*


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //保存データ取得
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val memoList = mutableListOf<Memo>()
        memoList.add(Memo("19日", "身長："+pref.getString("20191019"+"Height","")+"cm"
            ,"体重："+pref.getString("20191019"+"Weight","")+"kg"
            ,"BMI：20.0"
            ,pref.getString("20191019"+"Excuse","")!!))
        memoList.add(Memo("20日", "身長："+pref.getString("20191020"+"Height","")+"cm"
            ,"体重："+pref.getString("20191020"+"Weight","")+"kg"
            ,"BMI：20.0"
            ,pref.getString("20191020"+"Excuse","")!!))
        memoList.add(Memo("21日", "身長："+pref.getString("20191021"+"Height","")+"cm"
            ,"体重："+pref.getString("20191021"+"Weight","")+"kg"
            ,"BMI：20.0"
            ,pref.getString("20191021"+"Excuse","")!!))
        memoList.add(Memo("３日", "身長：１８１cm","体重：８２kg","BMI：22.3","今日のご飯は、そば"))
        memoList.add(Memo("５日", "身長：１８１cm","体重：８３kg","BMI：23.3","今日のご飯は、カレーライス"))
        memoList.add(Memo("１０日", "身長：１８１cm","体重：７９kg","BMI：10.3","今日のご飯は、なし"))


        memoRecyclerView.also { recyclerView: RecyclerView ->
            recyclerView.adapter = MemoViewAdapter(context!!, memoList)
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

}
