package io.material.rally.ui

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

/**
 * Created by Chan Myae Aung on 7/31/19.
 */
class RallyApp : Application() {

  override fun onCreate() {
    super.onCreate()
    AppCompatDelegate.setDefaultNightMode(
        AppCompatDelegate.MODE_NIGHT_YES)
  }
}