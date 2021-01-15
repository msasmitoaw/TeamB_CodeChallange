package com.suit.team.b.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.content.ContextCompat
import com.suit.team.b.R

fun setWord(context: Context, message: String) {
    makeText(context, message, Toast.LENGTH_SHORT).show()
    val activity = context as Activity
    Log.d(activity.localClassName, message)
}

fun ImageButton.onSelected(context: Context) {
    background = ContextCompat.getDrawable(context, R.drawable.ic_item_bg)
}