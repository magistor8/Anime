package com.magistor8.anime.ui.view.main.view_holder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.magistor8.anime.domain.ShortData
import kotlinx.android.synthetic.main.fragment_main_recycler_item.view.*

class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: ShortData) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.apply {
                this.image_view_item.load(data.image)
                this.item_title.text = data.title
                this.item_seasons.text = data.episodes.toString() + " episodes"
                this.item_year.text = data.year[0].toString() + "-" + data.year[1].toString()
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