package com.example.primeiroapp.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.primeiroapp.R
import com.example.primeiroapp.util.convertBrasilianStringToLocalDate
import com.example.primeiroapp.util.convertInternationalLocalDateToBrazilianString
import java.time.LocalDate
import java.util.*

class CadastroPesoActivity : AppCompatActivity() {
    lateinit var editDataPesagem: EditText
    lateinit var editPesoAtualizado: EditText
    lateinit var buttonRegistrarPesagem: Button
    lateinit var spinnerNiveisAtividadeFisica : Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_peso)

        editDataPesagem = findViewById(R.id.edit_text_data_pesagem)
        editPesoAtualizado = findViewById(R.id.edit_text_peso_atualizado)
        buttonRegistrarPesagem = findViewById(R.id.but_resgistrarPeso)
        spinnerNiveisAtividadeFisica = findViewById(R.id.spinner_niveis)

        //setando a data atual no Edit de data
        editDataPesagem.setText(convertInternationalLocalDateToBrazilianString(LocalDate.now()))


        //criando calendário
        //instancia um objeto calendário
        val calendario = Calendar.getInstance()

        //Buscando as informações do calendário para que o calendário abra na data de hoje
        val ano = calendario.get(Calendar.YEAR) // ano
        val mes = calendario.get(Calendar.MONTH) // mês
        val dia = calendario.get(Calendar.DAY_OF_MONTH) // ano

        //abrir date picker
        editDataPesagem.setOnClickListener {
            val dp = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _diaDoMes ->

                    var diaDoMesFormatado = ""
                    val mesSomadoUm = _mes + 1 //fazemos isso porque o pc define janeiro como 0
                    var mesFormatado = ""

                    if (_diaDoMes < 10) {
                        diaDoMesFormatado = "0$_diaDoMes"
                    } else {
                        diaDoMesFormatado = _diaDoMes.toString()
                    }

                    if (mesSomadoUm < 10) {
                        mesFormatado = "0$mesSomadoUm"
                    } else {
                        mesFormatado = mesSomadoUm.toString()
                    }

                    Log.i("teste", diaDoMesFormatado)
                    Log.i("teste", mesFormatado)

                    editDataPesagem.setText("$diaDoMesFormatado/$mesFormatado/$_ano")
                },
                ano,
                mes,
                dia
            )

            dp.show()

        }

        buttonRegistrarPesagem.setOnClickListener {
            if (salvarRegistroPesagem())
            {
                Toast.makeText(this, "Pesagem salva", Toast.LENGTH_SHORT).show()
                val abrirActivityDashboard= Intent(this, DashboardActivity::class.java)
                    //contexto e "destino" ||||| ::class.java - instancia a classe
                startActivity(abrirActivityDashboard)

            }
            else
            {
                Toast.makeText(this, "Falha ao salvar", Toast.LENGTH_SHORT).show()
            }
        }
        supportActionBar!!.hide()
    }

    fun salvarRegistroPesagem() : Boolean
    {
        if (validarCampos())
        {
            val dataPesagemLocalDate = convertBrasilianStringToLocalDate(editDataPesagem.text.toString())
            val dataPesagemString = dataPesagemLocalDate.toString()

            val nivelAtividadePesagem = spinnerNiveisAtividadeFisica.selectedItem.toString()
            
            val dados = getSharedPreferences("usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()


            val tagNivelAtividadeFisica = "nivelAtividadeFisica-${dataPesagemString}"
            val tagDoPeso = "peso-${dataPesagemString}"
            val tagDaDataPesagem = "dataPesagem-${dataPesagemString}"
            val pesoRegistrado = editPesoAtualizado.text.toString().toInt()
            Log.i("teste", tagDaDataPesagem)
            Log.i("teste", tagDoPeso)

            //clocando as coisas no editor para mandar pro arquivo
            editor.putInt("${tagDoPeso}", pesoRegistrado)
            editor.putString("${tagDaDataPesagem}", dataPesagemString)
            editor.putString("${tagNivelAtividadeFisica}", nivelAtividadePesagem)



            //finalizando a edição: enviando as informação para o arquivo, grando elas nele
            editor.apply()
            return true
        }
        else
        {
            Toast.makeText(this, "Complete as informações", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    fun validarCampos(): Boolean
    {
        var validos = true

        if (editPesoAtualizado.text.isEmpty()) {
            editPesoAtualizado.error = "O peso é obrigatório"
            validos = false
        }
        if (editDataPesagem.text.isEmpty()) {
            editDataPesagem.error = "Data da pesagem é obrigatório"
            validos = false
        } else {
            editDataPesagem.error = null
        }

        return validos
    }
}