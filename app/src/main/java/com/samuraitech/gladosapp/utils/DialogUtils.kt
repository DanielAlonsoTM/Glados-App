package com.samuraitech.gladosapp.utils

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText

import com.samuraitech.gladosapp.R

object DialogUtils {

    @SuppressLint("InflateParams")
    fun alertDialog(view: View, inflater: LayoutInflater) {
        val dialogLayout = inflater.inflate(R.layout.dialog_wifi, null)

        val builderDialog = AlertDialog.Builder(ContextThemeWrapper(view.context, R.style.AlertDialogCustom))
        builderDialog.setTitle("Wifi Settings")

        val editText = dialogLayout.findViewById<AppCompatEditText>(R.id.passwordWifi)

        builderDialog.setView(dialogLayout)
        builderDialog.setPositiveButton("Ok") { _: DialogInterface?, _: Int ->
            Utilities.snackBarMessage(view, editText.toString())
        }

        builderDialog.show()
    }
}
