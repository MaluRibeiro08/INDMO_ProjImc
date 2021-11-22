package com.example.primeiroapp.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun convertBrasilianStringToLocalDate(brazilianDate: String) : LocalDate
{
    //determina qual o padrão de data do brasil
        val dateFormatterFromBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    //Passa a string recebida para um local date, considerando que ela está no formato brasileiro.
        val localDateFormat = LocalDate.parse(brazilianDate, dateFormatterFromBrazil)

    return localDateFormat
}
fun convertInternationalLocalDateToBrazilianString(internationalLocalDate: LocalDate) : String
{
    //determina qual o padrão de data do brasil
    val dateFormatterFromBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    //Passa a string recebida para um local date, considerando que ela está no formato brasileiro.
    val brazilianDate = internationalLocalDate.format(dateFormatterFromBrazil)

    return brazilianDate.toString()
}

fun convertInternationalStringToLocalDate(internationalDate: String) : LocalDate
{
    //determina qual o padrão de data do brasil
    val dateFormatterFromIntenational = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    //Passa a string recebida para um local date, considerando que ela está no formato brasileiro.
    val localDateFormat = LocalDate.parse(internationalDate, dateFormatterFromIntenational)

    return localDateFormat
}

fun calcularIdade(stringDataNascimentoUsuario : String) : Int
{
    val dataHoje = LocalDate.now()
    val dataNascimentoUsuarioConvertido = convertInternationalStringToLocalDate(stringDataNascimentoUsuario!!)
    val idadeUsuario = dataNascimentoUsuarioConvertido.until(dataHoje, ChronoUnit.YEARS)

    return idadeUsuario.toInt()
}