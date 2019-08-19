package io.material.rally.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import io.material.rally.R
import io.material.rally.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_pwd
import kotlinx.android.synthetic.main.activity_login.input_email
import kotlinx.android.synthetic.main.activity_login.input_pwd

class LoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

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

  private fun login(){
    val email = et_email.text?.toString()?.trim()
    val password = et_pwd.text?.toString()?.trim()
    if(email == "user@rally" && password == "1234"){
      MainActivity.start(this)
    }
  }

}
