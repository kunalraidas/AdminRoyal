package com.kunalashish.adminroyal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.kunalashish.adminroyal.R
import com.kunalashish.adminroyal.fragment.*

class HomeActivity : AppCompatActivity() {

    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var previouMenuItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        coordinatorLayout = findViewById(R.id.coordinator)
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigation)
        setUptoolbar()
        OpenProductFragment()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
                this@HomeActivity,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()



        navigationView.setNavigationItemSelectedListener {

            if (previouMenuItem != null) {
                previouMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previouMenuItem = it

            when (it.itemId) {
                R.id.product -> {
                    OpenProductFragment()
                    drawerLayout.closeDrawers()
                }

                R.id.order -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, OrderFragment())
                            .commit()

                    supportActionBar?.title = "Order"
                    drawerLayout.closeDrawers()
                }

                R.id.payment -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, PaymentFragment())
                            .commit()

                    supportActionBar?.title = "Payment"
                    drawerLayout.closeDrawers()
                }
                R.id.delivery -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, DeliveryFragment())
                            .commit()

                    supportActionBar?.title = "Delivery"
                    drawerLayout.closeDrawers()
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, ProfileFragment())
                            .commit()

                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()
                }
            }

            return@setNavigationItemSelectedListener true
        }
    }

    fun setUptoolbar()
    {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Royal Mobile"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    fun OpenProductFragment()
    {
        val fragment = ProductFragment()
        val transation = supportFragmentManager.beginTransaction()

        transation.replace(R.id.frame,fragment)
        transation.commit()
        supportActionBar?.title = "Product"
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag)
        {
            !is ProductFragment -> OpenProductFragment()

            else -> super.onBackPressed()
        }
    }


}