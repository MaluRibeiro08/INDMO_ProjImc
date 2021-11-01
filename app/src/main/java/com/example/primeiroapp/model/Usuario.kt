package com.example.primeiroapp.model

import java.time.LocalDate

//PRIMEIRA FORMA DE CRIAR A CLASSE
    //colocando no parênteses, ele se torna o construtuor da dataClass
    //Data Class é feita justamente para armazenar dados
data class Usuario(
    var id: Int,
    var nome: String,
    var email: String,
    var senha: String,
    var peso: Int,
    var altura: Double,
    var dataNascimento: LocalDate,
    var profissao: String,
    var sexo: Char,
    var fotoPerfil: String
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