package com.sisain.capstone.ui.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sisain.capstone.R
import com.sisain.capstone.data.model.Recipe


class RecipeAdapter(private val recipes: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgRecipe: ImageView = view.findViewById(R.id.img_item_photo)
        val tvName: TextView = view.findViewById(R.id.tv_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return RecipeViewHolder(view)
    }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            val recipe = recipes[position]
            holder.tvName.text = recipe.name

            Glide.with(holder.itemView.context)
                .load(recipe.imageUrl)
                .placeholder(R.layout.item_card)
                .into(holder.imgRecipe)
        }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
