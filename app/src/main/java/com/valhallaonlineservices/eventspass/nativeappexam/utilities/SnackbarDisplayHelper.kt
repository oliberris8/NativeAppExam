package com.valhallaonlineservices.eventspass.nativeappexam.utilities

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackbarDisplayHelper(context: Context) {
    fun displaySnackbar(rootLayout: View, message: String) {
        val snackbar: Snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}
