package com.example.havagas

class Formulario(
    val nome: String,
    val email: String,
    val ingressoListaEmail: Boolean,
    val telefone: String,
    val celular: String,
    val sexo: String,
    val nascimento: String,
    val formação: String,
    val vagas: String
) {
    override fun toString(): String {
        return """
            Nome: $nome
            Telefone: $telefone
            Celular: $celular
            Email: $email
            Data de Nascimento: $nascimento
            Formação: $formação
            Vagas de Interesse: $vagas
            Ingressar na lista: ${if (ingressoListaEmail) "Sim" else "Não"}
            Sexo: $sexo
        """.trimIndent()
    }
}