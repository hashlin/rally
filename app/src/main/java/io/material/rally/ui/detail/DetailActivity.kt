package io.material.rally.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.material.rally.R
import kotlinx.android.synthetic.main.activity_detail.tab
import kotlinx.android.synthetic.main.activity_detail.viewPager

class DetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    setUpTab()
  }

  private fun setUpTab() {
    tab.addTabs(
        listOf(
            "Jan 2018", "Feb 2018", "Mar 2018", "Apr 2018", "May 2018", "Jun 2018", "July 2018",
            "Aug 2018","Sep 2018","Oct 2018","Nov 2018","Dec 2018"
            )
    )
//    tab.addOnTabListener {
//      Log.i("TAG","position $it")
//    }

    viewPager.adapter = MyPagerAdapter(supportFragmentManager)
    tab.setUpWithViewPager(viewPager)
  }
}
