package com.magistor8.anime.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magistor8.anime.R
import com.magistor8.anime.domain_model.Data
import com.magistor8.anime.view.main.view_holder.TestViewHolder

class MainFragmentAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: ArrayList<Data> = arrayListOf()

    fun setData(input: ArrayList<Data>) {
        data = input
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TestViewHolder(inflater.inflate(R.layout.fragment_main_recycler_item, parent, false) as View)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as TestViewHolder
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}