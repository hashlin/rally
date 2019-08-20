package io.material.rally.ui.detail

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import io.material.rally.R
import io.material.rally.data.DataProvider
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import kotlinx.android.synthetic.main.fragment_detail.rv_account

/**
 * Created by Chan Myae Aung on 8/4/19.
 */
class DetailFragment : Fragment() {

  private val accountAdapter by lazy { AccountOverviewAdapter(isSingleLine = false) }

  private val decoration by lazy {
    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
      val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
      val margin = resources.getDimensionPixelSize(R.dimen.spacing_medium)
      setDrawable(InsetDrawable(divider, margin, 0, margin, 0))
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_detail, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpRecyclerView()
  }

  private fun setUpRecyclerView() {
    Log.i("TAG","setting up detail fragment")
    rv_account.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(decoration)
      adapter = accountAdapter
    }
    accountAdapter.submitList(DataProvider.accountOverView.accounts)
  }
}
