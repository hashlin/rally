package io.material.rally.data.model

import kotlin.random.Random

/**
 * Created by Chan Myae Aung on 8/28/19.
 */
data class MonthlyItem(
  val month: Int,
  val name: String,
  val amount: Float,
  val type: ItemType
) {
  val date get() = "$month/${Random.nextInt(30)}/2018"
}

enum class ItemType {
  INCREASE,
  DECREASE
}

