package io.material.rally_line_chart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * Created by lin min phyo on 2019-07-31.
 */

class RallyLineChart : View {

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


    val path = Path().apply {
        dataPoints.forEachIndexed { index, pointF ->
            cubicTo(
                firstConnectPoints[index].x,
                firstConnectPoints[index].y,
                secondConnectPoints[index].x,
                secondConnectPoints[index].y,
                pointF.x,
                pointF.y
            )
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(path, paint)
    }
}