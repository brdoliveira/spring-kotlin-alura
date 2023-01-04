package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService (private var topicos: List<Topico>){
    init {
        val topico1 = Topico(
            id=1,
            titulo = "Duvido kt",
            mensagem = "Variaveis kt",
            curso = Curso(
                id = 1,
                nome = "kt",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Ana S.",
                email = "a.aa@gmail.com"
            )
        )

        val topico2 = Topico(
            id=2,
            titulo = "Duvido kt",
            mensagem = "Variaveis kt",
            curso = Curso(
                id = 1,
                nome = "kt",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Ana S.",
                email = "a.aa@gmail.com"
            )
        )

        val topico3 = Topico(
            id=2,
            titulo = "Duvido kt",
            mensagem = "Variaveis kt",
            curso = Curso(
                id = 1,
                nome = "kt",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Ana S.",
                email = "a.aa@gmail.com"
            )
        )

        topicos = Arrays.asList(topico1,topico2,topico3)
    }

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
    }

}