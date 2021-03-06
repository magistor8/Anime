package com.magistor8.anime.ui.view.main

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
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.ViewModelProvider
import com.magistor8.anime.domain.entities.ShortData
import com.magistor8.anime.ui.view.viewmodel.MainFragmentContract
import com.magistor8.anime.ui.view.viewmodel.MyViewModel


class MainFragment : Fragment() {

    private var startWidthImage = 0
    private var startHeightSearch = 0
    private var startHeightSearch2 = 0

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter = MainFragmentAdapter()

    private var isSearchResult = false
    private var searchData: List<ShortData> = arrayListOf()

    //ViewModel
    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(this).get(MyViewModel::class.java)
    }

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

        //??????????????
        binding.mainFragmentRecyclerView.adapter = adapter

        //???????????????? - ????????????
        viewModel.viewState.observe(viewLifecycleOwner) { state -> render(state) }

        //???????????????????????????? ????????????
        overrideBackKey(view)

        //?????????????? ???? ??????????
        setSearchListener()

        //??????????????
        binding.searchGroup.doOnLayout {
            startHeightSearch = it.measuredHeight
        }
        binding.inputLayout.doOnLayout {
            startHeightSearch2 = it.measuredHeight
        }
        startWidthImage = requireActivity().resources.getDimension(R.dimen.mainFragment_EquilateralImageViewContainer_width).toInt()
        //???????????????? ??????????????????????
        setImageAnimation()
    }

    private fun render(state: MainFragmentContract.ViewState) {
        when (state) {
            is MainFragmentContract.ViewState.SuccesShortData -> showSearchData(state)
            is MainFragmentContract.ViewState.EmptyState -> showSearchWindow()
            is MainFragmentContract.ViewState.Error -> {}
        }
    }

    private fun showSearchWindow() {
        //????????????????????
        adapter.setData(arrayListOf())
        val scrollHeight = binding.scroll.measuredHeight
        binding.mainFragmentRecyclerView.visibility = View.VISIBLE
        animHide(binding.scroll)
        searchResultAnimation(scrollHeight)
    }

    private fun showSearchData(state: MainFragmentContract.ViewState.SuccesShortData) {
        searchData = state.shortData
        adapter.setData(searchData)
    }

    private fun overrideBackKey(view: View) {
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == KeyEvent.KEYCODE_BACK && isSearchResult) {
                    binding.scroll.visibility = View.VISIBLE
                    binding.mainFragmentRecyclerView.visibility = View.GONE
                    binding.mainFragmentRecyclerView.layoutParams.height = 0
                    animShow(binding.scroll)
                    isSearchResult = false
                    return true
                }
                return false
            }
        })
    }

    private fun setSearchListener() {
        binding.inputLayout.setEndIconOnClickListener {
            performSearch(binding.inputEditText.editableText.toString())
        }

        binding.inputEditText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(v.editableText.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun performSearch(text: String) {
        //??????????
        viewModel.onEvent(MainFragmentContract.Event.LoadData(text))
    }

    private fun searchResultAnimation(scrollHeight: Int) {
        val anim = ValueAnimator.ofInt(binding.mainFragmentRecyclerView.measuredHeight, scrollHeight)
        anim.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams: ViewGroup.LayoutParams = binding.mainFragmentRecyclerView.layoutParams
            layoutParams.height = animatedValue
            binding.mainFragmentRecyclerView.layoutParams = layoutParams
        }
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                isSearchResult = true
            }
        })
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.duration = 400
        anim.start()
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}

