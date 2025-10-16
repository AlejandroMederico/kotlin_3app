package com.mederico.android.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mederico.android.R


class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private var divider: View = view.findViewById(R.id.divider)

    private var cvCategory : CardView = view.findViewById(R.id.cvCategory)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {
        val color = if (taskCategory.isSelect) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }
        cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context, color))
        itemView.setOnClickListener { onItemSelected(layoutPosition) }
        when(taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_business_category)
                )

            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_other_category)
                )

            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )

            }
        }
    }
}