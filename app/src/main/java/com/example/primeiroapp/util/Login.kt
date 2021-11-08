package com.example.primeiroapp.util

import android.content.Context
import android.content.SharedPreferences


fun autenticar (tela : Context, email: String, senha:String) : Boolean
{
    val sharedPreference = tela.getSharedPreferences("usuario",Context.MODE_PRIVATE)
    val emailSharedPreference = sharedPreference.getString("email", "")
    val senhaSharedPreference = sharedPreference.getString("senha", "")

    return ((emailSharedPreference == email) && (senhaSharedPreference == senha))

}