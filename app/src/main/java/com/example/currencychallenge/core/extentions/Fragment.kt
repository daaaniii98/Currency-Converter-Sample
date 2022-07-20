package com.example.currencychallenge.core.extentions

import android.app.Service
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    (requireActivity().getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager).also {
        it.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}