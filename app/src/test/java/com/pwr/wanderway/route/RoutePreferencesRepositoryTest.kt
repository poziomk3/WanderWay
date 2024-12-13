package com.pwr.wanderway.route

import app.cash.turbine.test
import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
import com.pwr.wanderway.data.repository.RoutePreferencesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class RoutePreferencesRepositoryTest {

    private lateinit var routePreferencesManager: RoutePreferencesManager
    private lateinit var routePreferencesRepository: RoutePreferencesRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        routePreferencesManager = mock()
        routePreferencesRepository = RoutePreferencesRepository(routePreferencesManager)
    }

    @Test
    fun `getDefaultOptionForCategory returns correct default option for all categories`() =
        runTest(testDispatcher) {
            preferenceConfigurations.forEach { config ->
                val category = config.category
                val expectedDefault = config.defaultOption

                val actualDefault = routePreferencesRepository.getDefaultOptionForCategory(category)
                assertEquals(
                    "Default option for category ${category.label} should be ${expectedDefault.label}",
                    expectedDefault,
                    actualDefault
                )
            }
        }

    @Test
    fun `savePreference saves the correct preference for all categories`() =
        runTest(testDispatcher) {
            preferenceConfigurations.forEach { config ->
                val category = config.category
                val selectedOption = config.options[0]

                routePreferencesRepository.savePreference(category, selectedOption)

                verify(routePreferencesManager).savePreference(category.label, selectedOption.label)
            }
            verifyNoMoreInteractions(routePreferencesManager)
        }

    @Test
    fun `retrieve saved preferences returns the correct saved preference for all categories`() =
        runTest(testDispatcher) {
            preferenceConfigurations.forEach { config ->
                val category = config.category
                val selectedOption = config.options[0]

                whenever(routePreferencesManager.getPreferenceFlow(category.label))
                    .thenReturn(flowOf(selectedOption.label))

                val activePreferenceFlow =
                    routePreferencesRepository.getActivePreferenceFlow(category)

                activePreferenceFlow.test {
                    val actualOption = awaitItem()
                    assertEquals(
                        "Retrieved preference for category ${category.label} should match the saved option ${selectedOption.label}",
                        selectedOption,
                        actualOption
                    )
                    cancelAndIgnoreRemainingEvents()
                }
            }
        }
}
