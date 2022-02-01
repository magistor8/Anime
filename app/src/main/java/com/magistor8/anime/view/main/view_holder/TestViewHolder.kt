package com.magistor8.anime.view.main.view_holder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.textview.MaterialTextView
import com.magistor8.anime.R
import com.magistor8.anime.domain_model.ShortData
import com.magistor8.anime.view.imageview.EquilateralImageView

class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: ShortData) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.apply {
                findViewById<EquilateralImageView>(R.id.image_view_item).load(data.image)
                findViewById<MaterialTextView>(R.id.item_title).text = data.title
                findViewById<MaterialTextView>(R.id.item_seasons).text = data.seasons.toString() + " seasons"
                findViewById<MaterialTextView>(R.id.item_year).text = data.year[0].toString() + "-" + data.year[1].toString()
                setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "on click: ${data.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}