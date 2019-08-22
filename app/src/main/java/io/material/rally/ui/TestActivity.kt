package io.material.rally.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.material.rally.R
import io.material.rally_line_chart.DataPoint
import kotlinx.android.synthetic.main.test.rallyLine

/**
 * Created by lin min phyo on 2019-08-06.
 */

class TestActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.test)

    rallyLine.addDataPoints(getSampleDataPoints())
  }
}
fun getSampleDataPoints() = listOf(
    DataPoint(1000f),
    DataPoint(500f),
    DataPoint(300f),
    DataPoint(0f),
    DataPoint(1000f),
    DataPoint(700f),
    DataPoint(800f),
    DataPoint(1000f),
    DataPoint(500f),
    DataPoint(300f),
    DataPoint(0f),
    DataPoint(1000f),
    DataPoint(700f),
    DataPoint(800f),
    DataPoint(1000f),
    DataPoint(500f),
    DataPoint(300f),
    DataPoint(0f),
    DataPoint(1000f),
    DataPoint(700f),
    DataPoint(800f),
    DataPoint(1000f),
    DataPoint(500f),
    DataPoint(300f),
    DataPoint(0f),
    DataPoint(1000f),
    DataPoint(700f),
    DataPoint(800f),
    DataPoint(700f),
    DataPoint(800f)
)
