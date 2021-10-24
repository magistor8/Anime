package com.magistor8.anime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

const val THEME = "THEME"
const val IS_VIOLET = "isViolet"

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var violetTheme = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = getSharedPreferences(THEME, MODE_PRIVATE)
        violetTheme = preferences.getBoolean(IS_VIOLET, true)
        setMyTheme()

        setContentView(R.layout.activity_main)

        val bottomSheetInc : ConstraintLayout = findViewById(R.id.bottom_sheet_inc)
        setBottomSheetBehavior(bottomSheetInc)
        setBottomAppBar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance(), "Main").commit()
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