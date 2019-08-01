package io.material.rally.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import io.material.rally.R
import kotlinx.android.synthetic.main.activity_main.tab_layout
import kotlinx.android.synthetic.main.activity_main.view_pager

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) runEnterAnimation()
    setUpViewPager()
  }

  private fun setUpViewPager() {
    val tabs = generateTabs()
    view_pager.adapter = RallyPagerAdapter(supportFragmentManager, tabs)
    tab_layout.setupWithViewPager(view_pager)

    tabs.forEachIndexed { i, tab ->
      tab_layout.getTabAt(i)
          ?.setIcon(tab.icon)
    }

    tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabReselected(tab: TabLayout.Tab?) {}

      override fun onTabUnselected(tab: TabLayout.Tab?) {}

      override fun onTabSelected(tab: TabLayout.Tab) {
        for (i in 0 until tab_layout.tabCount) {
          tab_layout.getTabAt(i)
              ?.text = ""
        }
        tab.text = tabs[tab.position].name
      }
    })
    tab_layout.getTabAt(0)
        ?.text = tabs[0].name

  }

  private fun calculateTabWidth() {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val widthS = displayMetrics.widthPixels
  }

  private fun runEnterAnimation() {
    tab_layout.post {
      tab_layout.translationY -= tab_layout.height.toFloat()
      tab_layout.alpha = 0f
      tab_layout.animate()
          .translationY(0f)
          .setInterpolator(AccelerateDecelerateInterpolator())
          .alpha(1f)
          .setDuration(300)
          .start()
    }
  }
}
