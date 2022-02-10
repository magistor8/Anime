package com.magistor8.anime.utils

import com.magistor8.anime.domain.SearchDTO
import com.magistor8.anime.domain.ShortData

class Converter {

    fun convertSearchDTOtoShortData(body: SearchDTO?): List<ShortData> {
        val shortData = mutableListOf<ShortData>()

        if (body?.statusCode != null && body.statusCode == 200) {

            for (document in body.data!!.documents) {

                val sData = ShortData("", 0, arrayListOf(), "")

                if (document.titles != null && document.titles?.en != null) {
                    sData.title = document.titles!!.en!!
                }
                if (document.episodesCount != null) {
                    sData.episodes = document.episodesCount!!
                }
                if (document.startDate != null && document.endDate != null) {
                    sData.year.add(document.startDate!!.substring(0, 4).toInt())
                    sData.year.add(document.endDate!!.substring(0, 4).toInt())
                }
                if (document.coverImage != null) {
                    sData.image = document.coverImage!!
                }
                shortData.add(sData)

            }
            return shortData
        }
        return emptyList()
    }
}