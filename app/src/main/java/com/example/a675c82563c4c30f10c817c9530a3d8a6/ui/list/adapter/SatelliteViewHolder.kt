package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.a675c82563c4c30f10c817c9530a3d8a6.R
import com.example.a675c82563c4c30f10c817c9530a3d8a6.databinding.ItemSatelliteBinding
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Satellite

class SatelliteViewHolder(
    private val onClick: (Int) -> Unit,
    private val binding: ItemSatelliteBinding
):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Satellite) {
        itemView.setOnClickListener {
            onClick(item.id)
        }
        binding.tvName.text = item.name
        setActive(item.active)
    }

    private fun setActive(active: Boolean) {
        if (active) {
            binding.viewIconActivity.background.setTint(Color.GREEN)
            binding.tvActivity.text = itemView.context.getString(R.string.active)
        }
        else {
            binding.viewIconActivity.background.setTint(Color.RED)
            binding.tvActivity.text = itemView.context.getString(R.string.passive)
        }
    }
}