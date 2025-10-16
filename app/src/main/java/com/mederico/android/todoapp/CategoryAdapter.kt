package com.mederico.android.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mederico.android.R

class CategoryAdapter(private val category: List<TaskCategory>, private val onItemSelected: (Int) -> Unit) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        holder.render(category[position], onItemSelected)
    }

    override fun getItemCount()= category.size
}