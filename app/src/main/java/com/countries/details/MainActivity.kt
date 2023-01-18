package com.countries.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment? ?: return
        val navController = navHostFragment.navController
        val actionBar = supportActionBar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.welcomeFragment) {
                actionBar!!.hide()
            } else {
                actionBar!!.show()
            }
        }
    }
}