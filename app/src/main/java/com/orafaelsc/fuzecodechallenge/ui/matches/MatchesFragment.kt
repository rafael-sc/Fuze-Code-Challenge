package com.orafaelsc.fuzecodechallenge.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orafaelsc.fuzecodechallenge.databinding.FragmentMatchesBinding
import com.orafaelsc.fuzecodechallenge.di.MatchesModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules

class MatchesFragment : Fragment() {

    private var binding: FragmentMatchesBinding? = null
    private val viewModel: MatchesViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentMatchesBinding
        .inflate(inflater, container, false)
        .apply {
            binding = this
        }.also {
            loadKoinModules(MatchesModule.module)
            setupView()
            initDataObserver()
        }.root

    private fun initDataObserver() {
    }

    private fun setupView() {
    }

    companion object {
        fun newInstance() = MatchesFragment()
    }
}
