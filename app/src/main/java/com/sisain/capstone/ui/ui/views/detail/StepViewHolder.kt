package com.sisain.capstone.ui.ui.views.detail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sisain.capstone.R
import com.sisain.capstone.data.model.Step

class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvStepNumber: TextView = itemView.findViewById(R.id.tv_step_number)
    private val tvStepDescription: TextView = itemView.findViewById(R.id.tv_step_description)

    fun bind(step: Step) {
        tvStepNumber.text = "Step ${step.stepNumber}"
        tvStepDescription.text = step.description
    }
}
