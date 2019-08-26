package io.material.rally.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.animation.addListener
import androidx.core.widget.addTextChangedListener
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import io.material.rally.R
import io.material.rally.ui.MainActivity
import io.material.rally.ui.RallyApp
import io.material.rally.ui.TestActivity
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_pwd
import kotlinx.android.synthetic.main.activity_login.imgLogo
import kotlinx.android.synthetic.main.activity_login.input_email
import kotlinx.android.synthetic.main.activity_login.input_pwd
import kotlinx.android.synthetic.main.activity_login.iv_fingerprint
import kotlinx.android.synthetic.main.activity_login.label_login_id
import kotlinx.android.synthetic.main.activity_main.logo

/**
 * Created by Chan Myae Aung on 8/18/19.
 */
class LoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    (application as RallyApp).preferenceRepository
        .nightModeLive.observe(this, Observer { nightMode ->
      nightMode?.let { delegate.localNightMode = it }
    })

    if (savedInstanceState == null) runEnterAnimation()

    setUpInputField()
  }

  private fun setUpInputField() {
    input_email.isEndIconVisible = false
    et_email.addTextChangedListener {
      input_email.isEndIconVisible = it?.toString()?.trim() == "user@rally"
    }
    et_email.setText("user@rally")


    et_pwd.setOnEditorActionListener { _, actionId, _ ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        login()
        true
      }
      false
    }
  }

  private fun login() {
    val email = et_email.text?.toString()
        ?.trim()
    val password = et_pwd.text?.toString()
        ?.trim()
    if (email == "user@rally" && password == "1234") {
      MainActivity.start(this)
    }
  }

  private fun runEnterAnimation() {
    input_email.alpha = 0f
    input_email.scaleX = 1.05f
    input_pwd.alpha = 0f
    input_pwd.scaleX = 1.05f
    iv_fingerprint.alpha = 0f
    label_login_id.alpha = 0f

    findViewById<View>(android.R.id.content).post {
      val startY = resources.displayMetrics.heightPixels / 2 - imgLogo.height / 2f
      val endY = imgLogo.y

      imgLogo.y = startY
      input_email.translationY += input_email.height
      input_pwd.translationY += input_pwd.height
      iv_fingerprint.translationY += iv_fingerprint.height
      label_login_id.translationY += input_pwd.height

      val logoAnimator = ObjectAnimator.ofFloat(imgLogo, View.Y, startY, endY)
      logoAnimator.duration = DURATION

      val emailInputAnimator = ObjectAnimator.ofPropertyValuesHolder(
          input_email,
          PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f),
          PropertyValuesHolder.ofFloat(View.ALPHA, 1f),
          PropertyValuesHolder.ofFloat(View.SCALE_X, 1f)
      )
      emailInputAnimator.startDelay = 250
      emailInputAnimator.duration = 250

      val pwdInputAnimator = ObjectAnimator.ofPropertyValuesHolder(
          input_pwd,
          PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f),
          PropertyValuesHolder.ofFloat(View.ALPHA, 1f),
          PropertyValuesHolder.ofFloat(View.SCALE_X, 1f)
      )
      pwdInputAnimator.startDelay = 250
      pwdInputAnimator.duration = 250

      val ivFingerPrintAnimator = ObjectAnimator.ofPropertyValuesHolder(
          iv_fingerprint,
          PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f),
          PropertyValuesHolder.ofFloat(View.ALPHA, 1f)
      )
      ivFingerPrintAnimator.startDelay = 300
      ivFingerPrintAnimator.duration = 200

      val labelAnimator = ObjectAnimator.ofPropertyValuesHolder(
          label_login_id,
          PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f),
          PropertyValuesHolder.ofFloat(View.ALPHA, 1f)
      )
      ivFingerPrintAnimator.startDelay = 400
      ivFingerPrintAnimator.duration = 100

      val set = AnimatorSet()
      set.interpolator = FastOutSlowInInterpolator()
      set.playTogether(
          logoAnimator, emailInputAnimator, pwdInputAnimator, ivFingerPrintAnimator, labelAnimator
      )
      set.start()
    }
  }

  companion object {
    private const val DURATION = 500L
  }
}
