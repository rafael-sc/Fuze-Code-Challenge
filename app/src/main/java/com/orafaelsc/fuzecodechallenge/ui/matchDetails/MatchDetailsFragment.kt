package com.orafaelsc.fuzecodechallenge.ui.matchDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.orafaelsc.fuzecodechallenge.databinding.FragmentMatchesBinding
import com.orafaelsc.fuzecodechallenge.di.MatchesModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext

class MatchDetailsFragment : Fragment() {

    //    private val firstTeamPlayersAdapter: PlayersAdapter by lazy {    }
//    private val secondTeamPlayersAdapter: PlayersAdapter by lazy {    }
    private val args: MatchDetailsFragmentArgs by navArgs()
    private var binding: FragmentMatchesBinding? = null
    private val viewModel: MatchDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMatchesBinding
        .inflate(inflater, container, false)
        .apply {
            binding = this
        }.also {
            GlobalContext.loadKoinModules(MatchesModule.module)
            viewModel.init(matchId = args.argMatchDetail)
            initDataObserver()
        }.root

    private fun initDataObserver() {
    }
}
