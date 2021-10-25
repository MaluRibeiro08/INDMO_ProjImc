package com.example.primeiroapp.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.primeiroapp.R
import java.util.*

class CadastroUsuarioActivity : AppCompatActivity()
{
    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento : EditText
    lateinit var radioButtonFem : RadioButton
    lateinit var radioButtonMasc : RadioButton
    lateinit var radioGrupSexo : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)

        editEmail = findViewById(R.id.edit_text_email_cadastro)
        editSenha = findViewById(R.id.edit_text_senha_cadastro)
        editNome = findViewById(R.id.edit_text_nome)
        editProfissao = findViewById(R.id.edit_text_profissao)
        editAltura = findViewById(R.id.edit_text_altura)
        editDataNascimento = findViewById(R.id.edit_text_data_nascimento)
        radioButtonFem = findViewById<RadioButton>(R.id.radio_button_fem)
        radioButtonMasc = findViewById<RadioButton>(R.id.radio_button_masc)
        radioGrupSexo = findViewById<RadioGroup>(R.id.radio_group_sexo)

        supportActionBar!!.title = "Perfil"

        //criando calendário
        //instancia um objeto calendário
        val calendario = Calendar.getInstance()

        //Buscando as informações do calendário para que o calendário abra na data de hoje
        val ano = calendario.get(Calendar.YEAR) // ano
        val mes = calendario.get(Calendar.MONTH) // mês
        val dia = calendario.get(Calendar.DAY_OF_MONTH) // ano

        //abrir date picker
        val etDataNascimento = findViewById<EditText>(R.id.edit_text_data_nascimento)
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
            if(validarCampos()) {
                Toast.makeText(this, "Perfil Salvo", Toast.LENGTH_SHORT).show()
            }
            else {
                    Toast.makeText(this, "Complete as informações", Toast.LENGTH_SHORT).show()
            }

        return true;
    }

    fun validarCampos() : Boolean
    {
        var validos = true

        if (editEmail.text.isEmpty())
        {
            editEmail.error = "Email é obrigatório"
            validos = false
        }
        if (editSenha.text.isEmpty())
        {
            editSenha.error = "Senha é obrigatório"
            validos = false
        }
        if (editSenha.text.length < 8)
        {
            editSenha.error = "Senha deve ter no min 8 digitos"
            validos = false
        }
        if (editNome.text.isEmpty())
        {
            editNome.error = "Nome é obrigatório"
            validos = false
        }
        if (editAltura.text.isEmpty())
        {
            editAltura.error = "Altura é obrigatório"
            validos = false
        }
        if (editProfissao.text.isEmpty())
        {
            editProfissao.error = "Profissão é obrigatório"
            validos = false
        }
        if (editDataNascimento.text.isEmpty())
        {
            editDataNascimento.error = "Data de nascimento é obrigatório"
            validos = false
        }
        else
        {
            editDataNascimento.error = null
        }
        if (!radioButtonFem.isChecked && !radioButtonMasc.isChecked)
        {
            radioButtonMasc.error = "Um sexo deve ser selecionado"
            validos = false
        }
        else
        {
            radioButtonMasc.error = null
        }

        return validos
    }
}