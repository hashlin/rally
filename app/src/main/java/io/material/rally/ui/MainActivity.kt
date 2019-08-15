package io.material.rally.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
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
    view_pager.adapter = RallyPagerAdapter(this , tabs)
    tab_layout.setUpWithViewPager2(view_pager)

    view_pager.setCurrentItem( 0 , true)
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
