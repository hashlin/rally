package io.material.rally_line_chart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by lin min phyo on 2019-07-31.
 */

class RallyLineChart : View {

  private val points = listOf(
      PointF(0f, 300f),
      PointF(50f, 0f),
      PointF(100f, 150f),
      PointF(150f, 150f),
      PointF(200f, 0f)

      //100 - (50/2) , 0 => 75,0
      //50 - (0/2) , 0f => 75,150f

  )

  /*
  formula to calculate y position based on value
  100 => height
  0 => height - (0/100*height)
  50 => height - (50/100*height)
  100 => height - (100/100*height)



   */

  constructor(context: Context?) : super(context)

  constructor(
    context: Context?,
    attrs: AttributeSet?
  )
      : super(context, attrs)

  constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int
  )
      : super(context, attrs, defStyleAttr)

  val paint = Paint().apply {
    color = Color.WHITE
    strokeWidth = 6f
    isAntiAlias = true
    style = Paint.Style.STROKE
    strokeCap = Paint.Cap.ROUND
  }

  private val firstConnectPoints = mutableListOf<PointF>(
      PointF()
  )
  private val secondConnectPoints = mutableListOf<PointF>(PointF())

  private val dataPoints = mutableListOf(
      PointF(100f, 100f), PointF(200f, 150f), PointF(300f, 120f),
      PointF(400f, 200f), PointF(500f, 100f), PointF(600f, 120f)
  ).apply {
    for (i in 1 until size) {
      firstConnectPoints.add(PointF((this[i].x + this[i - 1].x) / 2, this[i - 1].y))
      secondConnectPoints.add(PointF((this[i].x + this[i - 1].x) / 2, this[i].y))
    }
  }

  val path = Path()

  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)

    //path.cubicTo(width/4f,height.toFloat()/2,width/4f,height.toFloat()/2,width.toFloat()/2,0f)

//    (1..1).forEach {
//      val width = width.toFloat()
//      val height = height.toFloat()
//      val x1 = width / 4
//      val y1 = height / 2
//      val x2 = width / 4
//      val y2 = height / 2
//      val x3 = width / 2
//      val y3 = (height / 4) * 3
//      path.moveTo(0f, height)
//      path.cubicTo(x1, y1, x2, y2, x3, y3)
//      path.moveTo(x3,y3)
//
//    }
//    path.cubicTo((width / 4) * 3f,0f,
//        (width / 4) * 3f,0f,
//        width.toFloat(),height.toFloat())
//
//    canvas?.drawPath(path, paint)

    //draw line
    path.moveTo(points.first().x, points.first().y)
    for (i in 1 until points.size) {
      val p = points.get(i)
      path.lineTo(p.x, p.y)
    }
    canvas?.drawPath(path, paint)
  }
}