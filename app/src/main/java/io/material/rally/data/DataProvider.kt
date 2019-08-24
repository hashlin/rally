package io.material.rally.data

import io.material.rally.R
import io.material.rally.data.model.Account
import io.material.rally.data.model.AccountOverView
import io.material.rally.data.model.Bill
import io.material.rally.data.model.BillOverView
import io.material.rally.data.model.Budget
import io.material.rally.data.model.BudgetOverview

/**
 * Created by Chan Myae Aung on 8/15/19.
 */
object DataProvider {

  private val accounts = listOf(
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_700),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_500),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_300),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_dark_green),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_700),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_500),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_300),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_dark_green),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_500),
      Account("Home Savings", "......1234", 2_215.13f, R.color.rally_green_300)
  )

  private val bills = listOf(
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_yellow),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_orange),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_yellow_500),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_yellow),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_orange),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_yellow_500),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_yellow),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_orange),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_yellow_500),
      Bill("RedPay Credit", "Due Feb 9", 1200.00f, R.color.rally_yellow)
  )

  private val budgets = listOf(
      Budget("Coffee Shops", 1000f, 500f, R.color.rally_blue),
      Budget("Coffee Shops", 1000f, 200f, R.color.rally_purple),
      Budget("Coffee Shops", 1000f, 100f, R.color.rally_blue_100),
      Budget("Coffee Shops", 1000f, 800f, R.color.rally_blue),
      Budget("Coffee Shops", 1000f, 1000f, R.color.rally_purple),
      Budget("Coffee Shops", 1000f, 400f, R.color.rally_blue_100),
      Budget("Coffee Shops", 1000f, 50f, R.color.rally_blue),
      Budget("Coffee Shops", 1000f, 100f, R.color.rally_purple),
      Budget("Coffee Shops", 1000f, 500f, R.color.rally_blue_100),
      Budget("Coffee Shops", 1000f, 600f, R.color.rally_blue)
  )

  val accountOverView =
    AccountOverView(accounts.sumByDouble { it.amount.toDouble() }.toFloat(), accounts)

  val billOverView = BillOverView(bills.sumByDouble { it.amount.toDouble() }.toFloat(), bills)

  val budgetOverView = BudgetOverview(budgets , budgets.sumByDouble { it.total.toDouble() }.toFloat())
}