package com.pwr.wanderway.utils

import android.content.Context
import android.content.Intent
import com.pwr.wanderway.MainActivity

fun restartMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(intent)
}