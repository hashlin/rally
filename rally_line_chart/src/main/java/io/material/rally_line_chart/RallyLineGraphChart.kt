package io.material.rally_line_chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

/**
 * Created by Chan Myae Aung on 8/22/19.
 */
class RallyLineGraphChart : View {

  private val data = mutableListOf<DataPoint>()
  private val points = mutableListOf<PointF>()

  private val barPaint = Paint()

  constructor(context: Context?) : super(context) {
    init()
  }

  constructor(
    context: Context?,
    attrs: AttributeSet?
  ) : super(context, attrs) {
    init()
  }

  constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int
  ) : super(context, attrs, defStyleAttr) {
    init()
  }

  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

  }

  private fun init() {
    barPaint.apply {
      isAntiAlias = true
      strokeWidth = BAR_WIDTH
      style = Paint.Style.STROKE
      color = Color.GRAY
    }
  }

//  private fun measureWidth(widthMeasureSpec: Int): Int {
//    return resolveSizeAndState(MeasureSpec.getSize(widthMeasureSpec), widthMeasureSpec, 0)
//  }
//
//  private fun measureHeight(heightMeasureSpec: Int): Int {
//    return resolveSizeAndState(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec, 0)
//  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    drawVerticalBars(canvas)
    drawBezierCurve(canvas)
  }

  private fun drawVerticalBars(canvas: Canvas?) {
    val largeBarHeight = height / 3 * 2f
    val smallBarHeight = height - largeBarHeight / 3
    val barMargin = (width - (BAR_WIDTH * VERTICAL_BARS)) / VERTICAL_BARS
    var startX = 0f
    val startY = height.toFloat()
    var endX: Float
    var endY: Float

    for (i in 0 until VERTICAL_BARS) {
      startX += BAR_WIDTH + barMargin
      endX = startX
      endY = if (i % INDEX_OF_LARGE_BAR != 0) {
        smallBarHeight
      } else {
        largeBarHeight
      }
      canvas?.drawLine(startX, startY, endX, endY, barPaint)
    }
  }

  private fun drawBezierCurve(canvas: Canvas?){

  }

  private fun calculateXYForDataPoints() {

  }

  companion object {
    private const val INDEX_OF_LARGE_BAR = 7
    private const val VERTICAL_BARS = (INDEX_OF_LARGE_BAR * 7) + 1 // add fixed bars size

    private const val BAR_WIDTH = 6f // get from attribute for more flexibility
  }
}

data class DataPoint(val amount: Float)