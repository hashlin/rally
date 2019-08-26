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

    (application as RallyApp).preferenceRepository
        .nightModeLive.observe(this, Observer { nightMode ->
      nightMode?.let { delegate.localNightMode = it }
    })

    if (savedInstanceState == null) runEnterAnimation()
    setUpViewPager()
  }

  private fun setUpViewPager() {
    val tabs = generateTabs()
    view_pager.adapter = RallyPagerAdapter(this , tabs)
    tab_layout.setUpWithViewPager2(view_pager , false)

    view_pager.setCurrentItem( 0 , true)
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

  companion object{
    fun start(context: Context){
      val intent = Intent(context,MainActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      context.startActivity(intent)
    }
  }
}
