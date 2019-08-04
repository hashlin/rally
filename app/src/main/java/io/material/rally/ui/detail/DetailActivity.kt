package io.material.rally.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.material.rally.R
import kotlinx.android.synthetic.main.activity_detail.tab

class DetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    setUpTab()
  }

  private fun setUpTab() {
    tab.addTabs(
        listOf(
            "OverView", "Jan 2018", "Jan 2018", "Jan 2018", "Jan 2018", "Jan 2018", "Jan 2018",
            "Jan 2018"
            )
    )
  }
}
