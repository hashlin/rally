package com.example.rally_scrollable_tab

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
object Util {

  /**
   * This method is from medium article by supha software with a little params modification
   */
  fun getGaussianScale(
    childCenterX: Int,
    minScaleOffest: Float,
    scaleFactor: Float,
    spreadFactor: Double,
    left: Int,
    right: Int
  ): Float {
    val recyclerCenterX = (left + right) / 2
    return (Math.pow(
        Math.E,
        -Math.pow(childCenterX - recyclerCenterX.toDouble(), 2.toDouble()) / (2 * Math.pow(
            spreadFactor,
            2.toDouble()
        ))
    ) * scaleFactor + minScaleOffest).toFloat()
  }
}