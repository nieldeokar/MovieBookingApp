package com.tentwenty.movieticket.feature.seatselection

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.feature.base.BaseActivity
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import kotlinx.android.synthetic.main.activity_seat_selection.*
import javax.inject.Inject
import android.widget.TableLayout
import android.view.MenuItem
import android.view.View
import com.tentwenty.movieticket.TenTwentyApp
import kotlinx.android.synthetic.main.toolbar.*
import java.util.ArrayList


class SeatSelectionActivity : BaseActivity<SeatSelectionView, SeatSelectionPresenter>(), SeatSelectionView, View.OnClickListener {

    companion object {
        const val BUNDLE_EXTRA_MODEL = "movies_obj"
        const val EMPTY_SEAT_PASSAGE = 0
        const val SEAT_AVAILABLE = 1
        const val SEAT_UNAVAILABLE = 2
        const val SEAT_SELECTED = 3
    }

    @Inject
    lateinit var seatselectionPresenter: SeatSelectionPresenter

    var mTheaterLayoutList = ArrayList<TheaterLayout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TenTwentyApp).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        tableLayout.isStretchAllColumns = true


        setSupportActionBar(toolbar)

        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayUseLogoEnabled(true)
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.setHomeButtonEnabled(true)
        }


        presenter.getData()

    }

    override fun createPresenter() = seatselectionPresenter

    override fun renderSeats(seatsList: ArrayList<TheaterLayout>) {
        mTheaterLayoutList = seatsList

        val tableLayoutParams = TableLayout.LayoutParams()

        val tableRowParams = TableRow.LayoutParams()
        tableRowParams.setMargins(8, 8, 8, 8)

        seatsList.forEachIndexed{ i, rowLayout ->

            val tableRow = TableRow(this)

            val rowName = TextView(this)
            rowName.text = rowLayout.rowName
            rowName.gravity = Gravity.CENTER

            tableRow.addView(rowName, tableRowParams)

            rowLayout.values.forEachIndexed { j, element ->

                val textViewColumn = SquareTextView(this, element)
                textViewColumn.tag = "$i,$j"
                textViewColumn.gravity = Gravity.CENTER


                when (element) {
                    SEAT_AVAILABLE -> {
                        textViewColumn.text = (j + 1).toString()
                    }
                    SEAT_UNAVAILABLE -> {
                        textViewColumn.text = (j + 1).toString()
                    }
                    EMPTY_SEAT_PASSAGE -> {
                        // No action needed
                    }
                }

                textViewColumn.setOnClickListener(this)
                tableRow.addView(textViewColumn, tableRowParams)
            }

            tableLayout.addView(tableRow, tableLayoutParams)
        }
    }

    override fun onClick(view: View?) {

        val positionArray = (view!!.tag as String).split(",")

        val rowPosition = positionArray[0].toInt()
        val columnPosition = positionArray[1].toInt()

        val element = mTheaterLayoutList[rowPosition].values[columnPosition]

        if(element == SEAT_AVAILABLE){
            (view as SquareTextView).setmTextType(SEAT_SELECTED)
        }

        Toast.makeText(this, view?.tag as String, Toast.LENGTH_SHORT).show()



    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}



