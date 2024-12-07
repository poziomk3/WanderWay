
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.presentation.commons.RowSelectorConfig
import com.pwr.wanderway.presentation.routeCore.RouteViewModel
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


    private fun createRowSelectorConfig(pointOfInterest: PointOfInterest): RowSelectorConfig {
        return RowSelectorConfig(
            label = pointOfInterest.name,
            onClick = { removePointOfInterest(pointOfInterest) },
            icon = Icons.Filled.Clear
        )
    }


    private fun removePointOfInterest(pointOfInterest: PointOfInterest) {
        routeViewModel.removePointOfInterest(pointOfInterest)
        _collectedPointsOfInterest.value = _collectedPointsOfInterest.value.filterNot { config ->
            config.label == pointOfInterest.name
        }
    }
}
