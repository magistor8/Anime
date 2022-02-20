package com.magistor8.anime.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.magistor8.anime.R
import com.magistor8.anime.ui.view.main.MainFragment
import com.magistor8.anime.ui.view.setting.SettingFragment

private const val MAIN = "MainFragment"
private const val SETTING = "SettingFragment"

class Navigation() {

    fun mainFragment(context : FragmentActivity, addToBS: Boolean) {
        navigate(context, MainFragment.newInstance(), R.id.container, addToBS, MAIN)
    }

    fun settingFragment(context : FragmentActivity, addToBS: Boolean) {
        navigate(context, SettingFragment.newInstance(), R.id.container, addToBS, SETTING)
    }

    private fun navigate(context: FragmentActivity, fragment: Fragment, container: Int, addToBS: Boolean, tag: String) {
        with(context.supportFragmentManager) {
            val tr = beginTransaction()
            tr.setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            tr.replace(
                container,
                if (findFragmentByTag(tag) == null) {fragment} else {findFragmentByTag(tag)!!},
                tag
            )
            if (addToBS && findFragmentByTag(tag) == null) {
                tr.addToBackStack("")
            }
            tr.commit()
        }
    }

}