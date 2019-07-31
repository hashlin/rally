package io.material.rally

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import io.material.rally_pie.RallyPieAnimation
import kotlinx.android.synthetic.main.component_rally_pie.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.component_rally_pie)

        val animation = RallyPieAnimation(rallyPie)
        animation.interpolator = FastOutSlowInInterpolator()
        animation.duration = 6000
        rallyPie.startAnimation(animation)
    }
}
