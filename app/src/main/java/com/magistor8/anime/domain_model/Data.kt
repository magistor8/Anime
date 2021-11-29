package com.magistor8.anime.domain_model

import android.content.Context
import android.os.Parcelable
import com.magistor8.anime.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val title: String,
    val seasons: Int,
    val year: List<Int>,
    val image: String
): Parcelable

fun getTestData(context: Context): ArrayList<Data> {
    return arrayListOf(
        Data("Anime1", 3, arrayListOf(2000, 2002), context.getString(R.string.test_image1)),
        Data("Anime2", 6, arrayListOf(1990, 1995), context.getString(R.string.test_image2)),
        Data("Anime3", 2, arrayListOf(2010, 2012), context.getString(R.string.test_image3))
    )
}
