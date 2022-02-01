package com.magistor8.anime.domain_model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthDTO(
    val status_code: Int,
    val data: AuthDataDTO
): Parcelable

@Parcelize
data class AuthDataDTO(
    val id: Int
): Parcelable
