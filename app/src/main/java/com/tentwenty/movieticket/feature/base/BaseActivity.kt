package com.tentwenty.movieticket.feature.base

import android.os.Bundle
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.utils.util.ProgressDialogHelper


abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>(), BaseView {
    private var dialogHelper: ProgressDialogHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showLoading() {
        if (dialogHelper == null)
            dialogHelper = ProgressDialogHelper(this)
        dialogHelper?.show(getString(R.string.loading))
    }

    override fun hideLoading() {
        if (dialogHelper != null)
            dialogHelper?.dismiss()
    }

    override fun onDestroy() {
        if (dialogHelper == null) {
            dialogHelper?.destroy()
        }
        super.onDestroy()
    }

    override fun showToast(strMsg: String) {
        Toast.makeText(this,strMsg,Toast.LENGTH_LONG).show()
    }

    override fun showToast(msgId: Int) {
        Toast.makeText(this,getString(msgId),Toast.LENGTH_LONG).show()
    }
}