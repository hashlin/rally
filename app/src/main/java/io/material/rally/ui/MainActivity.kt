package io.material.rally.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
    view_pager.offscreenPageLimit = 0
    tab_layout.setUpWithViewPager(view_pager, false)

    view_pager.setCurrentItem(0, true)
  }

  fun navigateToTabs(item: TabItem) {
    view_pager.setCurrentItem(item.position, true)
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

  override fun onBackPressed() {
    if (view_pager.currentItem != 0) {
      view_pager.currentItem = 0
      return
    }
    super.onBackPressed()
  }

  companion object {
    fun start(context: Context) {
      val intent = Intent(context, MainActivity::class.java)
      //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      context.startActivity(intent)
    }
  }
}

enum class TabItem(val position: Int) {
  ACCOUNT(1),
  BILL(2),
  BUDGET(3)
}
