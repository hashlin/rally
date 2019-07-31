package io.material.rally

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import io.material.rally_pie.RallyPieAnimation
import io.material.rally_pie.RallyPieData
import io.material.rally_pie.RallyPiePortion
import kotlinx.android.synthetic.main.component_rally_pie.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.component_rally_pie)

        val animation = RallyPieAnimation(rallyPie)
        animation.interpolator = FastOutSlowInInterpolator()
        animation.duration = 6000
        val pieDataList = listOf(
            RallyPiePortion("A" , 30f , Color.BLUE),
            RallyPiePortion("B" , 30f , Color.RED),
            RallyPiePortion("C" , 30f , Color.MAGENTA)
        )
        rallyPie.setPieData(RallyPieData(portions = pieDataList))
        rallyPie.startAnimation(animation)
    }
}
