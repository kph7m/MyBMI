package com.example.mybmi

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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


        //タブの処理追加
        viewPager.adapter =PagerAdapter(supportFragmentManager, this)
        tab.setupWithViewPager(viewPager)

        //タブのレイアウト追加
        tab.getTabAt(0)?.also {
            it.setText(R.string.text_titleInputTab)
            it.setIcon(R.drawable.ic_input_tab)
        }
        tab.getTabAt(1)?.also {
            it.setText(R.string.text_titleHistoryTab)
            it.setIcon(R.drawable.ic_history_tab)
        }
    }


//
//    //Fragmentの表示処理
//    private fun viewFragment(fragment: Fragment){
//        val fragmentListener = this.supportFragmentManager
//        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.container,fragment).addToBackStack(null).commit()
//    }
}

class PagerAdapter(fm: FragmentManager, context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return InputFragment()
            }
            1 -> {
                return HistoryFragment()
            }
            else -> {
                return InputFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}