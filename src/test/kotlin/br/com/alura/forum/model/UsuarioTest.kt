package br.com.alura.forum.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1,
        nome = "BrunoR",
        email = "bruno2000@gmail.com",
        password = "qwerty"
    )
}