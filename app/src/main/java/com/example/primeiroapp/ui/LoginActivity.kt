package com.example.primeiroapp.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.primeiroapp.R
import com.example.primeiroapp.util.autenticar

class LoginActivity : AppCompatActivity()
{
    lateinit var editUsuario : EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()

        val buttonEntrar = findViewById<Button>(R.id.but_entrar)
        val editEmail = findViewById<EditText>(R.id.edit_email)
        val editSenha = findViewById<EditText>(R.id.edit_senha)

        buttonEntrar.setOnClickListener {
            val senha = editSenha.text.toString()
            val email = editEmail.text.toString()
            val autenticou = autenticar(this, email, senha)

            if (autenticou)
            {
                val abrirActivityDashboard = Intent(this, DashboardActivity::class.java)
                //contexto e "destino" ||||| ::class.java - instancia a classe

                startActivity(abrirActivityDashboard)

            }
            else
            {
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                editEmail.setTextColor(Color.RED)
                editSenha.setTextColor(Color.RED)
            }
        }

        val tvHello = findViewById<TextView>(R.id.tv_criar_conta)
        tvHello.setOnClickListener {
            val abrirActivityCadastro = Intent(this, CadastroUsuarioActivity::class.java)
            //contexto e "destino" ||||| ::class.java - instancia a classe

            startActivity(abrirActivityCadastro)
        }
    }

}