package com.mederico.android.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mederico.android.R

class SuperHeroAdapter(
    var superHeroList: List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superHeroList: List<SuperHeroItemResponse>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(
        holder: SuperHeroViewHolder,
        position: Int
    ) {
        holder.bind(superHeroList[position], onItemSelected)
    }

    override fun getItemCount() = superHeroList.size
}