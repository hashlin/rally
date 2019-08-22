package io.material.rally.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.material.rally.R
import io.material.rally_line_chart.DataPoint
import kotlinx.android.synthetic.main.test.btn
import kotlinx.android.synthetic.main.test.rallyLine

/**
 * Created by lin min phyo on 2019-08-06.
 */

class TestActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.test)

    rallyLine.addDataPoints(getRandomPoints())

    btn.setOnClickListener {
      rallyLine.addDataPoints(getRandomPoints())
    }
  }
}

fun getRandomPoints(): MutableList<DataPoint> {
  val list = mutableListOf<DataPoint>()
  val range = (0..10)

  (1..30).forEach { _ ->
    list.add(DataPoint(range.random()*100f))
  }
  return list
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
