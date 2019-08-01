package io.material.rally_pie

import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Created by lin min phyo on 2019-07-30.
 */

class RallyPieAnimation(val pie: RallyPie) : Animation() {

    private var rallyPieProgressRenderData: MutableList<Pair<Float, Float>> = mutableListOf()
    private var rallyPieRenderData: List<RallyPieRenderData> = listOf()

    fun addData(rallyPieRenderData: List<RallyPieRenderData>) {
        this.rallyPieRenderData = rallyPieRenderData
        repeat(rallyPieRenderData.size) {
            this.rallyPieProgressRenderData.add(-90f to 0f)
        }
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        pie.rallyPieProgressRenderData = rallyPieRenderData.mapIndexed { index, it ->
            val progressStartAngle = -90 + (it.startAngle - rallyPieProgressRenderData[index].first) * interpolatedTime
            val progressSweepAngle = 0 + (it.sweepAngle - rallyPieProgressRenderData[index].second) * interpolatedTime
            it.copy(startAngle = progressStartAngle , sweepAngle = progressSweepAngle)
        }

        pie.requestLayout()
    }
}
