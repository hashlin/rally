package io.material.rally.ui

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import io.material.rally.PreferenceRepository

/**
 * Created by Chan Myae Aung on 7/31/19.
 */
class RallyApp : Application() {
  lateinit var preferenceRepository: PreferenceRepository

  override fun onCreate() {
    super.onCreate()
    preferenceRepository = PreferenceRepository(
        getSharedPreferences(DEFAULT_PREFERENCES, Context.MODE_PRIVATE)
    )
  }

  companion object {
    const val DEFAULT_PREFERENCES = "default_preferences"
  }
}