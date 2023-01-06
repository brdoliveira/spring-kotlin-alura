package br.com.alura.forum.controller

import br.com.alura.forum.dto.NovoTopicoDTO
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(): List<Topico> = service.listar()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): Topico = service.buscarPorId(id)

    @PostMapping
    fun cadastrar(@RequestBody topico: NovoTopicoDTO) = service.cadastrar(dto)
}