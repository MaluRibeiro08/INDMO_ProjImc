package com.example.primeiroapp.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun convertStringToLocalDate(brazilianDate: String) : LocalDate
{
    //determina qual o padrão de data do brasil
        val dateFormatterFromBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    //Passa a string recebida para um local date, considerando que ela está no formato brasileiro.
        val localDateFormat = LocalDate.parse(brazilianDate, dateFormatterFromBrazil)

    return localDateFormat
}