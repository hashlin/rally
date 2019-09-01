package io.material.rally.extension

import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

/**
 * Created by Chan Myae Aung on 7/31/19.
 */

fun ViewGroup.inflate(@LayoutRes res: Int): View {
  return LayoutInflater.from(context)
      .inflate(res, this, false)
}

fun RecyclerView.getRallyItemDecoration() =
  DividerItemDecoration(this.context, RecyclerView.VERTICAL).apply {
    val divider =
      ContextCompat.getDrawable(this@getRallyItemDecoration.context, R.drawable.divider)!!
    val margin = resources.getDimensionPixelSize(R.dimen.spacing_medium)
    setDrawable(InsetDrawable(divider, margin, 0, margin, 0))
  }


fun Float.toMoneyFormatted(removeSuffix : Boolean = false) : String {
  return DecimalFormat("###,###,##0.00").format(this).apply {
    if(removeSuffix){
      return this.removeSuffix(".00")
    }
  }
}

fun Float.toUSDFormatted() : String {
  return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}