package io.material.rally.ui.settings

import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.switchmaterial.SwitchMaterial
import io.material.rally.R
import io.material.rally.ui.RallyApp
import io.material.rally_line_indicator.data.RallyLineIndicatorData
import io.material.rally_line_indicator.data.RallyLineIndicatorPortion
import io.material.rally_pie.RallyPieAnimation
import io.material.rally_pie.RallyPieData
import io.material.rally_pie.RallyPiePortion
import kotlinx.android.synthetic.main.fragment_settings.btn_cma
import kotlinx.android.synthetic.main.fragment_settings.btn_lmp
import kotlinx.android.synthetic.main.fragment_settings.tv_about_open_source
import kotlinx.android.synthetic.main.fragment_settings.tv_about_rally_project
import io.material.design_system.R as DesignSystemR
import android.content.Intent
import android.net.Uri

/**
 * Created by lin min phyo on 2019-08-01.
 */
class SettingsFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_settings, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    Linkify.addLinks(tv_about_rally_project, Linkify.WEB_URLS)
    Linkify.addLinks(tv_about_open_source, Linkify.WEB_URLS)

    btn_cma.setOnClickListener {
      startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Thechanmyaeaung")))
    }
    btn_lmp.setOnClickListener {
      startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Linminphyoe1")))
    }

  }
}