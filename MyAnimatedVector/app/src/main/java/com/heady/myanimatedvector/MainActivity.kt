package com.heady.myanimatedvector

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.heady.myanimatedvector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemSelectedListener { it: MenuItem ->
            navigateToScreen(
                menuItem = it,
                navController = navController
            )
            true
        }
    }

    private fun navigateToScreen(
        menuItem: MenuItem,
        navController: NavController
    ) {
        val options = NavOptions.Builder()
            .setPopUpTo(
                destinationId = R.id.blankDest,
                inclusive = false,
                saveState = true
            )
            .setRestoreState(true)
            .build()
        val screenValue = when (menuItem.itemId) {
            R.id.magic -> 0
            R.id.hearts -> 1
            else -> 2
        }
        val destination = when (menuItem.itemId) {
            R.id.magic -> R.id.blankDest
            R.id.hearts -> R.id.blankDest2
            else -> R.id.blankDest3
        }
        val bundle = bundleOf(ARG_SCREEN_TYPE to screenValue)
        navController.navigate(destination, bundle, options)
    }
}