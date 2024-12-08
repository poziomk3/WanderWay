package com.pwr.wanderway.data.repository

import app.cash.turbine.test
import com.pwr.wanderway.data.local.RoutePreferencesManager
import com.pwr.wanderway.data.model.preferences.preferenceConfigurations
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
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RoutePreferencesRepositoryTest {

    private val logger: Logger = LoggerFactory.getLogger(RoutePreferencesRepositoryTest::class.java)

    private lateinit var routePreferencesManager: RoutePreferencesManager
    private lateinit var routePreferencesRepository: RoutePreferencesRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        logger.info("Setting up test environment.")
        routePreferencesManager = mock()
        routePreferencesRepository = RoutePreferencesRepository(routePreferencesManager)
    }

    @Test
    fun `getDefaultOptionForCategory returns correct default option for all categories`() =
        runTest(testDispatcher) {
            logger.info("Starting test for default option retrieval for all categories.")
            preferenceConfigurations.forEach { config ->
                val category = config.category
                val expectedDefault = config.defaultOption
                logger.debug("Testing category: ${category.label}, expected default: ${expectedDefault.label}")

                val actualDefault = routePreferencesRepository.getDefaultOptionForCategory(category)
                assertEquals(
                    "Default option for category ${category.label} should be ${expectedDefault.label}",
                    expectedDefault,
                    actualDefault
                )
                logger.info("Verified default option for category: ${category.label}")
            }
            logger.info("Completed test for default option retrieval.")
        }

    @Test
    fun `savePreference saves the correct preference for all categories`() =
        runTest(testDispatcher) {
            logger.info("Starting test for saving preferences for all categories.")
            preferenceConfigurations.forEach { config ->
                val category = config.category
                val selectedOption = config.options[0]
                logger.debug("Saving preference: ${selectedOption.label} for category: ${category.label}")

                routePreferencesRepository.savePreference(category, selectedOption)

                verify(routePreferencesManager).savePreference(category.label, selectedOption.label)
                logger.info("Verified saving preference: ${selectedOption.label} for category: ${category.label}")
            }
            verifyNoMoreInteractions(routePreferencesManager)
            logger.info("Completed test for saving preferences.")
        }

    @Test
    fun `retrieve saved preferences returns the correct saved preference for all categories`() =
        runTest(testDispatcher) {
            logger.info("Starting test for retrieving saved preferences for all categories.")
            preferenceConfigurations.forEach { config ->
                val category = config.category
                val selectedOption = config.options[0]
                logger.debug("Retrieving saved preference: ${selectedOption.label} for category: ${category.label}")

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
                    logger.info("Verified retrieved preference: ${actualOption.label} matches the saved option for category: ${category.label}")
                    cancelAndIgnoreRemainingEvents()
                }
            }
            logger.info("Completed test for retrieving saved preferences.")
        }
}
