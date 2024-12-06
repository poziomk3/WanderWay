import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import com.pwr.wanderway.coreViewModels.RouteViewModel
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.presentation.commons.RowSelectorConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BuildYourRouteViewModel(
    private val routeViewModel: RouteViewModel
) {

    private val _collectedPointsOfInterest = MutableStateFlow<List<RowSelectorConfig>>(emptyList())
    val collectedPointsOfInterest: StateFlow<List<RowSelectorConfig>> = _collectedPointsOfInterest

    init {
        initializePointsOfInterest()
    }

    private fun initializePointsOfInterest() {
        _collectedPointsOfInterest.value = routeViewModel.collectedPointsOfInterest.value.map {
            createRowSelectorConfig(it)
        }
    }

    fun onCreateRoute() {
        routeViewModel.generateRoutes()
    }

    private fun createRowSelectorConfig(pointOfInterest: PointOfInterest): RowSelectorConfig {
        return RowSelectorConfig(
            label = pointOfInterest.name,
            onClick = { removePointOfInterest(pointOfInterest) },
            icon = Icons.Filled.Clear
        )
    }

    fun reorderItems(fromIndex: Int, toIndex: Int) {
        val currentList = _collectedPointsOfInterest.value.toMutableList()
        val item = currentList.removeAt(fromIndex)
        currentList.add(toIndex, item)
        _collectedPointsOfInterest.value = currentList
    }


    private fun removePointOfInterest(pointOfInterest: PointOfInterest) {
        routeViewModel.removePointOfInterest(pointOfInterest)
        // Update the local state by filtering out the item
        _collectedPointsOfInterest.value = _collectedPointsOfInterest.value.filterNot { config ->
            config.label == pointOfInterest.name
        }
    }
}
