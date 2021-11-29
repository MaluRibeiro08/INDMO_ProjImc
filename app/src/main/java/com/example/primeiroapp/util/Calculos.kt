package com.example.primeiroapp

import android.content.Context

fun calcularImc (tela : Context) : Float
//fun de Function | variavel:tipo | () : retorno
{
    val sharedPreference = tela.getSharedPreferences("usuario",Context.MODE_PRIVATE)
    val pesosPesosSharedPreference = sharedPreference.getString("pesos", "")
    val peso = pesosPesosSharedPreference!!.split(";").last().toInt()
    val altura = sharedPreference.getFloat("altura", 0.0f)

//    FORMATAR COM CASAS DECIMAIS
    return peso / (altura * altura)
}