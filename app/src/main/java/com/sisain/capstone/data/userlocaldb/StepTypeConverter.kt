package com.sisain.capstone.data.userlocaldb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sisain.capstone.data.model.Step

class StepTypeConverter {
    @TypeConverter
    fun fromStepList(steps: List<Step>): String {
        return Gson().toJson(steps)
    }

    @TypeConverter
    fun toStepList(stepsString: String): List<Step> {
        val type = object : TypeToken<List<Step>>() {}.type
        return Gson().fromJson(stepsString, type)
    }
}