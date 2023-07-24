package com.example.a675c82563c4c30f10c817c9530a3d8a6.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a675c82563c4c30f10c817c9530a3d8a6.databinding.ItemSatelliteBinding
import com.example.a675c82563c4c30f10c817c9530a3d8a6.domain.model.Satellite

class SatelliteListAdapter(private val onClickItem: (id: Int, name: String) -> Unit):
    RecyclerView.Adapter<SatelliteViewHolder>() {

    private val items = arrayListOf<Satellite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSatelliteBinding.inflate(inflater, parent, false)
        return SatelliteViewHolder(onClickItem, binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addData(list: List<Satellite>) {
        items.addAll(list)
        notifyItemRangeChanged(items.size - list.size, list.size)
    }
}