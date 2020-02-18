package com.samuraitech.gladosapp.utils

import androidx.appcompat.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.samuraitech.gladosapp.R

object GraphicsUtilities {
    fun changeFullColorAppBar(context: Context, window: Window, actBar: ActionBar?) {
        window.statusBarColor = ContextCompat.getColor(context, R.color.colorPrimary)

        actBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        actBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actBar?.setCustomView(R.layout.actionbar_custom)
    }

    fun makeTransparentActionBar(window: Window, actBar: ActionBar?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        actBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
