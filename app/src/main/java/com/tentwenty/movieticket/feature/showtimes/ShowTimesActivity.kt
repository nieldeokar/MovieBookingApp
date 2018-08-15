package com.tentwenty.movieticket.feature.showtimes

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.TenTwentyApp
import com.tentwenty.movieticket.feature.base.BaseActivity
import com.tentwenty.movieticket.feature.main.DividerItemDecoration
import com.tentwenty.movieticket.feature.seatselection.SeatSelectionActivity
import com.tentwenty.movieticket.feature.shared.model.Movie
import kotlinx.android.synthetic.main.activity_show_times.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class ShowTimesActivity : BaseActivity<ShowTimesView, ShowTimesPresenter>(), ShowTimesView, ShowTimesAdapter.ClickListener {


    companion object {
        const val BUNDLE_EXTRA_MOVIE_OBJECT = "movie_obj"
    }

    @Inject
    lateinit var showTimesPresenter: ShowTimesPresenter

    @Inject
    lateinit var showTimesAdapter: ShowTimesAdapter

    private lateinit var mMovie : Movie


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TenTwentyApp).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_times)

        setSupportActionBar(toolbar)

        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayUseLogoEnabled(true)
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.setHomeButtonEnabled(true)
        }

        if(intent.hasExtra(BUNDLE_EXTRA_MOVIE_OBJECT)){

            mMovie = intent.getParcelableExtra(BUNDLE_EXTRA_MOVIE_OBJECT)
            mActionBar?.title = mMovie.title
            initRecyclerView()

            presenter.getData(mMovie.id)
        }else{
            Toast.makeText(this,"Unknown Source", Toast.LENGTH_SHORT).show()
        }

    }

    override fun createPresenter() = showTimesPresenter

    private fun initRecyclerView() {
        rcyclerShowTimes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcyclerShowTimes.setHasFixedSize(true)
        rcyclerShowTimes.adapter = showTimesAdapter
        rcyclerShowTimes.itemAnimator = DefaultItemAnimator()
        rcyclerShowTimes.addItemDecoration(DividerItemDecoration(this,  DividerItemDecoration.VERTICAL_LIST))
        showTimesAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(position: Int, v: View?) {
        val intent = Intent(this, SeatSelectionActivity::class.java)
        intent.putExtra(SeatSelectionActivity.BUNDLE_EXTRA_SHOW_TIME_ID,showTimesAdapter.getItem(position).showTime.id)
        startActivity(intent)
    }

    override fun renderShowTimes(movieShowTimes: List<MovieShowTimes>) {
        if(movieShowTimes.isEmpty()){
            tvError.visibility = View.VISIBLE
            rcyclerShowTimes.visibility = View.GONE
        }else {
            tvError.visibility = View.GONE
            rcyclerShowTimes.visibility = View.VISIBLE
            showTimesAdapter.setData(movieShowTimes)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
