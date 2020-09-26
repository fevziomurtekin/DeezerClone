package com.fevziomurtekin.deezer_clone.core

import android.app.Activity
import android.provider.Settings.Global.getString
import android.view.View
import com.fevziomurtekin.deezer_clone.R
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
    fun showSnackBar(view:View,message:String) =
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                    .show()



}