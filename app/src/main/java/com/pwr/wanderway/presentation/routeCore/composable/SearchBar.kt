package com.pwr.wanderway.presentation.routeCore.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R


data class SearchBarItem(val id: String, val name: String, val additionalInfo: String?)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    suggestions: List<SearchBarItem>,
    onSearch: (String) -> Unit,
    query: String
) {
    val textFieldState = rememberTextFieldState()
    var expanded by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(query) {
        if (textFieldState.text != query) {
            textFieldState.setTextAndPlaceCursorAtEnd(query)
        }
    }


    var filteredSuggestions by rememberSaveable { mutableStateOf(suggestions) }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(250.dp)
            .testTag("SearchBarRoot")
    ) {
        DockedSearchBar(
            modifier =
            Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
                .semantics {
                    traversalIndex = 0f
                },
            shadowElevation = 8.dp,
            shape = MaterialTheme.shapes.medium,
            inputField = {
                SearchBarDefaults.InputField(
                    state = textFieldState,
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(stringResource(R.string.add_location_search_placeholder)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier.testTag("SearchInput")
                )
            },

            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            filteredSuggestions =
                suggestions.filter { suggestion ->
                    suggestion.name.contains(textFieldState.text, ignoreCase = true)
                }

            val limitedSuggestions = filteredSuggestions


            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(4.dp)
                    .testTag("SuggestionsContainer")
            ) {
                limitedSuggestions.forEach { suggestion ->
                    ListItem(
                        headlineContent = { Text(suggestion.name) },

                        supportingContent = {
                            Text(
                                suggestion.additionalInfo ?: "", maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier =
                        Modifier
                            .clickable {
                                expanded = false
                                textFieldState.setTextAndPlaceCursorAtEnd(suggestion.name)
                                onSearch(suggestion.id)
                            }
                            .fillMaxWidth()
                            .testTag("SuggestionItem-${suggestion.id}")
                    )
                }
            }
        }
    }
}




