package com.orafaelsc.fuzecodechallenge.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orafaelsc.fuzecodechallenge.commom.extensions.setupObserverOnCreated
import com.orafaelsc.fuzecodechallenge.databinding.FragmentMatchesBinding
import com.orafaelsc.fuzecodechallenge.di.MatchesModule
import com.orafaelsc.fuzecodechallenge.ui.matches.adapter.MatchesAdapter
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules

class MatchesFragment : Fragment() {

    private var binding: FragmentMatchesBinding? = null
    private val viewModel: MatchesViewModel by viewModel()
    private val forecastAdapter: MatchesAdapter by lazy {
        MatchesAdapter {
            context?.run {
                viewModel.onAdapterItemClick(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMatchesBinding
        .inflate(inflater, container, false)
        .apply {
            binding = this
        }.also {
            loadKoinModules(MatchesModule.module)
            if (savedInstanceState == null) {
                viewModel.init()
            }
            initDataObserver()
        }.root

    private fun initDataObserver() {
        with(viewModel) {
            setupObserverOnCreated(matches() to ::matchesObserver)
            setupObserverOnCreated(loadingState() to ::loadingStateObserver)
            setupObserverOnCreated(
                navigateToDetailsFragment() to ::navigateToDetailsFragmentObserver
            )
        }
    }

    private fun navigateToDetailsFragmentObserver(matchId: String) {
        findNavController().navigate(
            MatchesFragmentDirections.actionMatchesFragmentToMatchDetailsFragment(
                matchId
            )
        )
    }

    private fun matchesObserver(matches: List<Match>) {
        binding?.run {
            forecastAdapter.setItems(matches)
            recyclerViewList.run {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = forecastAdapter
            }
        }
    }

    private fun loadingStateObserver(isLoading: Boolean) {
        binding?.run {
            recyclerViewList.isVisible = isLoading.not()
            progressBar.isVisible = isLoading
        }
    }
}
