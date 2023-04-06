package com.test.placeholderusers.users.fragments

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.filters.MediumTest
import com.test.placeholderusers.R
import com.test.placeholderusers.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest // Integration test from userList fragment to user detail fragment
@HiltAndroidTest
@ExperimentalCoroutinesApi
class UserListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testUserListFragmentToUserDetailFragment() {
        launchFragmentInHiltContainer<UserListFragment> {
            val navController = TestNavHostController(requireActivity())
            requireActivity().runOnUiThread { navController.setGraph(R.navigation.navigation) }
            Navigation.setViewNavController(requireView(), navController)

            navController.navigate(R.id.userDetailFragment)
            val destination = navController.currentDestination
            assertNotNull(destination)
            assertEquals(destination!!.id, R.id.userDetailFragment)
        }
    }
}
