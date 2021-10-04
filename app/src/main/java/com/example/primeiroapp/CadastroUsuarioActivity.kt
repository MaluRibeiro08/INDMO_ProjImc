package com.example.primeiroapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import java.util.*

class CadastroUsuarioActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)

        supportActionBar!!.title = "Perfil"

        //criando calendário
        //instancia um objeto calendário
        val calendario = Calendar.getInstance()

        //Buscando as informações do calendário para que o calendário abra na data de hoje
        val ano = calendario.get(Calendar.YEAR) // ano
        val mes = calendario.get(Calendar.MONTH) // mês
        val dia = calendario.get(Calendar.DAY_OF_MONTH) // ano

        //abrir date picker
        val etDataNascimento = findViewById<EditText>(R.id.et_data_nascimento)
        etDataNascimento.setOnClickListener {
            val dp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, _ano, _mes, _diaDoMes -> etDataNascimento.setText("$_diaDoMes/${_mes+1}/$_ano")}, ano, mes, dia)
            //Argumentos para datePickerDialog:
                // - Onde o calendário vai aparecer (na mesma tela "this")
                // - Fução: quando uma data for escolhida ("OnDateSetListener"), vai disparar a função de seta que guarda a data escolhida no calendário e depois joga isso no TextView no formato BR
                // - data, mes e ano que estarão no calendário ao abrir.

            dp.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu_cadastro_usuario, menu) //inflando o menu que a função traz com o menu criado no arquivo.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.menu_iten_salvar ->
            {
                Toast.makeText(this, "Perfil Salvo", Toast.LENGTH_SHORT).show()
            }
        }
        return true;
    }
}