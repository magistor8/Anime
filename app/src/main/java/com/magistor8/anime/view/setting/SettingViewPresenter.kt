package com.magistor8.anime.view.setting

import android.content.SharedPreferences
import com.magistor8.anime.view.IS_APP_BAR
import com.magistor8.anime.view.IS_VIOLET

class SettingViewPresenter(private val view: SettingView) {

    var violetTheme = true
    var appBar = true

    fun init(pref: SharedPreferences) {
        violetTheme = pref.getBoolean(IS_VIOLET, true)
        appBar = pref.getBoolean(IS_APP_BAR, true)
    }

}