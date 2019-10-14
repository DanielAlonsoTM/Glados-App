package com.glados.gladosapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import com.glados.gladosapp.R

object DialogUtils {

    fun alertDialog(view: View, context: Context, inflater: LayoutInflater) {
        val builderDialog = AlertDialog.Builder(context)

        val dialogLayout = inflater.inflate(R.layout.popup_wifi, null)
        val editText = dialogLayout.findViewById<AppCompatEditText>(R.id.passwordWifi)
        val buttonAlert = dialogLayout.findViewById<Button>(R.id.buttonConfirmPassword)

        buttonAlert.setOnClickListener { Utilities.snackBarMessage(view, editText.text.toString()) }

        builderDialog.setView(dialogLayout)
        builderDialog.show()
    }
}