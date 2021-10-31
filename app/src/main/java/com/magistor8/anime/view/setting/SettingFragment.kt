package com.magistor8.anime.view.setting

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.magistor8.anime.databinding.FragmentSettingBinding
import com.magistor8.anime.view.IS_VIOLET
import com.magistor8.anime.view.THEME

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private var violetTheme = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val preferences = requireActivity().getSharedPreferences(THEME, MODE_PRIVATE)
        violetTheme = preferences.getBoolean(IS_VIOLET, true)
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!violetTheme) {
            binding.themeGreen.isChecked = true
            binding.themeViolet.isChecked = false
        }

        binding.themeGreen.setOnClickListener {
            val editor = requireActivity().getSharedPreferences(THEME, MODE_PRIVATE).edit()
            editor.putBoolean(IS_VIOLET, false)
            editor.apply()
            requireActivity().recreate()
        }

        binding.themeViolet.setOnClickListener {
            val editor = requireActivity().getSharedPreferences(THEME, MODE_PRIVATE).edit()
            editor.putBoolean(IS_VIOLET, true)
            editor.apply()
            requireActivity().recreate()
        }

    }

    companion object {
        fun newInstance() = SettingFragment()
    }

}

