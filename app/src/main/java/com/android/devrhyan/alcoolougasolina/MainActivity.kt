package com.android.devrhyan.alcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var boxAlcool : TextInputLayout
    private lateinit var textAlcool : TextInputEditText
    private lateinit var boxGasolina : TextInputLayout
    private lateinit var textGasolina : TextInputEditText
    private lateinit var calcButton : Button
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        InitComponentsApp()

        calcButton.setOnClickListener {
            calcularPreco()
        }
    }

    private fun InitComponentsApp() {
        boxAlcool = findViewById(R.id.input_box1)
        textAlcool = findViewById(R.id.edit_alcool)
        boxGasolina = findViewById(R.id.input_box2)
        textGasolina = findViewById(R.id.edit_gasolina)
        calcButton = findViewById(R.id.calcular)
        result = findViewById(R.id.textView2)
    }

    private fun calcularPreco(){
        val precoAlcool = textAlcool.text.toString()
        val precoGasolina = textGasolina.text.toString()

        val validate = validarCampos(precoAlcool, precoGasolina)

        if(validate){
            val testKMOption = (precoAlcool.toDouble() / precoGasolina.toDouble())
            if(testKMOption >= 0.7){
                result.text = "Utilize a gasolina para melhor beneficio."
            } else {
                result.text = "Utilize alcool para melhor beneficio"
            }
        }
    }

    private fun validarCampos(p1: String, p2: String) : Boolean{

        textAlcool.error = null
        textGasolina.error = null

        if(p1.isEmpty()){
            textAlcool.error = "Digite o preço do Alcool."
            return false
        } else if (p2.isEmpty()){
            textGasolina.error = "Digite o preço da Gasolina."
            return false
        }

        return true
    }

}