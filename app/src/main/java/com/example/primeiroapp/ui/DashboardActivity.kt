package com.example.primeiroapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.primeiroapp.R
import com.example.primeiroapp.calcularImc
import com.example.primeiroapp.repository.PesagemRepository
import com.example.primeiroapp.util.calcularIdade
import com.example.primeiroapp.util.convertBase64ToBitmap
import org.w3c.dom.Text


class DashboardActivity : AppCompatActivity() {

    lateinit var nomeUsuario : String
    lateinit var profissaoUsuario : String
    lateinit var alturaUsuario : Any
    lateinit var stringDataNascimentoUsuario : String
    lateinit var stringBase64Imagem : String
    lateinit var pesosUsuario : String

    lateinit var textViewNome : TextView
    lateinit var textViewProfissao : TextView
    lateinit var textViewAltura : TextView
    lateinit var textViewIdade : TextView
    lateinit var textViewPeso : TextView
    lateinit var imageViewFotoPerfil : ImageView
    lateinit var textViewImc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //instanciando campos a serem modificados
        textViewNome = findViewById<TextView>(R.id.text_view_nome)
        textViewProfissao = findViewById<TextView>(R.id.text_view_profissao)
        textViewAltura = findViewById<TextView>(R.id.text_view_altura)
        textViewIdade = findViewById<TextView>(R.id.text_view_idade)
        imageViewFotoPerfil = findViewById<ImageView>(R.id.image_view_foto_perfil_dashboard)
        textViewPeso = findViewById(R.id.text_view_peso)
        textViewImc = findViewById(R.id.text_view_imc)
        var cardRegistrarPesagem = findViewById<CardView>(R.id.card_registrar_pesagem)
        var cardVerHistorico = findViewById<CardView>(R.id.card_historico)

        carregarDashboard()


        cardRegistrarPesagem.setOnClickListener {

                val abrirActivityRegistrarPeso = Intent(this, CadastroPesoActivity::class.java)
            //contexto e "destino" ||||| ::class.java - instancia a classe

            startActivity(abrirActivityRegistrarPeso)
        }

        cardVerHistorico.setOnClickListener {
            val abrirActivityHistorico = Intent(this, HistoricoActivity::class.java)
            //contexto e "destino" ||||| ::class.java - instancia a classe

            startActivity(abrirActivityHistorico)
        }

        supportActionBar!!.hide()
    }

    private fun carregarDashboard()
    {
        //pegando o sharedPreferences para conseguir os ddados
        val arquivo = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        //pegando os dados do shared preferences
        nomeUsuario = arquivo.getString("nome", "Erro ao recuperar dado")!!
        profissaoUsuario = arquivo.getString("profissao", "Erro ao recuperar dado")!!
        alturaUsuario = arquivo.getFloat("altura", 0.020F)
        stringDataNascimentoUsuario = arquivo.getString("dataNascimento", "2000-01-01")!!
        stringBase64Imagem = arquivo.getString("fotoPerfil", "")!!
        pesosUsuario = arquivo.getString("pesos", "00")!!



        //inserindo dados nos respectivos campos
            //criando dados
            val idadeUsuario = calcularIdade(stringDataNascimentoUsuario)
            val imcUsuario = calcularImc(this)
            val arrayPesos = pesosUsuario.split(";")
            //val quantidadePesagens = arrayPesos.size
            val ultimoPesoUsuario = arrayPesos[arrayPesos.lastIndex];

            //modificando valores dos campos
            textViewAltura.text = alturaUsuario.toString()
            textViewNome.text = nomeUsuario
            textViewProfissao.text = profissaoUsuario
            textViewIdade.text = idadeUsuario.toString()
            imageViewFotoPerfil.setImageBitmap(convertBase64ToBitmap(stringBase64Imagem))
            textViewPeso.text = ultimoPesoUsuario
            textViewImc.text = imcUsuario.toString()

    }
}