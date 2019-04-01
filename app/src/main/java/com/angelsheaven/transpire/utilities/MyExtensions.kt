package com.angelsheaven.transpire.utilities

import android.view.View
import android.widget.TextView
import com.angelsheaven.transpire.R
import com.google.android.material.snackbar.Snackbar

/**
 * Extension function of Class View used to display a
 * snackbar on top
 * @author Quan Nguyen
 * @param message to display
 * @param backgroundColorResourceId background color of snackbar
 */
fun View.mySnackBar(message: String): Snackbar {
    return Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        val snackBarView = this@apply.view

        /**
         * Align center for TextView message on Snackbar
         */
        val snackBarTextView = snackBarView
            .findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        snackBarTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackBarTextView.setTextColor(this.context.getColor(R.color.text_color_snackbar))

        /**
         * @backgroundColorResourceId is not null then get color and set to snackbar
         */
        val color = context.getColor(R.color.snackbarBackgroundColor)
        snackBarView.setBackgroundColor(color)
    }
}

