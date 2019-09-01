package io.material.rally.data.model

import androidx.annotation.ColorRes

/**
 * Created by Chan Myae Aung on 8/15/19.
 */
data class Account(val name: String,val desc:String,val amount:Float, @ColorRes val color:Int)

data class AccountOverView(val total:Float,val accounts:List<Account>)