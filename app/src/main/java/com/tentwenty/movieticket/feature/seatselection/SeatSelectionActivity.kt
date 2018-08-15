package com.tentwenty.movieticket.feature.seatselection

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.TenTwentyApp
import com.tentwenty.movieticket.feature.base.BaseActivity
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import kotlinx.android.synthetic.main.activity_seat_selection.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class SeatSelectionActivity : BaseActivity<SeatSelectionView, SeatSelectionPresenter>(), SeatSelectionView, View.OnClickListener {

    companion object {
        const val BUNDLE_EXTRA_SHOW_TIME_ID = "show_time_id"
        const val EMPTY_SEAT_PASSAGE = 0
        const val SEAT_AVAILABLE = 1
        const val SEAT_UNAVAILABLE = 2
        const val SEAT_SELECTED = 3
    }

    @Inject
    lateinit var seatselectionPresenter: SeatSelectionPresenter

    private lateinit var mTheaterLayoutList: Array<TheaterLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TenTwentyApp).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        setSupportActionBar(toolbar)

        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayUseLogoEnabled(true)
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.setHomeButtonEnabled(true)
        }

        if(intent.hasExtra(BUNDLE_EXTRA_SHOW_TIME_ID)){

            tableLayout.isStretchAllColumns = true


            presenter.getData(intent.getIntExtra(BUNDLE_EXTRA_SHOW_TIME_ID,0))
        }else{
            Toast.makeText(this,"Unknown Source",Toast.LENGTH_SHORT).show()
        }


    }

    override fun createPresenter() = seatselectionPresenter

    override fun renderSeats(showTimeEntity: ShowTimeEntity) {
        mTheaterLayoutList = showTimeEntity.theaterLayout.getTheaterLayouts()!!


        val tableLayoutParams = TableLayout.LayoutParams()

        val tableRowParams = TableRow.LayoutParams()
        tableRowParams.setMargins(8, 8, 8, 8)

        mTheaterLayoutList.forEachIndexed{ i, rowLayout ->

            val tableRow = TableRow(this)

            val rowName = TextView(this)
            rowName.text = rowLayout.rowName
            rowName.gravity = Gravity.CENTER

            tableRow.addView(rowName, tableRowParams)

            rowLayout.values.forEachIndexed { j, element ->

                val tvCell = SquareTextView(this, element)
                tvCell.tag = "$i,$j"


                when (element) {
                    SEAT_AVAILABLE -> {
                        tvCell.text = (j + 1).toString()
                        tvCell.setOnClickListener(this)
                    }
                    SEAT_UNAVAILABLE -> {
                        tvCell.text = (j + 1).toString()
                        tvCell.isClickable = false
                    }
                    EMPTY_SEAT_PASSAGE -> {
                        // No action needed
                    }
                }

                tableRow.addView(tvCell, tableRowParams)
            }

            tableLayout.addView(tableRow, tableLayoutParams)
        }
    }

    override fun onClick(view: View?) {

        if(view?.id == R.id.btnBookTicket){

            moveToPaymentActivity()

        }else if (::mTheaterLayoutList.isInitialized) {

            val positionArray = (view!!.tag as String).split(",")

            val rowPosition = positionArray[0].toInt()
            val columnPosition = positionArray[1].toInt()

            val element = mTheaterLayoutList[rowPosition].values[columnPosition]

            if (element == SEAT_AVAILABLE) {
                (view as SquareTextView).setTextType(SEAT_SELECTED)
            }

            val txt = mTheaterLayoutList[rowPosition].rowName + (columnPosition+1)
            btnBookTicket.visibility = View.VISIBLE
            btnBookTicket.text = getString(R.string.book_text,txt)
        }

    }

    private fun moveToPaymentActivity() {
        // TODO CHANGE
        val intent = Intent(this, SeatSelectionActivity::class.java)
//        intent.putExtra(SeatSelectionActivity.BUNDLE_EXTRA_SHOW_TIME_ID,mMovie)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}



