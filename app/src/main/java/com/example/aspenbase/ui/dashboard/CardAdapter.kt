package com.example.aspenbase.ui.dashboard

import android.view.*
import androidx.core.content.*
import androidx.recyclerview.widget.*
import com.example.aspenbase.*
import com.example.aspenbase.data.*
import com.example.aspenbase.databinding.*

class CardAdapter(private val cardItemList: List<CardItem>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    internal var onClickItem: ((CardItem) -> Unit) = {}
    internal var onClickFavorite: ((position: Int) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            ItemPopularBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(cardItemList[position])
    }

    override fun getItemCount() = cardItemList.size

    inner class CardViewHolder(private val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.run {
                ivCard.setOnClickListener {
                    onClickItem(cardItemList[adapterPosition])
                }
                imageButtonFavorite.setOnClickListener {
//                    if (adapterPosition != RecyclerView.NO_POSITION) {
//                        val item = cardItemList[adapterPosition]
//                        item.isFavorite = !item.isFavorite
//                        onClickFavorite.invoke(item)
//                        notifyItemChanged(adapterPosition)
                    onClickFavorite(adapterPosition)
//                    }
                }
            }
        }

        fun onBind(cardItem: CardItem) {
            binding.run {
                ivCard.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        cardItem.imageRes
                    )
                )
                tvTitle.text = cardItem.title
                tvRating.text = cardItem.rating.toString()
                imageButtonFavorite.setImageResource(
                    if (cardItem.isFavorite) {
                        R.drawable.baseline_favorite
                    } else {
                        R.drawable.baseline_favorite_border
                    }
                )
            }
        }
    }
}
