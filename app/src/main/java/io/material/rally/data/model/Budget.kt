package io.material.rally.data.model

/**
 * Created by Chan Myae Aung on 8/15/19.
 */
data class Budget(val name: String,val desc:String,val amount:String)

data class BudgetOverview(val total:String,val budgets:List<Budget>)