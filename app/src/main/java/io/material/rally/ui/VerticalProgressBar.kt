package io.material.rally.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar

/**
 * Created by lin min phyo on 2019-08-04.
 */
class  VerticalProgressBar @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0,
  defStyleRes : Int = 0
) : ProgressBar(context, attrs, defStyleAttr, defStyleRes) {

  init {
    rotation = -90f
  }
}