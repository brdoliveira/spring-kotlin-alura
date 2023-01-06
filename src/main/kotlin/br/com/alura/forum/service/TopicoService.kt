package br.com.alura.forum.service

import br.com.alura.forum.dto.NovoTopicoDTO
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService (
    private var topicos: List<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
){

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
    }

    fun cadastrar(dto: NovoTopicoDTO) {

        topicos.plus(Topico(
            id = topicos.size.toLong(),
            titulo = dto.titulo,
            mensagem = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor)
        ))
    }

}