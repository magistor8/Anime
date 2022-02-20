package com.magistor8.anime.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchDTO (
    @SerializedName("status_code" ) var statusCode : Int?    = null,
    @SerializedName("message"     ) var message    : String? = null,
    @SerializedName("data"        ) var data       : Data?   = Data(),
    @SerializedName("version"     ) var version    : String? = null
): Parcelable

@Parcelize
data class Titles (
    @SerializedName("en" ) var en : String? = null,
    @SerializedName("jp" ) var jp : String? = null
): Parcelable

@Parcelize
data class Descriptions (
    @SerializedName("en" ) var en : String? = null
): Parcelable

@Parcelize
data class Documents (
    @SerializedName("anilist_id"       ) var anilistId       : Int?              = null,
    @SerializedName("mal_id"           ) var malId           : Int?              = null,
    @SerializedName("format"           ) var format          : Int?              = null,
    @SerializedName("status"           ) var status          : Int?              = null,
    @SerializedName("titles"           ) var titles          : Titles?           = Titles(),
    @SerializedName("descriptions"     ) var descriptions    : Descriptions?     = Descriptions(),
    @SerializedName("start_date"       ) var startDate       : String?           = null,
    @SerializedName("end_date"         ) var endDate         : String?           = null,
    @SerializedName("season_period"    ) var seasonPeriod    : Int?              = null,
    @SerializedName("season_year"      ) var seasonYear      : Int?              = null,
    @SerializedName("episodes_count"   ) var episodesCount   : Int?              = null,
    @SerializedName("episode_duration" ) var episodeDuration : Int?              = null,
    @SerializedName("cover_image"      ) var coverImage      : String?           = null,
    @SerializedName("cover_color"      ) var coverColor      : String?           = null,
    @SerializedName("banner_image"     ) var bannerImage     : String?           = null,
    @SerializedName("genres"           ) var genres          : ArrayList<String> = arrayListOf(),
    @SerializedName("sequel"           ) var sequel          : Int?              = null,
    @SerializedName("score"            ) var score           : Int?              = null,
    @SerializedName("nsfw"             ) var nsfw            : Boolean?          = null,
    @SerializedName("hasCoverImage"    ) var hasCoverImage   : Boolean?          = null,
    @SerializedName("id"               ) var id              : Int?              = null
): Parcelable

@Parcelize
data class Data (
    @SerializedName("current_page" ) var currentPage : Int?                 = null,
    @SerializedName("count"        ) var count       : Int?                 = null,
    @SerializedName("documents"    ) var documents   : ArrayList<Documents> = arrayListOf(),
    @SerializedName("last_page"    ) var lastPage    : Int?                 = null
): Parcelable