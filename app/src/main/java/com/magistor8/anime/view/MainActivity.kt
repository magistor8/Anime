package com.magistor8.anime.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.magistor8.anime.view.bottomappbar.BottomNavigationDrawerFragment
import com.magistor8.anime.R
import com.magistor8.anime.view.main.MainFragment
import com.magistor8.anime.view.setting.SettingFragment
import com.magistor8.anime.view.viewpager.ViewPagerAdapter

const val THEME = "THEME"
const val IS_VIOLET = "isViolet"
const val IS_FIRST_LUNCH = "IS_FIRST_LUNCH"

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var violetTheme = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Получим настройки темы
        val preferences = getSharedPreferences(THEME, MODE_PRIVATE)
        violetTheme = preferences.getBoolean(IS_VIOLET, true)

        //Установим тему
        setMyTheme()
        setContentView(R.layout.activity_main)

        //Проверим нужно ли показать стартовый экран
        startView(preferences)

        //Установим AppBar
        val bottomSheetInc : ConstraintLayout = findViewById(R.id.bottom_sheet_inc)
        setBottomSheetBehavior(bottomSheetInc)
        setBottomAppBar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance(), "Main").commit()
        }
    }

    private fun startView(preferences: SharedPreferences) {
        val startView = preferences.getBoolean(IS_FIRST_LUNCH, true)
        if (startView) {
            val container: FrameLayout = findViewById(R.id.container)
            val viewPager2: ViewPager2 = findViewById(R.id.viewPager2)
            val bottomAppBar: BottomAppBar = findViewById(R.id.bottom_app_bar)
            viewPager2.visibility = View.VISIBLE
            bottomAppBar.visibility = View.GONE
            container.visibility = View.GONE
            val startButtonClickListener = View.OnClickListener {
                viewPager2.visibility = View.GONE
                container.visibility = View.VISIBLE
                bottomAppBar.visibility = View.VISIBLE
                val editor = preferences.edit()
                editor.putBoolean(IS_FIRST_LUNCH, false)
                editor.apply()
            }

            val viewPagerAdapter = ViewPagerAdapter()
            viewPagerAdapter.listener = startButtonClickListener
            viewPager2.adapter = viewPagerAdapter
        }
    }

    private fun setMyTheme() {
        if (violetTheme) {
            setTheme(R.style.Violet)
        } else {
            setTheme(R.style.Green)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_setting -> {
                with(supportFragmentManager) {
                    if (findFragmentByTag("Setting") == null) {
                        beginTransaction().replace(R.id.container, SettingFragment.newInstance(), "Setting").addToBackStack("").commit()
                    }
                }
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(this.supportFragmentManager, "tag")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar() {
        this.setSupportActionBar(findViewById(R.id.bottom_app_bar))
    }

}