package com.magistor8.anime.view.setting

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.magistor8.anime.R
import com.magistor8.anime.databinding.FragmentSettingBinding
import com.magistor8.anime.view.IS_APP_BAR
import com.magistor8.anime.view.IS_VIOLET
import com.magistor8.anime.view.SETTINGS


class SettingFragment : Fragment(), SettingView {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val presenter = SettingViewPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val preferences = requireActivity().getSharedPreferences(SETTINGS, MODE_PRIVATE)
        presenter.init(preferences)
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomView: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view)
        if (bottomView.selectedItemId != R.id.bottom_setting) bottomView.selectedItemId = R.id.bottom_setting

        if (!presenter.violetTheme) {
            binding.themeGreen.isChecked = true
            binding.themeViolet.isChecked = false
        }
        if (!presenter.appBar) {
            binding.bottomView.isChecked = true
            binding.appBar.isChecked = false
        }

        binding.themeGreen.setOnClickListener {
            setTheme(it)
        }
        binding.themeViolet.setOnClickListener {
            setTheme(it)
        }
        binding.appBar.setOnClickListener {
            setBottomView(it)
        }
        binding.bottomView.setOnClickListener {
            setBottomView(it)
        }

    }

    private fun setBottomView(it: View) {
        val editor = requireActivity().getSharedPreferences(SETTINGS, MODE_PRIVATE).edit()
        when(it.id) {
            R.id.bottomView -> { editor.putBoolean(IS_APP_BAR, false) }
            R.id.appBar -> { editor.putBoolean(IS_APP_BAR, true) }
        }
        editor.apply()
        requireActivity().recreate()
    }

    private fun setTheme(it: View) {
        val editor = requireActivity().getSharedPreferences(SETTINGS, MODE_PRIVATE).edit()
        when(it.id) {
            R.id.themeGreen -> { editor.putBoolean(IS_VIOLET, false) }
            R.id.themeViolet -> { editor.putBoolean(IS_VIOLET, true) }
        }
        editor.apply()
        requireActivity().recreate()
    }

    companion object {
        fun newInstance() = SettingFragment()
    }

}

