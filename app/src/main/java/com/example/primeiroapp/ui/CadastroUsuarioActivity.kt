package com.example.primeiroapp.ui

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.primeiroapp.R
import com.example.primeiroapp.model.Usuario
import com.example.primeiroapp.util.convertStringToLocalDate
import java.time.LocalDate
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
                //SALVANDO REGISTRO NO SHARED PREFERENCES

                    //criando o objeto usuário a ser salvo no SharedPreferences

                        //separando a data para passar como parametro na criação do usuário.
                            //val dataSeparada = editDataNascimento.text.toString().split("/")
                            //LocalDate.of(dataSeparada[2].toInt(), dataSeparada[1].toInt(), dataSeparada[0].toInt())
                            val dataNascimento = convertStringToLocalDate(editDataNascimento.text.toString())

                        //separando a data para passar como parametro na criação do usuário.
                            //val idSexoSelecionado = radioGrupSexo.checkedRadioButtonId
                            //var sexoSelecionado = ""
                            //if (idSexoSelecionado.equals(radioButtonMasc.id))
                            //{
                            //    sexoSelecionado = "M"
                            //}
                            //else if (idSexoSelecionado.equals(radioButtonFem.id))
                           //{
                           //     sexoSelecionado = "F"
                            //}

                        //usando o contrutuor da classe para criá-la. Colocamos os atributos na ordem que está na declaração da classe
                            val usuario = Usuario(
                                1,
                                editNome.text.toString(),
                                editEmail.text.toString(),
                                editSenha.text.toString(),
                                0,
                                editAltura.text.toString().toDouble(),
                                LocalDate.of(dataNascimento.year, dataNascimento.monthValue, dataNascimento.dayOfMonth),
                                editProfissao.text.toString(),
                                if (radioButtonFem.isChecked) 'F' else 'M'
                            )

                    //verificando a existência de um sharedPreferences. Em caso positivo, o abre para edicao; em caso negativo, cria um
                        val dados = getSharedPreferences("usuario", Context.MODE_PRIVATE)
                            //getSharedPreferences recebe o nome do arquivo a ser aberto/criado E o modo com que essa abertura deve acontecer (privada, nesse caso)
                            // "dados" agora representa o arquivo

                    //criando um objeto que permite a edição do SharedPreferences
                        val editor = dados.edit()

                    //clocando as coisas no editor para mandar pro arquivo
                        editor.putInt("id", usuario.id)
                        editor.putString("nome", usuario.nome)
                        editor.putString("email", usuario.email)
                        editor.putString("senha", usuario.senha)
                        editor.putInt("peso", usuario.peso)
                        editor.putFloat("altura", usuario.altura.toFloat())
                        editor.putString("dataNascimento", usuario.dataNascimento.toString())
                        editor.putString("profissao", usuario.profissao)
                        editor.putString("sexo", usuario.sexo.toString())

                    //finalizando a edição: enviando as informação para o arquivo, grando elas nele
                        editor.apply()
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