package com.magistor8.anime.domain_model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ShortData(
    var title: String,
    var episodes: Int,
    var year: ArrayList<Int>,
    var image: String
):Parcelable
