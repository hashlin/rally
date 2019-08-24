package io.material.rally_line_chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.os.Handler
import android.os.HandlerThread
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Chan Myae Aung on 8/22/19.
 */
class RallyLineGraphChart : View {

  private val data = mutableListOf<DataPoint>()
  private val points = mutableListOf<PointF>()
  private val conPoint1 = mutableListOf<PointF>()
  private val conPoint2 = mutableListOf<PointF>()

  private val path = Path()
  private val barPaint = Paint()
  private val pathPaint = Paint()

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

  private fun init() {
    barPaint.apply {
      isAntiAlias = true
      strokeWidth = BAR_WIDTH
      style = Paint.Style.STROKE
      color = Color.GRAY
    }
    pathPaint.apply {
      isAntiAlias = true
      strokeWidth = 12f
      style = Paint.Style.STROKE
      color = Color.parseColor("#ff21AF6C")
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

    //drawVerticalBars(canvas)
    drawBezierCurve(canvas)
  }

  private fun drawVerticalBars(canvas: Canvas?) {
    val largeBarHeight = getLargeBarHeight()
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

  private fun drawBezierCurve(canvas: Canvas?) {

    if (points.isEmpty() && conPoint1.isEmpty() && conPoint2.isEmpty()) return

    path.reset()
    path.moveTo(points.first().x, points.first().y)

    for (i in 1 until points.size) {
      path.cubicTo(
          conPoint1[i - 1].x, conPoint1[i - 1].y, conPoint2[i - 1].x, conPoint2[i - 1].y,
          points[i].x, points[i].y
      )
    }

    canvas?.drawPath(path, pathPaint)
  }

  private fun calculatePointsForData() {
    if (data.isEmpty()) return

    val bottomY = getLargeBarHeight() - CURVE_BOTTOM_MARGIN
    val xDiff =
      width.toFloat() / (data.size - 1) //subtract -1 because we want to include position at right side

    val maxData = data.maxBy { it.amount }!!.amount

    for (i in 0 until data.size) {
      val y = bottomY - (data[i].amount / maxData * (bottomY - CURVE_TOP_MARGIN))
      points.add(PointF(xDiff * i, y))
    }
  }

  private fun calculateConnectionPointsForBezierCurve() {
    for (i in 1 until points.size) {
      conPoint1.add(PointF((points[i].x + points[i - 1].x) / 2, points[i - 1].y))
      conPoint2.add(PointF((points[i].x + points[i - 1].x) / 2, points[i].y))
    }
  }

  private fun getLargeBarHeight() = height / 3 * 2f

  fun addDataPoints(data: List<DataPoint>) {
    //do calculation in worker thread // Note: You should use some safe thread mechanism
    post {
      Thread(Runnable {

        val oldPoints = points.toList()

        if (oldPoints.isEmpty()) {
          this.data.addAll(data.toList())
          calculatePointsForData()
          calculateConnectionPointsForBezierCurve()
          postInvalidate()
          return@Runnable
        }

        resetDataPoints()
        this.data.addAll(data.toList())
        calculatePointsForData()
        calculateConnectionPointsForBezierCurve()

        val newPoints = points.toList()

        val size = oldPoints.size
        var maxDiffY = 0f
        for (i in 0 until size) {
          val abs = abs(oldPoints[i].y - newPoints[i].y)
          if (abs > maxDiffY) maxDiffY = abs
        }

        val loopCount = maxDiffY / 16

        val tempPointsForAnimation = mutableListOf<MutableList<PointF>>()

        for (i in 0 until size) {
          val old = oldPoints[i]
          val new = newPoints[i]

          var tempY = old.y
          val tempList = mutableListOf<PointF>()

          for (j in 0..loopCount.toInt()) {
            if (tempY == new.y) {
              tempList.add(PointF(new.x, new.y))

            } else {

              if (new.y > old.y) {
                tempY += 16
                tempY = min(tempY, new.y)
                tempList.add(PointF(new.x, tempY))

              } else {
                tempY -= 16
                tempY = max(tempY, new.y)
                tempList.add(PointF(new.x, tempY))
              }
            }
          }
          tempPointsForAnimation.add(tempList)

        }

        val first = tempPointsForAnimation[0]
        val second = tempPointsForAnimation[1]
        val third = tempPointsForAnimation[2]

        val length = first.size

        for (i in 0 until length) {
          conPoint1.clear()
          conPoint2.clear()
          points.clear()
          points.add(first[i])
          points.add(second[i])
          points.add(third[i])
          calculateConnectionPointsForBezierCurve()
          postInvalidate()
          Thread.sleep(60)

          /*
          1s = 16


           */
        }

      }).start()
    }
  }

  private fun resetDataPoints() {
    this.data.clear()
    points.clear()
    conPoint1.clear()
    conPoint2.clear()
  }

  companion object {
    private const val INDEX_OF_LARGE_BAR = 7
    private const val VERTICAL_BARS = (INDEX_OF_LARGE_BAR * 7) + 1 // add fixed bars size

    private const val BAR_WIDTH = 6f // get from attribute for more flexibility
    private const val CURVE_BOTTOM_MARGIN = 16f
    private const val CURVE_TOP_MARGIN = 200f

  }
}

data class DataPoint(val amount: Float)