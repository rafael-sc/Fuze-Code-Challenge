package com.orafaelsc.fuzecodechallenge.ui.matches.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.orafaelsc.fuzecodechallenge.R
import com.orafaelsc.fuzecodechallenge.commom.AdapterItemClickListener
import com.orafaelsc.fuzecodechallenge.databinding.MatchItemBinding
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Match

class MatchesAdapter(
    private val itemClickListener: AdapterItemClickListener = {}
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = listOf<Match>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MatchViewHolder(
            MatchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (holder as? MatchViewHolder)?.onBind(item, position)
    }

    override fun getItemCount(): Int = items.size

    class MatchViewHolder(
        private val binding: MatchItemBinding,
        private val itemClickListener: AdapterItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Match, position: Int) {
            binding.run {
                root.setOnClickListener {
                    itemClickListener(position)
                }

                imageViewFirstTeamLogo.run {
                    load(item.firstTeam.iconUrl) {
                        crossfade(true)
                        placeholder(R.drawable.img_team_placeholder)
                        fallback(R.drawable.img_team_placeholder)
                    }
                    contentDescription =
                        resources.getString(R.string.content_team_logo, item.firstTeam.name)
                }
                textViewFirstTeamName.text = item.firstTeam.name

                imageViewSecondTeamLogo.run {
                    load(item.secondTeam.iconUrl) {
                        crossfade(true)
                        placeholder(R.drawable.img_team_placeholder)
                        fallback(R.drawable.img_team_placeholder)
                        background = null
                    }

                    contentDescription =
                        resources.getString(R.string.content_team_logo, item.secondTeam.name)
                }
                textViewSecondTeamName.text = item.secondTeam.name

                imageViewLeagueLogo.run {
                    if (item.leagueLogo.isNotEmpty()) {
                        load(item.leagueLogo) {
                            placeholder(R.drawable.img_team_placeholder)
                        }
                        background = null
                    } else {
                        isGone = true
                    }
                }
                textViewLeagueName.text = item.description
                textViewMatchTime.run {
                    text = item.starTimeText
                    val color = if (item.starTimeText == resources.getString(R.string.now)) {
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
    }

    fun setItems(list: List<Match>) {
        this.items = list
    }
}
