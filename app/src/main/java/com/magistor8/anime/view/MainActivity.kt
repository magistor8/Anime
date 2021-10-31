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
import com.magistor8.anime.databinding.ActivityMainBinding
import com.magistor8.anime.view.main.MainFragment
import com.magistor8.anime.view.setting.SettingFragment
import com.magistor8.anime.view.viewpager.ViewPagerAdapter

const val SETTINGS = "SETTINGS"
const val IS_VIOLET = "isViolet"
const val IS_FIRST_LUNCH = "IS_FIRST_LUNCH"
const val IS_APP_BAR = "IS_APP_BAR"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var violetTheme = true
    private var appBar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Получим настройки темы
        val preferences = getSharedPreferences(SETTINGS, MODE_PRIVATE)
        violetTheme = preferences.getBoolean(IS_VIOLET, true)
        //Настройки нижней панели
        appBar = preferences.getBoolean(IS_APP_BAR, true)

        //Установим тему
        setMyTheme()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Проверим нужно ли показать стартовый экран
        startView(preferences)

        //Установим нижнюю панель навигации
        setBottomView()

        //Установим AppBar
        val bottomSheetInc : ConstraintLayout = binding.bottomSheetInc.root
        setBottomSheetBehavior(bottomSheetInc)
        setBottomAppBar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance(), "Main").commit()
        }
    }

    private fun setBottomView() {
        if (!appBar) {
            binding.bottomAppBar.visibility = View.GONE
            binding.bottomSheetInc.bottomSheetContainer.visibility = View.GONE
            binding.bottomNavigationView.visibility = View.VISIBLE

            binding.bottomNavigationView.setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.bottom_fav -> {return@setOnItemSelectedListener true}
                    R.id.bottom_profile -> {return@setOnItemSelectedListener true}
                    R.id.bottom_setting -> {
                        settingFragment()
                        return@setOnItemSelectedListener true
                    }
                    else -> {return@setOnItemSelectedListener true}
                }
            }
        }
    }

    private fun startView(preferences: SharedPreferences) {
        val startView = preferences.getBoolean(IS_FIRST_LUNCH, true)
        if (startView) {
            val container: FrameLayout = binding.container
            val viewPager2: ViewPager2 = binding.viewPager2
            val bottomAppBar: BottomAppBar = binding.bottomAppBar
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
                settingFragment()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(this.supportFragmentManager, "tag")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun settingFragment() {
        with(supportFragmentManager) {
            if (findFragmentByTag("Setting") == null) {
                beginTransaction().replace(R.id.container, SettingFragment.newInstance(), "Setting")
                    .addToBackStack("").commit()
            }
        }
    }

    private fun setBottomAppBar() {
        this.setSupportActionBar(binding.bottomAppBar)
    }

}