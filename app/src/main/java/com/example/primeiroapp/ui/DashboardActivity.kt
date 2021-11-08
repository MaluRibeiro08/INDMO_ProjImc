package com.example.primeiroapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.primeiroapp.R
import com.example.primeiroapp.util.convertInternationalStringToLocalDate
import com.example.primeiroapp.util.convertStringToLocalDate
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //pegando o sharedPreferences para conseguir os ddados
        val arquivo = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        //pegando os dados do shared preferences
        val nomeUsuario = arquivo.getString("nome", "Erro ao recuperar dado")
        val profissaoUsuario = arquivo.getString("profissao", "Erro ao recuperar dado")
        val alturaUsuario = arquivo.getFloat("altura", 0.020F)
        val stringDataNascimentoUsuario = arquivo.getString("dataNascimento", "2000-01-01")
        val stringBase64Imagem = arquivo.getString("fotoPerfil", "")

        //inserindo dados nos respectivos campos
            //instanciando campos
                val textViewNome = findViewById<TextView>(R.id.text_view_nome)
                val textViewProfissao = findViewById<TextView>(R.id.text_view_profissao)
                val textViewAltura = findViewById<TextView>(R.id.text_view_altura)
                val textViewIdade = findViewById<TextView>(R.id.text_view_idade)
                val imageViewFotoPerfil = findViewById<ImageView>(R.id.image_view_foto_perfil_dashboard)

            //criando dados
            val dataHoje = LocalDate.now()
            val dataNascimentoUsuario = convertInternationalStringToLocalDate(stringDataNascimentoUsuario!!)
            val idadeUsuario = dataNascimentoUsuario.until(dataHoje,ChronoUnit.YEARS)


            //modificando valores dos campos
                textViewAltura.text = alturaUsuario.toString()
                textViewNome.text = nomeUsuario
                textViewProfissao.text = profissaoUsuario
                textViewIdade.text = idadeUsuario.toString()


        supportActionBar!!.hide()
    }
}