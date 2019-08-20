package io.material.rally.data.model

import androidx.annotation.ColorRes
import io.material.rally.extension.toMoneyFormatted

/**
 * Created by Chan Myae Aung on 8/15/19.
 */
data class Budget(
  val name: String,
  val total: Float,
  val spend: Float, @ColorRes val color: Int
) {
  val left = total - spend
  val desc = "$${spend.toMoneyFormatted()} / $${total.toMoneyFormatted()}"
}

data class BudgetOverview(
  val total: String,
  val budgets: List<Budget>
)