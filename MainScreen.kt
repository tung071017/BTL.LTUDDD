package com.example.hetcuu.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.hetcuu.R
import com.example.hetcuu.databinding.MainActivityBinding
import com.example.hetcuu.ui.theme.ui.FollowFragment
import com.example.hetcuu.ui.theme.ui.HomeFragment
import com.example.hetcuu.ui.theme.ui.TagsFragment
import com.google.android.material.navigation.NavigationView


class MainScreen : AppCompatActivity() {

    // Khai báo navigation drawer
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout

    private val HOME_FRAGMENT : Int = 0
    private val FOLLOW_FRAGMENT : Int = 1
    private val TAGS_FRAGMENT : Int = 2
    private var CURRENT_FRAGMENT : Int = HOME_FRAGMENT

    private lateinit var binding: MainActivityBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        replaceFragment(HomeFragment(),"Trang chủ")
        navView.menu.findItem(R.id.nav_home).isChecked = true

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){
                R.id.nav_home -> {
                    if (CURRENT_FRAGMENT != HOME_FRAGMENT) {

                        replaceFragment(HomeFragment(), it.title.toString())
                        CURRENT_FRAGMENT = HOME_FRAGMENT
                    }
                    else {
                        drawerLayout.closeDrawers()
                    }
                }

                R.id.nav_follow -> {
                    if (CURRENT_FRAGMENT != FOLLOW_FRAGMENT) {
                        replaceFragment(FollowFragment(), it.title.toString())
                        CURRENT_FRAGMENT = FOLLOW_FRAGMENT
                    }
                    else{
                        drawerLayout.closeDrawers()
                    }
                }

                R.id.nav_tags ->  {
                    if (CURRENT_FRAGMENT != TAGS_FRAGMENT) {
                        replaceFragment(TagsFragment(), it.title.toString())
                        CURRENT_FRAGMENT = TAGS_FRAGMENT
                    }
                    else{
                        drawerLayout.closeDrawers()
                    }
                }

                R.id.nav_login -> {
                    val intent : Intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }

            }

            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)
        val searchButton = menu?.findItem(R.id.action_search)
        searchButton!!.setOnMenuItemClickListener {
            val intent : Intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            true
        }

        return true
    }

    fun replaceFragment(fragment: Fragment,title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers()
        }
        else {
            super.onBackPressed()
        }
    }

}