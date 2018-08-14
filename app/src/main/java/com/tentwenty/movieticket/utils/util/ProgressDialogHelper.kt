package com.tentwenty.movieticket.utils.util

import android.app.ProgressDialog
import android.content.Context


class ProgressDialogHelper(context: Context) {
    private var progressDialog: ProgressDialog? = null

    init {
        progressDialog = ProgressDialog(context)
        progressDialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
    }

    fun show(msg: String) {
        progressDialog?.setMessage(msg)
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    fun dismiss() {
        if (progressDialog == null) {
            return
        }
        progressDialog?.dismiss()
    }

    fun destroy() {
        if (progressDialog == null) {
            return
        }
        progressDialog?.dismiss()
        progressDialog = null
    }
}