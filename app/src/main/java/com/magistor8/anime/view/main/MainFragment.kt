package com.magistor8.anime.view.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.magistor8.anime.R
import com.magistor8.anime.databinding.FragmentMainBinding
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.core.view.doOnLayout
import com.google.android.material.textfield.TextInputLayout


class MainFragment : Fragment() {

    private var startWidthImage = 0
    private var startHeightSearch = 0
    private var startHeightSearch2 = 0

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomView: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view)
        if (bottomView.selectedItemId != R.id.bottom_main) bottomView.selectedItemId = R.id.bottom_main


        //Размеры
        binding.searchGroup.doOnLayout {
            startHeightSearch = it.measuredHeight
        }
        binding.inputLayout.doOnLayout {
            startHeightSearch2 = it.measuredHeight
        }
        startWidthImage = requireActivity().resources.getDimension(R.dimen.mainFragment_EquilateralImageViewContainer_width).toInt()
        //Анимация изображения
        setImageAnimation()
    }

    private fun setImageAnimation() {
        val image = binding.imageContainer
        val search = binding.searchGroup
        val search2 = binding.inputLayout
        val chip1 = binding.chipGroupSeason
        val chip2 = binding.chipGroupSeries
        val description = binding.seriesDescription
        val button = binding.watch

        binding.imageView.setOnClickListener {
            if (image.measuredWidth == startWidthImage) {

                imageAnimationStart(image)
                searchGroupAnimationStart(search)
                inputLayoutAnimationStart(search2)
                animHide(chip1)
                animHide(chip2)
                animHide(description)
                animHide(button)

            } else {

                imageAnimationBack(image)
                searchGroupAnimationBack(search)
                inputLayoutAnimationBack(search2)
                animShow(chip1)
                animShow(chip2)
                animShow(description)
                animShow(button)

            }

        }
    }

    private fun animShow(view: View) {
        view.animate()
            .alpha(1f)
            .setDuration(400)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.isClickable = true
                }
            })
    }

    private fun animHide(view: View) {
        view.animate()
            .alpha(0f)
            .setDuration(400)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.isClickable = false
                }
            })
    }

    private fun inputLayoutAnimationBack(search2: TextInputLayout) {
        val animSearchInputLayout = ValueAnimator.ofInt(search2.measuredHeight, startHeightSearch2)
        animSearchInputLayout.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = search2.layoutParams
            layoutParams.height = animatedValue
            search2.layoutParams = layoutParams
        }

        animSearchInputLayout.interpolator = AccelerateDecelerateInterpolator()
        animSearchInputLayout.duration = 400
        animSearchInputLayout.start()
    }

    private fun inputLayoutAnimationStart(search2: TextInputLayout) {
        val animSearchInputLayout = ValueAnimator.ofInt(search2.measuredHeight, 0)
        animSearchInputLayout.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = search2.layoutParams
            layoutParams.height = animatedValue
            search2.layoutParams = layoutParams
        }
        animSearchInputLayout.interpolator = AccelerateDecelerateInterpolator()
        animSearchInputLayout.duration = 400
        animSearchInputLayout.start()
    }

    private fun searchGroupAnimationBack(search: LinearLayout) {
        val animSearchGroup = ValueAnimator.ofInt(search.measuredHeight, startHeightSearch)
        animSearchGroup.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = search.layoutParams
            layoutParams.height = animatedValue
            search.layoutParams = layoutParams
        }
        animSearchGroup.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                for (child in search.children) {
                    child.visibility = View.VISIBLE
                }
            }
        })
        animSearchGroup.interpolator = AccelerateDecelerateInterpolator()
        animSearchGroup.duration = 400
        animSearchGroup.start()
    }

    private fun searchGroupAnimationStart(search: LinearLayout) {
        val animSearchGroup = ValueAnimator.ofInt(search.measuredHeight, 0)
        animSearchGroup.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = search.layoutParams
            layoutParams.height = animatedValue
            search.layoutParams = layoutParams
        }
        animSearchGroup.interpolator = AccelerateDecelerateInterpolator()
        animSearchGroup.duration = 400
        animSearchGroup.start()

        for (child in search.children) {
            child.visibility = View.GONE
        }
    }

    private fun imageAnimationBack(image: FrameLayout) {
        val animImage = ValueAnimator.ofInt(image.measuredWidth, startWidthImage)
        animImage.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = image.layoutParams
            layoutParams.width = animatedValue
            image.layoutParams = layoutParams
        }
        animImage.interpolator = AccelerateDecelerateInterpolator()
        animImage.duration = 400
        animImage.start()
        binding.animeDescription.background = ResourcesCompat.getDrawable(
            requireActivity().resources,
            R.drawable.textlines,
            requireActivity().theme
        )
    }

    private fun imageAnimationStart(image: FrameLayout) {
        val animImage = ValueAnimator.ofInt(image.measuredWidth, binding.general.measuredWidth)
        animImage.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = image.layoutParams
            layoutParams.width = animatedValue
            image.layoutParams = layoutParams
        }
        animImage.interpolator = AccelerateDecelerateInterpolator()
        animImage.duration = 400
        animImage.start()
        binding.animeDescription.background = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}

