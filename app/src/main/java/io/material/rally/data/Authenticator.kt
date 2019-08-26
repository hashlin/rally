package io.material.rally.data

import android.content.Context

/**
 * Created by Chan Myae Aung on 8/26/19.
 */
class Authenticator(val context: Context) {

  private val pref = context.getSharedPreferences("RALLY", Context.MODE_PRIVATE)

  fun isLoggedIn(): Boolean {
    return pref.getBoolean(KEY_LOGIN, false)
  }

  fun login() {
    pref.edit()
        .putBoolean(KEY_LOGIN, true)
        .apply()
  }

  companion object {
    private const val KEY_LOGIN = "key-login"
  }
}