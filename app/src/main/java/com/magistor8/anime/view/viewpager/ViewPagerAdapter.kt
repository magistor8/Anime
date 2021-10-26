package com.magistor8.anime.view.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.magistor8.anime.R

class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {

    lateinit var pagerVH: PagerVH
    lateinit var listener: View.OnClickListener

    private val colors = intArrayOf(
        R.color.purple_700,
        R.color.light_700,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        pagerVH = PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
        pagerVH.setClickListener(listener)
        return pagerVH
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        findViewById<MaterialButton>(R.id.confirm).visibility = View.GONE
        rootView.setBackgroundResource(colors[position])
        when(position) {
            0 -> findViewById<AppCompatTextView>(R.id.tvTitle).text = "Welcome dear friend. In this application you can find and watch your favorite anime"
            1 -> findViewById<AppCompatTextView>(R.id.tvTitle).text = "Search and save, play random anime, share with your friends"
            2 -> {
                findViewById<AppCompatTextView>(R.id.tvTitle).text = "Happy viewing"
                findViewById<MaterialButton>(R.id.confirm).visibility = View.VISIBLE
            }
        }
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setClickListener(listener : View.OnClickListener) {
        itemView.findViewById<MaterialButton>(R.id.confirm).setOnClickListener(listener)
    }
}