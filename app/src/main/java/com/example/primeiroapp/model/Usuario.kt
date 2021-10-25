package com.example.primeiroapp.model

import java.time.LocalDate

//PRIMEIRA FORMA DE CRIAR A CLASSE
data class Usuario(
    var id: Int,
    var nome: String,
    var email: String,
    var senha: String,
    var peso: Int,
    var altura: Double,
    var dataNascimento: LocalDate,
    var profissao: String,
    var sexo: Char
)

//SEGUNDA FORMA DE CRIAR CLASSE
//class Usuario {
//
//    // DECLARANDO ATRIBUTOS
//    // Não precisa colocar o tipo da variável porque a tipagem é dinâmica. Tem que inicializar a variável
//        //var id: Int = 0
//        //var id = 0
//        //var nome: String = ""
//}