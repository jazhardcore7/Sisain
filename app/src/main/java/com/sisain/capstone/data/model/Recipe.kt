package com.sisain.capstone.data.model

data class Recipe(
    val name: String,
    val imageResId: Int,
    val steps: List<Step>
)

data class StepDet(val stepNumber: Int, val description: String)

data class Step(
    val stepNumber: Int,
    val description: String

)