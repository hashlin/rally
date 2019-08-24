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

  var i = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.test)

    rallyLine.addDataPoints(getPoints())

    btn.setOnClickListener {
      rallyLine.addDataPoints(getPoints())
    }
  }

  fun getPoints():List<DataPoint>{
    i++
    return if(i % 2 != 0){
      listOf(DataPoint(1000f),DataPoint(1000f),DataPoint(500f))
    }else{
      listOf(DataPoint(1000f),DataPoint(500f),DataPoint(0f))
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
