package io.material.rally.data.model

/**
 * Created by Chan Myae Aung on 8/15/19.
 */
data class Account(val name: String,val desc:String,val amount:String)

data class AccountOverView(val total:String,val accounts:List<Account>)