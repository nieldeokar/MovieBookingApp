package com.tentwenty.movieticket.feature.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.utils.constants.ApiConstants
import kotlinx.android.synthetic.main.item_movie.view.*
import com.tentwenty.movieticket.feature.main.MainAdapter.ClickListener
import java.security.InvalidParameterException


class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val myList: MutableList<Movie> = ArrayList()

    var clickListener: MainAdapter.ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = myList[position]

        holder.titleText?.text = data.title
        holder.releaseDate?.text = data.releaseDate
        holder.isAdult?.text = if (data.adult) "18+" else "Non adult"

        Picasso.get()
                .load(ApiConstants.IMG_BASE_URL + data.posterPath)
                .into(holder.posterPath)
    }

    override fun getItemCount() = myList.count()

    fun setData(myList: List<Movie>) {
        this.myList.clear()
        this.myList.addAll(myList)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Movie {
        if (position < 0 || position >= myList.size) {
            throw InvalidParameterException("Invalid item index")
        }
        return myList[position]
    }

    inner class MainViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val titleText = itemView?.title
        val isAdult = itemView?.adult
        val posterPath = itemView?.posterPath
        val releaseDate = itemView?.releaseDate
        val bookTicket = itemView?.book

        init {
            bookTicket?.setOnClickListener(this)
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
