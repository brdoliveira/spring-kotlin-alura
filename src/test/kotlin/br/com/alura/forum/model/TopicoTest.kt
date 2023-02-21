package br.com.alura.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Kotlin Básico",
        mensagem = "Aprendendo Kotlin Básico",
        curso = CursoTest.build(),
        autor = UsuarioTest.build()
    )
}