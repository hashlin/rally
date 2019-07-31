package io.material.rally.ui.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by Chan Myae Aung on 7/31/19.
 */

fun ViewGroup.inflate(@LayoutRes res:Int):View{
  return LayoutInflater.from(context).inflate(res,this,false)
}