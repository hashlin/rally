package io.material.design_system

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.animation.ValueAnimator



/**
 * Created by Chan Myae Aung on 8/12/19.
 */
class ScaleTransition : Transition {

  private var startScale = 0f
  private var endScale = 1f

  constructor(
    startScale: Float,
    endScale: Float
  ) : super() {
    this.startScale = startScale
    this.endScale = endScale
  }

  constructor(
    context: Context,
    attrs: AttributeSet
  ) : super(context, attrs){
    val a = context.obtainStyledAttributes(attrs, R.styleable.FadeTransition)
    startScale = a.getFloat(R.styleable.ScaleTransition_startScal, startScale)
    endScale = a.getFloat(R.styleable.ScaleTransition_endScal, endScale)
    a.recycle()
  }

  override fun captureStartValues(transitionValues: TransitionValues) {
    captureValues(transitionValues)
  }

  override fun captureEndValues(transitionValues: TransitionValues) {
    captureValues(transitionValues)
  }

  private fun captureValues(values: TransitionValues) {
    values.values[PROPNAME_SCALE_X] = values.view.scaleX
    values.values[PROPNAME_SCALE_Y] = values.view.scaleY
  }

  override fun createAnimator(
    sceneRoot: ViewGroup,
    startValues: TransitionValues?,
    endValues: TransitionValues?
  ): Animator? {
    if (endValues == null || startValues == null)
      return null    // no values


    val view = startValues.view
    val propX = PropertyValuesHolder.ofFloat(PROPNAME_SCALE_X, startScale, endScale)
    val propY = PropertyValuesHolder.ofFloat(PROPNAME_SCALE_Y, startScale, endScale)
    val valAnim = ValueAnimator.ofPropertyValuesHolder(propX, propY)
    valAnim.addUpdateListener { valueAnimator ->
      view.pivotX = view.width / 2f
      view.pivotY = view.height / 2f
      view.scaleX = valueAnimator.animatedFraction
      view.scaleY = valueAnimator.animatedFraction
    }
    return valAnim
  }

  companion object {
    private val PROPNAME_SCALE_X = "PROPNAME_SCALE_X"
    private val PROPNAME_SCALE_Y = "PROPNAME_SCALE_Y"
  }
}