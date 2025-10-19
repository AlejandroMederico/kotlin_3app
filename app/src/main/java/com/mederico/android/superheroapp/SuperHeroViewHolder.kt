package com.mederico.android.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mederico.android.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(item: SuperHeroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperHeroName.text = item.name
        Picasso.get().load(item.image.url).into(binding.ivSuperHero)
        binding.root.setOnClickListener { onItemSelected(item.id) }
    }
}