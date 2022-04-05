package com.example.caira2.iu.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.caira2.R
import com.example.caira2.databinding.ActivityBodyappBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar



class BodyappActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBodyappBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBodyappBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarBodyapp.toolbar)

//        binding.appBarBodyapp.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_bodyapp)

       //Cambiar color
        //Para cambiar el color del fondo, texto y icono del item de un menu.
        navView.setBackgroundColor(ContextCompat.getColor(this,R.color.sidenavdark));
        navView.itemTextColor =
            ColorStateList.valueOf(ContextCompat.getColor(this,R.color.white))
        navView.itemIconTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this,R.color.white))

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_dashboard, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //        public abstract MenuItem add (
//        int groupId,
//        int itemId,
//        int order,
//        int titleRes)

        menu.add(
            0,
            1,
            1,
            menuIconWithText(
                ContextCompat.getDrawable(this,R.drawable.ic_menu_settings)!!,
                resources.getString(R.string.account_settings)
            )
        );
        menu.add(
            0,
            2,
            2,
            menuIconWithText(
                ContextCompat.getDrawable(this,R.drawable.ic_menu_social)!!,
                resources.getString(R.string.social_profile)
            )
        );
        menu.add(
            0,
            3,
            3,
            menuIconWithText(
                ContextCompat.getDrawable(this, R.drawable.ic_menu_logout)!!,
                resources.getString(R.string.logout)
            )
        );
        menuInflater.inflate(R.menu.bodyapp, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            (1) -> {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                return true
            }
            2 -> {

                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                return true
            }
            3 -> {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                return true
            }
            else ->{
                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                //drawerLayout.closeDrawer(GravityCompat.START)

                //* Abre Navigation Drawer.
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return true
//        super.onOptionsItemSelected(item)
//        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_bodyapp)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun menuIconWithText(r: Drawable, title: String): CharSequence? {
        r.setBounds(0, 0, r.intrinsicWidth, r.intrinsicHeight)
        val sb = SpannableString("    $title")
        val imageSpan = ImageSpan(r, ImageSpan.ALIGN_BOTTOM)
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sb
    }
}