package com.example.primeiroapp

fun calcularImc (peso:Int, altura:Double) : Double
//fun de Function | variavel:tipo | () : retorno
{
    return peso / (altura * altura)
}