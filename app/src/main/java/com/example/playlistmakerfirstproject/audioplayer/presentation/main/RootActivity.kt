package com.example.playlistmakerfirstproject.audioplayer.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.playlistmakerfirstproject.R
import com.example.playlistmakerfirstproject.audioplayer.presentation.playlistsCreation.BackNavigationListenerRoot
import com.example.playlistmakerfirstproject.audioplayer.presentation.playlistsCreation.PlaylistCreationFragment
import com.example.playlistmakerfirstproject.databinding.ActivityRootBinding
import kotlinx.coroutines.launch

class RootActivity : AppCompatActivity(), BackNavigationListenerRoot {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.playlistDetailsFragment -> hideBottomNavigation()
                R.id.playlistFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }


    private fun showBottomNavigation() {
        binding.bottomNavigationView.visibility = android.view.View.VISIBLE
        binding.devider.visibility = android.view.View.VISIBLE

    }

    private fun hideBottomNavigation() {
        binding.bottomNavigationView.visibility = android.view.View.GONE
        binding.devider.visibility = android.view.View.GONE
    }


    // navigation functions
    override fun onBackPressed() {
        onNavigateBack(false)

    }

    private fun backCheckFragment() {
        val currentNavHostFragment = supportFragmentManager.findFragmentById(R.id.container_view)
        if (currentNavHostFragment is NavHostFragment) {
            val childFragmentManager = currentNavHostFragment.childFragmentManager
            val currentFragment = childFragmentManager.primaryNavigationFragment
            if (currentFragment is PlaylistCreationFragment) {
                val playlistArg = currentFragment.arguments?.getSerializable("EDIT_PLAYLIST")
                if (playlistArg != null) {
                    super.onBackPressed()
                } else {
                    lifecycleScope.launch {
                        currentFragment.navigateBack()
                    }
                }
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onNavigateBack(isEmpty: Boolean) {
        if (isEmpty) super.onBackPressed()
        else backCheckFragment()
    }


}
