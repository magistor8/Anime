package com.magistor8.anime.domain_model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val title: String,
    val seasons: Int,
    val year: List<Int>,
    val image: String
): Parcelable
