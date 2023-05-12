package com.shem.ubayalibrary.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.shem.ubayalibrary.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private var toggleMenu: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navView = findViewById<NavigationView>(R.id.navView)
        bottomNav.setupWithNavController(navController)
        NavigationUI.setupWithNavController(navView, navController)

        navController.graph.setStartDestination(R.id.itemHome)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navController.addOnDestinationChangedListener{navController, d: NavDestination, arguments->
            if(d.id == R.id.loginFragment){
                bottomNav.visibility = View.GONE
                navView.visibility = View.GONE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowHomeEnabled(false)
                toggle.isDrawerIndicatorEnabled = false
                toggleMenu = false
            }
            else {
                bottomNav.visibility = View.VISIBLE
                navView.visibility = View.VISIBLE
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                if(d.id == R.id.itemHome){
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                    toggle.isDrawerIndicatorEnabled = true
                    toggleMenu = true
                } else {
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    toggle.isDrawerIndicatorEnabled = false
                    toggleMenu = false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggleMenu) {
            if (item.itemId == android.R.id.home) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
