package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.model.TopicoViewTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.*
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {
    private val topicos = PageImpl(listOf(TopicoTest.build()))

    private val paginacao: Pageable = mockk()

    private val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }

    private val topicoViewMapper: TopicoViewMapper = mockk {
        every { map(any()) } returns TopicoViewTest.build()
    }

    private val topicoFormMapper: TopicoFormMapper = mockk()

    private val topicoService = TopicoService(topicoRepository, topicoViewMapper, topicoFormMapper)

    @Test
    fun `deve listar topicos a partir do nome do curso`() {
        topicoService.listar("Kotlin Avançado", paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), paginacao) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar todos topicos quando o nome do curso for nulo`() {
        topicoService.listar(null, paginacao)

        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), paginacao) }
    }

    @Test
    fun `deve retornar not found exception quando curso nao for encontrado por id`() {
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }

        assertThat(actual.message).isEqualTo("Tópico não encontrado!")
    }

    @Test
    fun `deve retornar um topico quando encontrado por id `() {
        val slot = slot<Topico>()

        every { topicoRepository.findById(any()) } returns Optional.of(TopicoTest.build())
        every { topicoViewMapper.map(capture(slot)) } returns TopicoViewTest.build()

        topicoService.buscarPorId(1)

        val topico = TopicoTest.build()
        assertThat(slot.captured.id).isEqualTo(topico.id)
        assertThat(slot.captured.titulo).isEqualTo(topico.titulo)
        assertThat(slot.captured.mensagem).isEqualTo(topico.mensagem)
    }

}