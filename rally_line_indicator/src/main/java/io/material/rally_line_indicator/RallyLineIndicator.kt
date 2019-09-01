package io.material.rally_line_indicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import io.material.rally_line_indicator.data.RallyLineIndicatorData
import io.material.rally_line_indicator.data.RallyLineIndicatorRenderData
import io.material.rally_line_indicator.data.toPoints

/**
 * Created by lin min phyo on 2019-08-01.
 */

class RallyLineIndicator @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {



  private var renderData = listOf<RallyLineIndicatorRenderData>()

  private val paint = Paint().apply {
    strokeWidth = 10f
    color = Color.GREEN
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    val width = measuredWidth.toFloat()

    var startAt = 0f

    // todo : Move calculation to outside of onDraw
    renderData.forEach {
      val stopAt = startAt + width * it.percentage

      paint.color = it.color
      canvas?.drawLine(startAt, (height/2).toFloat(), stopAt, (height/2).toFloat(), paint)
      startAt = stopAt
    }
  }

  fun setData(pieData: RallyLineIndicatorData) {
    val maxValue = pieData.maxValue?.let {
      it
    } ?: pieData.portions.sumByDouble { it.value.toDouble() }
        .toFloat()


    renderData = pieData.portions.toPoints(maxValue)
  }
}