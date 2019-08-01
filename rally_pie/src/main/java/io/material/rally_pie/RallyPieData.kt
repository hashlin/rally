package io.material.rally_pie

/**
 * Created by lin min phyo on 2019-07-31.
 */

data class RallyPieData(val portions: List<RallyPiePortion>, val maxValue: Float? = null)

data class RallyPiePortion(val name: String, val value: Float, val colorInt: Int)

fun List<RallyPiePortion>.toPoints(maxValue: Float): List<RallyPieRenderData> {
    val renderDataList = mutableListOf<RallyPieRenderData>()
    forEachIndexed { index, it ->
        val startAngle = if (renderDataList.isEmpty()) {
            -90f + 1f
        } else {
            val last = renderDataList.last()
            last.startAngle + last.sweepAngle + 1f
        }

        val sweepAngle = it.value * 360 / maxValue - 1f

        renderDataList.add(RallyPieRenderData(it.name, startAngle, sweepAngle, it.colorInt))
    }

    return renderDataList
}


data class RallyPieRenderData(val name: String, val startAngle: Float, val sweepAngle: Float, val color: Int)

