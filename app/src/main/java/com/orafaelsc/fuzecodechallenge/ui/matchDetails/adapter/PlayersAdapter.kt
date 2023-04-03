package com.orafaelsc.fuzecodechallenge.ui.matchDetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.orafaelsc.fuzecodechallenge.R
import com.orafaelsc.fuzecodechallenge.ui.matches.model.Player

class PlayersAdapter(
    private val orientation: LayoutOrientation
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = if (orientation == LayoutOrientation.LEFT) {
            R.layout.player_item_left
        } else {
            R.layout.player_item_right
        }
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (holder as? PlayerViewHolder)?.onBind(item)
    }

    override fun getItemCount(): Int = items.size

    class PlayerViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val textViewNickName: TextView = itemView.findViewById(R.id.textViewNickName)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val imageViewPhoto: ImageView = itemView.findViewById(R.id.imageViewPhoto)

        fun onBind(item: Player) {
            imageViewPhoto.run {
                load(item.image) {
                    crossfade(true)
                    placeholder(R.drawable.img_team_placeholder)
                    fallback(R.drawable.img_team_placeholder)
                    transformations(RoundedCornersTransformation())
                }
                contentDescription =
                    resources.getString(R.string.content_player_image, item.nickName)
            }
            textViewName.text = item.name
            textViewNickName.text = item.nickName
        }
    }

    fun setItems(list: List<Player>) {
        this.items = list
    }
}
