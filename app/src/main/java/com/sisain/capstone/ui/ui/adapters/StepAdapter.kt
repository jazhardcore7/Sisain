package com.sisain.capstone.ui.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sisain.capstone.R
import com.sisain.capstone.data.model.Step
import com.sisain.capstone.ui.ui.views.detail.StepViewHolder

class StepsAdapter(private val steps: List<Step>) : RecyclerView.Adapter<StepViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step, parent, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = steps[position]
        holder.bind(step)
    }

    override fun getItemCount(): Int = steps.size
}