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
import com.google.android.material.snackbar.Snackbar
import com.orafaelsc.fuzecodechallenge.R
import com.orafaelsc.fuzecodechallenge.commom.exceptions.ApiException
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
    private val matchesAdapter: MatchesAdapter by lazy {
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
            setupObserverOnCreated(errorState() to ::errorStateObserver)
        }
    }

    private fun errorStateObserver(exception: Throwable) {
        val message = if (exception is ApiException.UnableToGetMatchesException) {
            resources.getString(R.string.unable_to_get_matches)
        }else{
            resources.getString(R.string.generic_error_message)
        }
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_INDEFINITE,
        ).setAction(R.string.general_retry) {
            viewModel.init()
        }.show()
    }

    private fun navigateToDetailsFragmentObserver(match: Match) {
        findNavController().navigate(
            MatchesFragmentDirections.actionMatchesFragmentToMatchDetailsFragment(match)
        )
    }

    private fun matchesObserver(matches: List<Match>) {
        binding?.run {
            matchesAdapter.setItems(matches)
            recyclerViewList.run {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = matchesAdapter
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
