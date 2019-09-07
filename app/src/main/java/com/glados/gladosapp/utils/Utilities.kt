package com.glados.gladosapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utilities {
    fun snackBarMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}