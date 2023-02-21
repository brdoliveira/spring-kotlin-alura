package br.com.alura.forum.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1,
        nome = "Bruno",
        email = "brunio@email.com",
        password = "123"
    )
}