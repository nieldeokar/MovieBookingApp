package com.tentwenty.movieticket.feature.showtimes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tentwenty.movieticket.R
import kotlinx.android.synthetic.main.row_show_time.view.*
import java.security.InvalidParameterException


class ShowTimesAdapter : RecyclerView.Adapter<ShowTimesAdapter.MainViewHolder>() {

    private val dataList: MutableList<MovieShowTimes> = ArrayList()

    var clickListener: ShowTimesAdapter.ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_show_time, parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = dataList[position]

        holder.cinemaName?.text = data.cinema.name
        holder.cinemaLocation?.text = data.cinema.cinema_location
        holder.timing?.text = data.showTime.timings

    }

    override fun getItemCount() = dataList.count()

    fun setData(myList: List<MovieShowTimes>) {
        this.dataList.clear()
        this.dataList.addAll(myList)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): MovieShowTimes {
        if (position < 0 || position >= dataList.size) {
            throw InvalidParameterException("Invalid item index")
        }
        return dataList[position]
    }

    inner class MainViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val cinemaName = itemView?.cinemaName
        val cinemaLocation = itemView?.cinemaLocation
        val timing = itemView?.timing

        init {
            itemView?.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            clickListener?.onItemClick(adapterPosition, view)
        }
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }
}
