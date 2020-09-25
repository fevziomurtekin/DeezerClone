package com.fevziomurtekin.deezer_clone.core

object Env {

    /* database*/
    const val DATABASE_NAME     = "deezerdatabase.db"
    const val DATABASE_VERSION  = 1

    /* API ENV */
    const val BASE_URL          = "https://api.deezer.com"
    const val SECRET_KEY        = "0365cf639fd2d66e00d40acbcfd147d8"
    const val APPLICATION_ID    = "436942"
    const val APPLICATION_CODE  = "fr9635b95a9408fb54545ef7205de5ec"
    const val REDIRECT_URI      = "https://fevziomurtekin.github.io/"

}

/*
* @code https://connect.deezer.com/oauth/auth.php?app_id=436942&redirect_uri=https://fevziomurtekin.github.io/&perms=basic_access,email
* @accestoken https://connect.deezer.com/oauth/access_token.php?app_id=436942&secret=0365cf639fd2d66e00d40acbcfd147d8&code=fr9635b95a9408fb54545ef7205de5ec
*  */