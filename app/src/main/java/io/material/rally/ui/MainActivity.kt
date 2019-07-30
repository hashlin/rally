package io.material.rally.ui

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import io.material.rally.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewPager()
    }

    private fun setUpViewPager(){
        val tabs = generateTabs()
        view_pager.adapter = RallyPagerAdapter(supportFragmentManager, tabs)
        tab_layout.setupWithViewPager(view_pager)

        tabs.forEachIndexed { i, tab ->
            tab_layout.getTabAt(i)?.setIcon(tab.icon)
        }

        tab_layout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                for(i in 0 until tab_layout.tabCount){
                    tab_layout.getTabAt(i)?.text = ""
                }
                tab.text = tabs[tab.position].name
            }
        })

    }

    private fun calculateTabWidth(){
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthS = displayMetrics.widthPixels
    }
}
