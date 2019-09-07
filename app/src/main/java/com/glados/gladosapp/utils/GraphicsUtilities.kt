package com.glados.gladosapp.utils

import androidx.appcompat.app.ActionBar
import android.content.Context
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.glados.gladosapp.R

object GraphicsUtilities {
    fun changeFullColorAppBar(context: Context, window: Window, actBar: ActionBar?) {

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(context, R.color.colorPrimary)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//    val actionBar: ActionBar? = actBar
//    actionBar?.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.gradient_actionbar))
//    actionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_actionbar))
        actBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actBar?.setCustomView(R.layout.actionbar_custom)
    }
}
