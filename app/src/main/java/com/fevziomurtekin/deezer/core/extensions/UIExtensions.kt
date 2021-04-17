package com.fevziomurtekin.deezer.core.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar


/** Show dialogs & snackbar & Toast  **/
object UIExtensions {

    /**
     * @param view, The view used to make the snackbar.
    This should be contained within the view hierarchy you want to display the
    snackbar. Generally it can be the view that was interacted with to trigger
    the snackbar, such as a button that was clicked, or a card that was swiped.
     * @param message, error message.
     * */
    fun showSnackBar(view: View, message: String) =
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                    .show()



    /**
     * @param context, Activity type.
     * hiding keyboard.
     * */
    fun hideKeyboard(view:View,activity: Activity) {
        view.clearFocus()
        val im = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(view.windowToken,0)
    }
}
