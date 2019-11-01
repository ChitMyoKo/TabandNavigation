package com.acedatasystem.bi3.tabsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val adapter = ViewPagerAdapter(supportFragmentManager)
    private  var fragmentLists = mutableListOf<Fragment>(OneFragment(),TwoFragment(),ThreeFragment())
    private  var titleLists = mutableListOf<String>("a","b","c")
    private var nav = "Departure"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupViewPager(viewPager!!)
        tabsLayout.setupWithViewPager(viewPager)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navView.setNavigationItemSelectedListener(this)

        tabsLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) { }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity,(nav+" "+p0!!.position.toString()),Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {

        adapter.addFragment(fragmentLists,titleLists)
        viewPager.adapter = adapter
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var fragment : Fragment? = null

        when (item.itemId) {
            R.id.nav_home -> {
                nav = "Departure"
                fragmentLists = mutableListOf<Fragment>(OneFragment(),TwoFragment(),ThreeFragment())
                titleLists = mutableListOf<String>("a","b","c")
                setupViewPager(viewPager)
            }
            R.id.nav_gallery -> {
                nav = "Arrival"
                fragmentLists = mutableListOf<Fragment>(FourFragment(),FourFragment(),FourFragment())
                titleLists = mutableListOf<String>("d","e","f")
                setupViewPager(viewPager)
            }
        }

        if(fragment != null) {
            var ft : FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.commit()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
