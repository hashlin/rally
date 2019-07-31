package io.material.rally_pie

import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Created by lin min phyo on 2019-07-30.
 */

class RallyPieAnimation(pie: RallyPie) : Animation() {

    private var startOldAngles: List<Float> = listOf()
    private var startNewAngles: List<Float> = listOf()
    private var newAngles: List<Float> = listOf()
    private var oldAngles: List<Float> = listOf()
    private var pie: RallyPie? = pie

    init {
        this.oldAngles = pie.getSweepAngles()
        this.startOldAngles = pie.getStartAngles()
    }

    fun setAngles(newAngles : List<Float>){
        this.newAngles = newAngles
    }


    fun setStartAngles(startAngles : List<Float>){
        this.startNewAngles = startAngles
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {

        val updatedAngles = newAngles.mapIndexed { index, value ->
           0 + ( value - oldAngles[index] ) * interpolatedTime
        }

        val updatedStartAngles= startNewAngles.mapIndexed { index, value ->
            -90  + (value - startOldAngles[index]) * interpolatedTime
        }

        pie?.setStartAngles(updatedStartAngles)
        pie?.setSweepAngles(updatedAngles)
        pie?.requestLayout()
    }
}
