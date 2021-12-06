package com.example.primeiroapp.repository

import android.content.Context
import com.example.primeiroapp.model.Pesagem

class PesagemRepository (var context: Context)
{
    //funcao responsavel por criar a lista de pesagem
    fun getListaPesagem () : List<Pesagem>
    {
        val listaPesagem = mutableListOf<Pesagem>()

        //PEGANDO OS DADOS
            //abrindo shared prefs
            val dados = context.getSharedPreferences("usuario", Context.MODE_PRIVATE)

            //pegando os dados especificos
            val pesosString = dados.getString("pesos", "")
            val datasPesagensString = dados.getString("datasPesagens", "")

            //fazendo um vetor/array
            val pesos = pesosString!!.split(';').toTypedArray()
            val datasPesagens = datasPesagensString!!.split(';').toTypedArray()

        //CRIANDO A LISTA DE PESAGEM
            for (indice in 0 until pesos.size)
            // '..' => "Até"
            //for (peso in pesos)
            //a cada iteração, pega um índice (correspondente à quantidade de iterações) do array ("pesos") e atribui à variavel ("peso")
            {
                val pesagem = Pesagem(datasPesagens[indice], pesos[indice].toInt())
                listaPesagem.add(pesagem)
            }

        return listaPesagem
    }
}