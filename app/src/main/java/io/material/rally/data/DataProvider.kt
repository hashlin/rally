package io.material.rally.data

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
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13"),
      Account("Home Savings","......1234","2,215.13")
  )

  private val bills = listOf(
      Bill("RedPay Credit","Due Feb 9","1,200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00"),
      Bill("RedPay Credit","Due Feb 9","1200.00")
  )

  private val budgets = listOf(
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13"),
      Budget("Coffee Shops","$45.49 / $70","2,215.13")
  )

  val accountOverView = AccountOverView("12,132.49", accounts)

  val billOverView = BillOverView("1,810.09", bills)

  val budgetOverView = BudgetOverview("717.12", budgets)
}