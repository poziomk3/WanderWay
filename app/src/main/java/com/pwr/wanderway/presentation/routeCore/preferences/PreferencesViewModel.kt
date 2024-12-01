package com.pwr.wanderway.presentation.routeCore.preferences

import androidx.lifecycle.ViewModel
import com.pwr.wanderway.data.model.DropdownConfig

class PreferencesViewModel: ViewModel(){
    val dropdownConfigs = listOf(
        DropdownConfig(
            label = "Duration of trip",
            options = listOf("Half-day", "Full-day", "Weekend"),
            defaultOption = "Full-day"
        ),
        DropdownConfig(
            label = "Type of sightseeing",
            options = listOf("Historical", "Nature", "Modern attractions"),
            defaultOption = "Nature"
        ),
        DropdownConfig(
            label = "Group type",
            options = listOf("Solo", "Family", "Friends", "Tour group"),
            defaultOption = "Solo"
        ),
        DropdownConfig(
            label = "Difficulty level",
            options = listOf("Easy", "Moderate", "Challenging"),
            defaultOption = "Easy"
        ),
        DropdownConfig(
            label = "Preferred weather",
            options = listOf("Any", "Sunny", "Cloudy", "Cool"),
            defaultOption = "Any"
        ),
        DropdownConfig(
            label = "Preferred transport",
            options = listOf("Car", "Public transport", "Bike", "Walking"),
            defaultOption = "Car"
        ),
        DropdownConfig(
            label = "Meal preferences",
            options = listOf("Quick snacks", "Local cuisine", "Packed lunch"),
            defaultOption = "Quick snacks"
        ),
        DropdownConfig(
            label = "Budget range",
            options = listOf("Low", "Medium", "High"),
            defaultOption = "Medium"
        ),
        DropdownConfig(
            label = "Activity preferences",
            options = listOf("Relaxing", "Adventurous", "Educational"),
            defaultOption = "Relaxing"
        )
    )
}