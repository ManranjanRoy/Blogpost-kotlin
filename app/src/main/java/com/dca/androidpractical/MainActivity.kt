package com.dca.androidpractical

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.dca.androidpractical.ui.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


  lateinit var progreDialog: ProgressDialog
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.setBackgroundDrawableResource(R.drawable.bgtool)
        setContentView(R.layout.appbar)

        val firstFragment=HomeFragment()
        val secondFragment=DashBoardFragment()
        val thirdFragment=ThreeFragment()
        val fourthFragment=FourFragment()
        val fifthFragment=FiveFragment()
        val navigationView=findViewById<BottomNavigationView>(R.id.nav_view)
        val drawerLayout=findViewById<DrawerLayout>(R.id.main_drawer_layout)
        val navigation=findViewById<NavigationView>(R.id.main_navigation_view)
        val drawer=findViewById<ImageView>(R.id.drawerbutton);

        navigationView.itemIconTintList=null

        var headerview=navigation.getHeaderView(0)
        val closebutton=headerview.findViewById<ImageView>(R.id.closebutton);

        drawer.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }
        closebutton.setOnClickListener {
            drawerLayout.closeDrawer(Gravity.LEFT)
        }

        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home->setCurrentFragment(firstFragment)
                R.id.navigation_dashboard->setCurrentFragment(secondFragment)
                R.id.navigation_notifications->setCurrentFragment(thirdFragment)
                R.id.navigation_four->setCurrentFragment(fourthFragment)
                R.id.navigation_five->setCurrentFragment(fifthFragment)

            }
            true
        }
        setCurrentFragment(firstFragment)

    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun backGroundColor() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.setBackgroundDrawableResource(R.drawable.bgtool)
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    override fun onDestroy() {
        super.onDestroy()
        finishAffinity()
    }
}