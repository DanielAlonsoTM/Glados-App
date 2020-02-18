package com.samuraitech.gladosapp.utils

import android.content.Context
import android.content.DialogInterface
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.communication.ClientService

object DialogUtils {

    fun alertDialog(view: View, context: Context, inflater: LayoutInflater) {
        val dialogLayout = inflater.inflate(R.layout.popup_wifi, null)

        val builderDialog = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AlertDialogCustom))
        builderDialog.setTitle("Wifi Settings")

        val editText = dialogLayout.findViewById<AppCompatEditText>(R.id.passwordWifi)

        builderDialog.setView(dialogLayout)
        builderDialog.setPositiveButton("Ok") { _: DialogInterface?, _: Int ->
            Thread(Runnable {
                ClientService.sendMessageToServer(editText.text.toString())
            }).start()
        }
        builderDialog.show()
    }
}