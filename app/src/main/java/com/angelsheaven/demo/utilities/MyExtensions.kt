package com.angelsheaven.demo.utilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.angelsheaven.demo.R
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

        val snackBarTextView = view
            .findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

        with(snackBarTextView) {
            /**
             * Align center for TextView message on Snackbar
             */
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setTextColor(context.getColor(R.color.text_color_snackbar))
            /**
             * @backgroundColorResourceId is not null then get color and set to snackbar
             */
            setBackgroundColor(context.getColor(R.color.snackbarBackgroundColor))
        }

    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
