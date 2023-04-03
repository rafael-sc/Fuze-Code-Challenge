package com.orafaelsc.fuzecodechallenge.ui.matchDetails

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.orafaelsc.fuzecodechallenge.R
import com.orafaelsc.fuzecodechallenge.commom.extensions.setupObserverOnCreated
import com.orafaelsc.fuzecodechallenge.databinding.FragmentMatchDetailsBinding
import com.orafaelsc.fuzecodechallenge.di.MatchesModule
import com.orafaelsc.fuzecodechallenge.ui.matchDetails.adapter.LayoutOrientation
import com.orafaelsc.fuzecodechallenge.ui.matchDetails.adapter.PlayersAdapter
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext

class MatchDetailsFragment : Fragment() {

    private val firstTeamPlayersAdapter: PlayersAdapter = PlayersAdapter(LayoutOrientation.LEFT)
    private val secondTeamPlayersAdapter: PlayersAdapter = PlayersAdapter(LayoutOrientation.RIGHT)
    private val args: MatchDetailsFragmentArgs by navArgs()
    private var binding: FragmentMatchDetailsBinding? = null
    private val viewModel: MatchDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMatchDetailsBinding
        .inflate(inflater, container, false)
        .apply {
            binding = this
        }.also {
            GlobalContext.loadKoinModules(MatchesModule.module)
            viewModel.init(match = args.argMatchDetail)
            initViews()
            initDataObserver()
        }.root

    private fun initViews() {
        binding?.imageViewBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initDataObserver() {
        with(viewModel) {
            setupObserverOnCreated(loadingState() to ::loadingStateObserver)
            setupObserverOnCreated(matchDetails() to ::matchDetailsObserver)
            setupObserverOnCreated(firstTeamPlayers() to ::firsTeamPlayersObserver)
            setupObserverOnCreated(secondTeamPlayers() to ::secondTeamPlayersObserver)
        }
    }

    private fun matchDetailsObserver(match: Match) {
        binding?.run {
            textViewTitle.text = match.description

            imageViewFirstTeamLogo.run {
                load(match.firstTeam.iconUrl) {
                    crossfade(true)
                    placeholder(R.drawable.img_team_placeholder)
                    fallback(R.drawable.img_team_placeholder)
                }
                contentDescription =
                    resources.getString(R.string.content_team_logo, match.firstTeam.name)
            }
            textViewFirstTeamName.text = match.firstTeam.name

            imageViewSecondTeamLogo.run {
                load(match.secondTeam.iconUrl) {
                    crossfade(true)
                    placeholder(R.drawable.img_team_placeholder)
                    fallback(R.drawable.img_team_placeholder)
                    background = null
                }

                contentDescription =
                    resources.getString(R.string.content_team_logo, match.secondTeam.name)
            }
            textViewSecondTeamName.text = match.secondTeam.name

            textViewMatchTime.run {
                text = match.starTimeText
                val color = if (match.starTimeText == resources.getString(R.string.now)) {
                    R.color.red
                } else {
                    R.color.white_20
                }
                backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this.context,
                            color
                        )
                    )
            }
        }
    }

    private fun firsTeamPlayersObserver(players: List<Player>) {
        binding?.run {
            firstTeamPlayersAdapter.setItems(players)
            recyclerViewFirstTeamPlayers.run {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = firstTeamPlayersAdapter
            }
        }
    }

    private fun secondTeamPlayersObserver(players: List<Player>) {
        binding?.run {
            secondTeamPlayersAdapter.setItems(players)
            recyclerViewSecondTeamPlayers.run {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = secondTeamPlayersAdapter
            }
        }
    }

    private fun loadingStateObserver(isLoading: Boolean) {
        binding?.run {
            recyclerViewFirstTeamPlayers.isVisible = isLoading.not()
            recyclerViewSecondTeamPlayers.isVisible = isLoading.not()
            progressBar.isVisible = isLoading
        }
    }
}
