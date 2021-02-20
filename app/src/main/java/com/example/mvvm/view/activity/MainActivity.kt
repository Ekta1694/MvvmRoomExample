package com.example.mvvm.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mvvm.R
import com.example.mvvm.view.fragment.GalleryFragment
import com.example.mvvm.view.fragment.MainFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var drawer: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer!!.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
        toggle.syncState()
        val navigationView =
            findViewById<View>(R.id.navigation_view) as NavigationView
        navigationView.menu.getItem(0).isChecked = true
        toolbar!!.title = "Home"
        val fragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, MainFragment())
        fragmentTransaction.addToBackStack(MainFragment::class.java.name)
        fragmentTransaction.commit()
        drawer!!.closeDrawer(GravityCompat.START)
        navigationView.setNavigationItemSelectedListener { item ->
            val fragmentTransaction =
                supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.nav_home -> {
                    toolbar!!.title = "Home"
                    fragmentTransaction.replace(R.id.fragmentContainer, MainFragment())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
                R.id.nav_gallery -> {
                    toolbar!!.title = "Gallery"
                    fragmentTransaction.replace(R.id.fragmentContainer, GalleryFragment())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
                R.id.nav_send -> toolbar!!.title = "Send"
                R.id.nav_share -> toolbar!!.title = "Share"
                R.id.nav_slideshow -> toolbar!!.title = "Slideshow"
                R.id.nav_tools -> toolbar!!.title = "Tools"
                else -> {
                }
            }
            // addFragment(MainFragment(),R.id.fragmentContainer,supportFragmentManager,false);
            drawer!!.closeDrawer(GravityCompat.START)
            true
        }
    }
}