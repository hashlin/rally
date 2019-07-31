package io.material.rally.ui.overview

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

import io.material.rally.R
import io.material.rally.ui.overview.adapter.Account
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import kotlinx.android.synthetic.main.layout_account_overview.rv_account_overview

class OverviewFragment : Fragment() {

  private val accountAdapter by lazy {
    AccountOverviewAdapter()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_overview, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setUpAccountRecyclerView()
  }

  private fun setUpAccountRecyclerView(){
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      adapter = accountAdapter
      addItemDecoration(DividerItemDecoration(requireContext(),RecyclerView.VERTICAL).apply {
        val divider = ContextCompat.getDrawable(requireContext(),R.drawable.divider)!!
        val margin = resources.getDimensionPixelSize(R.dimen.spacing_medium)
        setDrawable(InsetDrawable(divider, margin, 0, margin, 0))
      })
    }
    accountAdapter.submitList(listOf(Account(""),Account(""),Account("")))

  }

  private fun setUpBillRecyclerView(){
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      adapter = AccountOverviewAdapter()
    }
  }

  private fun setUpBudgetRecyclerView(){
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      adapter = null
    }
  }

}
