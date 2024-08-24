package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var amb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(amb.root)

        amb.cbCel.setOnCheckedChangeListener { _, isChecked ->
            amb.cel.visibility = if (isChecked) View.VISIBLE else View.GONE
            amb.campoCel.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        amb.inicialSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val formacaoSelecionada = parent?.getItemAtPosition(position).toString()
                when (formacaoSelecionada) {
                    "Fundamental", "Médio" -> {
                        amb.campoAnoFormacao.visibility = View.VISIBLE
                        amb.campoConclusao.visibility = View.GONE
                        amb.campoInstituicao.visibility = View.GONE
                        amb.campoTituloMonografia.visibility = View.GONE
                        amb.campoOrientador.visibility = View.GONE
                    }
                    "Graduação", "Especialização" -> {
                        amb.campoAnoFormacao.visibility = View.GONE
                        amb.campoConclusao.visibility = View.VISIBLE
                        amb.campoInstituicao.visibility = View.VISIBLE
                        amb.campoTituloMonografia.visibility = View.GONE
                        amb.campoOrientador.visibility = View.GONE
                    }
                    "Mestrado", "Doutorado" -> {
                        amb.campoAnoFormacao.visibility = View.GONE
                        amb.campoConclusao.visibility = View.VISIBLE
                        amb.campoInstituicao.visibility = View.VISIBLE
                        amb.campoTituloMonografia.visibility = View.VISIBLE
                        amb.campoOrientador.visibility = View.VISIBLE
                    }
                    else -> {
                        amb.campoAnoFormacao.visibility = View.GONE
                        amb.campoInstituicao.visibility = View.GONE
                        amb.campoConclusao.visibility = View.GONE
                        amb.campoTituloMonografia.visibility = View.GONE
                        amb.campoOrientador.visibility = View.GONE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // NSA
            }
        }

        amb.salvarBt.setOnClickListener {
            val nome = amb.campoNome.text.toString()
            val telefone = if (amb.rgTel.checkedRadioButtonId == R.id.comercial) "Comercial" else "Residencial"
            val celular = if (amb.cbCel.isChecked) amb.campoCel.text.toString() else ""
            val email = amb.campoEmail.text.toString()
            val ingressoListaEmail = amb.cbEmail.isChecked
            val sexo = if (amb.rgSexo.checkedRadioButtonId == R.id.masculino) "Masculino" else "Feminino"
            val nascimento = amb.campoNascimento.text.toString()
            val formacao = amb.inicialSp.selectedItem.toString()
            val vagas = amb.campoVaga.text.toString()
            val anoFormacao = amb.campoAnoFormacao.text.toString().takeIf { it.isNotBlank() }
            val anoConclusao = amb.campoConclusao.text.toString().takeIf { it.isNotBlank() }
            val instituicao = amb.campoInstituicao.text.toString().takeIf { it.isNotBlank() }
            val tituloMonografia = amb.campoTituloMonografia.text.toString().takeIf { it.isNotBlank() }
            val orientador = amb.campoOrientador.text.toString().takeIf { it.isNotBlank() }

            val formulario = Formulario(
                nome = nome,
                email = email,
                ingressoListaEmail = ingressoListaEmail,
                telefone = telefone,
                celular = celular,
                sexo = sexo,
                nascimento = nascimento,
                formação = formacao,
                vagas = vagas,
                anoFormacao = anoFormacao,
                anoConclusao = anoConclusao,
                instituicao = instituicao,
                tituloMonografia = tituloMonografia,
                orientador = orientador
            )

            // Exibir mensagem com os dados do formulário
            Toast.makeText(this, formulario.toString(), Toast.LENGTH_LONG).show()
        }

        amb.limparBt.setOnClickListener {
            amb.campoNome.setText("")
            amb.campoTel.setText("")
            amb.rgTel.check(R.id.residencial)
            amb.campoCel.setText("")
            amb.campoEmail.setText("")
            amb.campoNascimento.setText("")
            amb.campoVaga.setText("")
            amb.campoAnoFormacao.setText("")
            amb.campoConclusao.setText("")
            amb.campoInstituicao.setText("")
            amb.campoTituloMonografia.setText("")
            amb.campoOrientador.setText("")
            amb.cbEmail.isChecked = false
            amb.cbCel.isChecked = false
            amb.rgSexo.check(R.id.masculino)
            amb.inicialSp.setSelection(0)


            amb.campoAnoFormacao.visibility = View.GONE
            amb.campoConclusao.visibility = View.GONE
            amb.campoInstituicao.visibility = View.GONE
            amb.campoTituloMonografia.visibility = View.GONE
            amb.campoOrientador.visibility = View.GONE
        }
    }
}
