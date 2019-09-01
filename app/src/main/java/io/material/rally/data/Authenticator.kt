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

  fun login(
    email: String?,
    password: String?
  ): Boolean {
    if (email == "user@rally" && password == "1234") {
      pref.edit()
          .putBoolean(KEY_LOGIN, true)
          .apply()
      return true
    }
    return false
  }

  companion object {
    private const val KEY_LOGIN = "key-login"
  }
}