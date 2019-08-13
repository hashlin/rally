package io.material.design_system

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.ViewGroup
import android.animation.PropertyValuesHolder
import android.transition.Visibility
import android.view.View
import androidx.core.animation.doOnEnd
/**
 * Created by Chan Myae Aung on 8/12/19.
 */
class PopTransition(context: Context, attrs: AttributeSet) : Visibility(context, attrs) {

  private var startScale: Float = 0.0f
  private var endScale: Float = 1.0f

  init {
    val a = context.obtainStyledAttributes(attrs, R.styleable.PopTransition)
    startScale = a.getFloat(R.styleable.PopTransition_startScale, startScale)
    endScale = a.getFloat(R.styleable.PopTransition_endScale, endScale)
    a.recycle()
  }

  override fun onAppear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
    view.scaleX = startScale
    view.scaleY = startScale
    return ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat(View.SCALE_X, endScale),
        PropertyValuesHolder.ofFloat(View.SCALE_Y, endScale)
    )
  }

  override fun onDisappear(sceneRoot: ViewGroup, view: View, startValues: TransitionValues, endValues: TransitionValues): Animator {
    return ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat(View.SCALE_X, endScale),
        PropertyValuesHolder.ofFloat(View.SCALE_Y, endScale)
    ).apply {
      doOnEnd {
        // Reset View X & Y to allow shared element to return with proper start dimension
        view.scaleX = startScale
        view.scaleY = startScale
      }
    }
  }


}