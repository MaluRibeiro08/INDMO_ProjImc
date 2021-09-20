package com.example.primeiroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    //"on creat" primeiro a ser executado. desenha  a tela

    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            //"R" de recursos
            //"activity_main" é o arquivo de layout, onde a tela está descrita

        val buttonCalcular = findViewById<Button>(R.id.button_calcular)
            //criando variável para se referenciar ao botão que colocamos no layout
        val editTextPeso = findViewById<EditText>(R.id.edit_text_peso)
            //criando variável para se referenciar ao edit text que colocamos no layout
        val editTextAltura = findViewById<EditText>(R.id.edit_text_altura)
        val textViewResultado = findViewById<TextView>(R.id.text_view_resultado)
        val textViewStatus = findViewById<TextView>(R.id.text_view_status)

        buttonCalcular.setOnClickListener{
            Toast.makeText(this, "Clicou! :)", Toast.LENGTH_SHORT).show()
                /*"context" -  a tela onde a mensagem vai aparecer
                   "Toast.LENGTH_SHORT" tempo CURTO de exibição da mensgaem na tela
                */

            /*textViewResultado.text = calcularImc(editTextPeso.text.toString().toInt(),
                                        editTextAltura.text.toString().toDouble()).toString()*/

        // ______________ AULA 23.08 _______________
            //professor
                val peso = editTextPeso.text.toString().toInt()
                val altura = editTextAltura.text.toString().toDouble()
                val imc = calcularImc(peso, altura)

                textViewResultado.text= String.format("%.1f",imc)
                    //imc com uma casa decimal


            //eu
               /* var resultado = 0.0
                var status = ""
                resultado = calcularImc(editTextPeso.text.toString().toInt(),
                            editTextAltura.text.toString().toDouble())

                textViewResultado.text = resultado.toString()

                if (resultado < 18.5)
                {
                    status = "Abaixo do peso! :|"
                }
                else if (resultado <24.9)
                {
                    status = "Peso Ideal!! Parabéns :)"
                }
                else if (resultado <29.9)
                {
                    status = "Acima do peso! :|"
                }
                else if (resultado <34.9)
                {
                    status = "Obesidade grau I :|"
                }
                else if (resultado <39.9)
                {
                    status = "Obesidade grau II :|"
                }
                else
                {
                    status = "Obesidade grau III. Cuidado!"
                }

                textViewStatus.text = status */
        }

    }
}