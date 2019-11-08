package com.example.mybmi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * アプリ開始日
 */
const val APP_START_DATE = "20190901"//アプリ開始日
val DATE_FORMAT = SimpleDateFormat("yyyyMMdd", Locale.JAPAN)
val MONTH_FORMAT = SimpleDateFormat("M", Locale.JAPAN)//前ゼロなし
val DAY_FORMAT = SimpleDateFormat("d", Locale.JAPAN)//前ゼロなし

class MainActivity : FragmentActivity() {
//inputアイコンはMITライセンス　：https://icons-for-free.com/user+icon-1320166904740390564/
//履歴アイコンはCCライセンス　：https://icon-icons.com/ja/%E3%82%A2%E3%82%A4%E3%82%B3%E3%83%B3/%E5%B1%A4/64757
//履歴アイコンはCCライセンスだからアプリとしての公開は控えた方が良い？
//⇨控えた方がいい

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //タブの処理追加
        viewPager.adapter =PagerAdapter(supportFragmentManager)
        tab.setupWithViewPager(viewPager)

        //タブのレイアウト追加
        tab.getTabAt(0)?.also {
            it.text = getString(R.string.text_titleInputTab)
            it.setIcon(R.drawable.ic_input_tab)
        }
        tab.getTabAt(1)?.also {
            it.text = getString(R.string.text_titleHistoryTab)
            it.setIcon(R.drawable.ic_history_tab)
        }

        //タブ遷移時の処理
        tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                when(p0!!.position) {
                    0 -> {
                    }
                    1 -> {
                        //履歴タブへの遷移時　HistoryFragmentの内容を再描画
//                        val v = supportFragmentManager.fragments[1] as HistoryFragment
//                        v.setHistory()
                    }
                    else -> {
                    }
                }
            }
        })
    }
}

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                 InputFragment()
            }
            1 -> {
                HistoryFragment()
            }
            else -> {
                InputFragment()
            }
        }
    }
    override fun getCount(): Int {
        return 2
    }
}