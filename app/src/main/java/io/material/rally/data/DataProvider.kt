package io.material.rally.data

import io.material.rally.R
import io.material.rally.data.model.Account
import io.material.rally.data.model.AccountOverView
import io.material.rally.data.model.Alert
import io.material.rally.data.model.Bill
import io.material.rally.data.model.BillOverView
import io.material.rally.data.model.Budget
import io.material.rally.data.model.BudgetOverview

/**
 * Created by Chan Myae Aung on 8/15/19.
 */
object DataProvider {

  val alerts = listOf(
      Alert(
          "Heads up, you've used up 90% of your Shopping budget for this month.", R.drawable.ic_sort
      ),
      Alert(
          "You've spent $120 on Restaurants this week.", R.drawable.ic_sort
      ),
      Alert(
          "You've spent $24 in ATM fees this month.", R.drawable.ic_credit_card
      ),
      Alert(
          "Good work! Your checking account is 4% higher than this time last month.",
          R.drawable.ic_attach_money
      ),
      Alert(
          "Increase your potential tax deduction! Assign categories to 16 unassigned transactions.",
          R.drawable.ic_not_interest
      ),
      Alert(
          "Get every tax deduction you're entitled to. Assign categories to 16 unassigned transactions.",
          R.drawable.ic_overview
      ),
      Alert(
          "Your ABC Loan payment of $325.81 was received", R.drawable.ic_attach_money
      ),
      Alert(
          "Open an IRA account and get $100 bonus.", R.drawable.ic_attach_money
      )

  )

  private val accounts = listOf(
      Account("Home Savings", "••••••1234", 2_215.13f, R.color.rally_green_700),
      Account("Car Savings", "••••••5678", 8676.88f, R.color.rally_green_500),
      Account("Vacations", "••••••1234", 987.48f, R.color.rally_green_300),
      Account("KBZ Saving", "••••••5678", 253.13f, R.color.rally_dark_green),
      Account("AYA Saving", "••••••1234", 100.00f, R.color.rally_green_700),
      Account("Home Savings", "••••••5678", 2_215.13f, R.color.rally_green_500),
      Account("Trips", "••••••1234", 52.00f, R.color.rally_green_300),
      Account("ABC Bank Deposit", "••••••5678", 1000.00f, R.color.rally_dark_green),
      Account("IRA Savings", "••••••1234", 200.50f, R.color.rally_green_500),
      Account("Yoma Savings", "••••••5678", 1000.00f, R.color.rally_green_300)
  )

  private val bills = listOf(
      Bill("RedPay Credit", "Due Jan 28", 45.63f, R.color.rally_yellow),
      Bill("Rent", "Due Feb 9", 1200.00f, R.color.rally_orange),
      Bill("TabFine Credit", "Due Feb 22", 87.33f, R.color.rally_yellow_500),
      Bill("ABC Loans", "Due March 30", 400.00f, R.color.rally_yellow),
      Bill("Bank Loans", "Due Feb 2", 1000.00f, R.color.rally_orange),
      Bill("Laptop Credit", "Due Apr 18", 50.00f, R.color.rally_yellow_500),
      Bill("ABC Credit", "Due Sep 29", 128.50f, R.color.rally_yellow),
      Bill("AYA Loans", "Due Nov 8", 800.00f, R.color.rally_orange),
      Bill("IRA Credit", "Due Oct 3", 300.00f, R.color.rally_yellow_500),
      Bill("KBZ Loans", "Due Dec 10", 60.00f, R.color.rally_yellow)
  )

  private val budgets = listOf(
      Budget("Coffee Shops", 1000f, 500f, R.color.rally_blue),
      Budget("Groceries", 1000f, 200f, R.color.rally_purple),
      Budget("Restaurants", 1000f, 100f, R.color.rally_blue_100),
      Budget("Clothing", 1000f, 800f, R.color.rally_blue),
      Budget("Foods", 1000f, 1000f, R.color.rally_purple),
      Budget("Furniture", 1000f, 400f, R.color.rally_blue_100),
      Budget("Laptop and Mobile", 1000f, 50f, R.color.rally_blue),
      Budget("Presents", 1000f, 100f, R.color.rally_purple),
      Budget("Repair", 1000f, 500f, R.color.rally_blue_100),
      Budget("Mobile Data", 1000f, 600f, R.color.rally_blue)
  )


  val accountOverView =
    AccountOverView(accounts.sumByDouble { it.amount.toDouble() }.toFloat(), accounts)

  val billOverView = BillOverView(bills.sumByDouble { it.amount.toDouble() }.toFloat(), bills)

  val budgetOverView =
    BudgetOverview(budgets, budgets.sumByDouble { it.total.toDouble() }.toFloat())

}