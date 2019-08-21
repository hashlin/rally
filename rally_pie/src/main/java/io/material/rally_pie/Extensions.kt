package io.material.rally_pie

import android.content.Context
import android.util.TypedValue



/**
 * Created by lin min phyo on 2019-08-17.
 */
fun Context.dpToPx(dp : Float) : Float {
  return TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics
  )
}