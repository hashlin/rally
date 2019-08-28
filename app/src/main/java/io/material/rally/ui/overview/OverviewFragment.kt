package io.material.rally.ui.overview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.button.MaterialButton
import io.material.rally.R
import io.material.rally.data.DataProvider
import io.material.rally.extension.getRallyItemDecoration
import io.material.rally.extension.toUSDFormatted
import io.material.rally.ui.MainActivity
import io.material.rally.ui.TabItem.ACCOUNT
import io.material.rally.ui.TabItem.BILL
import io.material.rally.ui.TabItem.BUDGET
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import io.material.rally.ui.overview.adapter.AlertAdapter
import io.material.rally.ui.overview.adapter.BillAdapter
import io.material.rally.ui.overview.adapter.BudgetAdapter
import io.material.rally_line_indicator.data.RallyLineIndicatorData
import io.material.rally_line_indicator.data.RallyLineIndicatorPortion
import kotlinx.android.synthetic.main.fragment_overview.content
import kotlinx.android.synthetic.main.layout_account_overview.account_line_indicator
import kotlinx.android.synthetic.main.layout_account_overview.btn_acc_see_all
import kotlinx.android.synthetic.main.layout_account_overview.rv_account_overview
import kotlinx.android.synthetic.main.layout_account_overview.tv_account_amount
import kotlinx.android.synthetic.main.layout_alert.rv_alerts
import kotlinx.android.synthetic.main.layout_bill_overview.bill_line_indicator
import kotlinx.android.synthetic.main.layout_bill_overview.btn_bill_see_all
import kotlinx.android.synthetic.main.layout_bill_overview.rv_bill_overview
import kotlinx.android.synthetic.main.layout_bill_overview.tv_bill_amount
import kotlinx.android.synthetic.main.layout_budget_overview.btn_budget_see_all
import kotlinx.android.synthetic.main.layout_budget_overview.budget_line_indicator
import kotlinx.android.synthetic.main.layout_budget_overview.rv_budget_overview
import kotlinx.android.synthetic.main.layout_budget_overview.tv_budget_amount
import kotlinx.android.synthetic.main.layout_budget_overview.tv_total_budget

/**
 * Created by Chan Myae Aung on 7/30/19.
 */
class OverviewFragment : Fragment() {

  private val accountAdapter by lazy { AccountOverviewAdapter(isSingleLine = false) }
  private val billAdapter by lazy { BillAdapter() }
  private lateinit var budgetAdapter: BudgetAdapter
  private lateinit var alertsAdapter: AlertAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_overview, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpAlertRecyclerView()
    setUpAccountRecyclerView()
    setUpBillRecyclerView()
    setUpBudgetRecyclerView()
    setUpClickListener()

    if (savedInstanceState == null) runEnterAnimation()
  }

  private fun setUpClickListener() {
    btn_acc_see_all.setOnClickListener {
      getParentActivity<MainActivity>().navigateToTabs(ACCOUNT)
    }
    btn_bill_see_all.setOnClickListener {
      getParentActivity<MainActivity>().navigateToTabs(BILL)
    }
    btn_budget_see_all.setOnClickListener {
      getParentActivity<MainActivity>().navigateToTabs(BUDGET)
    }
  }

  private fun setUpAlertRecyclerView() {
    alertsAdapter = AlertAdapter(clickListener = { showDialogForAlert(it) })
    rv_alerts.apply {
      layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

      val snapHelper = PagerSnapHelper()
      snapHelper.attachToRecyclerView(this)

      setHasFixedSize(true)
      adapter = alertsAdapter
    }

    val alerts = DataProvider.alerts
    alertsAdapter.submitList(alerts)
  }

  private fun setUpAccountOverview(lineIndicatorData: RallyLineIndicatorData) {
    account_line_indicator.setData(lineIndicatorData)
    tv_account_amount.text = DataProvider.accountOverView.total.toUSDFormatted()
  }

  private fun setUpAccountRecyclerView() {
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = accountAdapter
    }
    accountAdapter.submitList(DataProvider.accountOverView.accounts.take(3))

    val portions = DataProvider.accountOverView.accounts.take(3)
        .map {
          RallyLineIndicatorPortion(
              name = it.name,
              value = it.amount,
              colorInt = ContextCompat.getColor(requireContext(), it.color)
          )
        }
    setUpAccountOverview(RallyLineIndicatorData(portions = portions))
  }

  private fun setUpBillOverview(lineIndicatorData: RallyLineIndicatorData) {
    bill_line_indicator.setData(lineIndicatorData)
    tv_bill_amount.text = DataProvider.billOverView.total.toUSDFormatted()
  }

  private fun setUpBillRecyclerView() {
    rv_bill_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = billAdapter
    }

    val billOverviews = DataProvider.billOverView.bills.take(3)
    billAdapter.submitList(billOverviews)

    val portions = billOverviews
        .map {
          RallyLineIndicatorPortion(
              name = it.name,
              value = it.amount,
              colorInt = ContextCompat.getColor(requireContext(), it.color)
          )
        }
        .sortedBy { it.colorInt }
    setUpBillOverview(RallyLineIndicatorData(portions))
  }

  private fun setUpBudgetOverview(lineIndicatorData: RallyLineIndicatorData) {
    budget_line_indicator.setData(lineIndicatorData)
    tv_budget_amount.text = DataProvider.budgetOverView.budgets.sumByDouble { it.spend.toDouble() }
        .toFloat()
        .toUSDFormatted()
    tv_total_budget.text = "/ ${lineIndicatorData.maxValue?.toUSDFormatted()}"
  }

  private fun setUpBudgetRecyclerView() {

    val isTwoLine = requireContext().resources.configuration.smallestScreenWidthDp >= 600
    budgetAdapter = BudgetAdapter(isTwoLine)

    rv_budget_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(getRallyItemDecoration())
      adapter = budgetAdapter
    }

    budgetAdapter.submitList(DataProvider.budgetOverView.budgets.take(3))

    val portions = DataProvider.budgetOverView.budgets
        .map {
          RallyLineIndicatorPortion(
              name = it.name,
              value = it.spend,
              colorInt = ContextCompat.getColor(requireContext(), it.color)
          )
        }

    setUpBudgetOverview(
        RallyLineIndicatorData(
            portions = portions,
            maxValue = DataProvider.budgetOverView.total
        )
    )
  }

  private fun showDialogForAlert(alert: String) {
    val dialog = Dialog(requireContext(),R.style.Widget_Rally_AlertDialog).apply {
      setCancelable(true)
      setContentView(R.layout.dialog_alert)

      findViewById<TextView>(R.id.tv_alert_title).text = alert
      findViewById<MaterialButton>(R.id.btnDismiss).setOnClickListener {
        dismiss()
      }
    }
    dialog.show()
  }

  private fun runEnterAnimation() {
    content.post {
      var duration = 300L
      for (i in 0 until content.childCount) {
        duration += 100
        val child = content.getChildAt(i)
        child.translationY += 400
        child.alpha = 0f
        child.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .setListener(object : AnimatorListenerAdapter() {
              override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
              }
            })
            .start()
      }

    }
  }

}

fun <T> Fragment.getParentActivity(): T {
  return requireActivity() as T
}

