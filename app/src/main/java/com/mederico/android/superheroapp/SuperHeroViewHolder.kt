package com.mederico.android.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mederico.android.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(item: SuperHeroItemResponse){
        binding.tvSuperHeroName.text = item.name
    }
}