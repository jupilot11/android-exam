package com.joeydee.android_exam.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.joeydee.android_exam.RandomUserApplication

class ToastHelper {

    companion object {
        fun showText(@StringRes res: Int) {
            val context = RandomUserApplication.context
            Toast.makeText(context, res, Toast.LENGTH_SHORT)
                .show()
        }

        fun showText(context: Context, @StringRes res: Int) {
            Toast.makeText(context, res, Toast.LENGTH_SHORT)
                .show()
        }

        fun showText(text: String?) {
            val context = RandomUserApplication.context
            Toast.makeText(context, text, Toast.LENGTH_SHORT)
                .show()
        }

        fun showText(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_LONG)
                .show()
        }
    }
}