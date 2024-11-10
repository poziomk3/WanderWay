package com.pwr.wanderway.presentation.routeCore.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SearchBarWithSuggestions() {
    var searchText by remember { mutableStateOf("") }
    var showSuggestions by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                    showSuggestions = newText.isNotEmpty()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search for a location") }
            )
        }
    }
}