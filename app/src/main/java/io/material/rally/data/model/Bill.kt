package io.material.rally.data.model

/**
 * Created by Chan Myae Aung on 8/15/19.
 */
data class Bill(val name: String,val desc:String,val amount:String)

data class BillOverView(val total:String,val bills:List<Bill>)