package com.magistor8.anime.domain_model

import android.os.Parcelable
import com.magistor8.anime.MyApp
import com.magistor8.anime.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShortData(
    val title: String,
    val seasons: Int,
    val year: List<Int>,
    val image: String
): Parcelable

fun testShortData () : List<ShortData> = arrayListOf(
    ShortData("Anime1", 3, arrayListOf(2000, 2002), MyApp.instance.getString(R.string.test_image1)),
    ShortData("Anime2", 6, arrayListOf(1990, 1995), MyApp.instance.getString(R.string.test_image2)),
    ShortData("Anime3", 2, arrayListOf(2010, 2012), MyApp.instance.getString(R.string.test_image3))
)
