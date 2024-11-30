package vn.finance.onboarding

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

const val ONBOARDING = "onboarding"

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}