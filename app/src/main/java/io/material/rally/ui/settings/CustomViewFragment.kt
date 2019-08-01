package io.material.rally.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import io.material.rally.R
import io.material.rally.RallyPie
import io.material.rally_pie.RallyPieAnimation
import io.material.design_system.R as DesignSystemR
import io.material.rally_pie.RallyPieData
import io.material.rally_pie.RallyPiePortion
import kotlinx.android.synthetic.main.component_rally_pie.*

/**
 * Created by lin min phyo on 2019-08-01.
 */
class CustomViewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.component_rally_pie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val rallyPiePortions = listOf(
            RallyPiePortion("A" , 100f , ContextCompat.getColor(requireContext() , DesignSystemR.color.rally_green_500)),
            RallyPiePortion("A" , 500f , ContextCompat.getColor(requireContext() , DesignSystemR.color.rally_green_700)),
            RallyPiePortion("A" , 300f , ContextCompat.getColor(requireContext() , DesignSystemR.color.rally_green_300))
        )
        val rallyPieData = RallyPieData(portions = rallyPiePortions)
        val rallyPieAnimation = RallyPieAnimation(rallyPie)
        rallyPieAnimation.duration = 600

        rallyPie.setPieData(pieData =  rallyPieData , animation = rallyPieAnimation)
    }
}