package io.material.rally.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import io.material.rally.R
import io.material.rally.ui.RallyApp
import io.material.rally.ui.getRandomPoints
import kotlinx.android.synthetic.main.activity_detail.rallyLine
import kotlinx.android.synthetic.main.activity_detail.tab
import kotlinx.android.synthetic.main.activity_detail.toolbar
import kotlinx.android.synthetic.main.activity_detail.viewPager

class DetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    (application as RallyApp).preferenceRepository
        .nightModeLive.observe(this, Observer { nightMode ->
      nightMode?.let { delegate.localNightMode = it }
    })

    setUpToolbar()
    setUpTab()
    rallyLine.addDataPoints(getRandomPoints())
  }

  private fun setUpToolbar() {
    setSupportActionBar(toolbar as Toolbar?)
    val actionBar = supportActionBar
    actionBar?.setDisplayHomeAsUpEnabled(true)
    actionBar?.title = ""
  }

  private fun setUpTab() {
    tab.addTabs(
        listOf(
            "Jan 2018", "Feb 2018", "Mar 2018", "Apr 2018", "May 2018", "Jun 2018", "July 2018",
            "Aug 2018", "Sep 2018", "Oct 2018", "Nov 2018", "Dec 2018"
        )
    )
    tab.addOnPageChangeListener {
      rallyLine.addDataPoints(getRandomPoints())
    }

    viewPager.adapter = MonthlyPagerAdapter(supportFragmentManager)
    tab.setUpWithViewPager(viewPager)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_detail, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if(item.itemId == android.R.id.home){
      super.onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }
}
