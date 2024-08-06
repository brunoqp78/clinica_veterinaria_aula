package org.iftm.gerenciadorveterinarios.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

@DataJpaTest
public class VeterinarioRepositoryTest {

    @Autowired
    private VeterinarioRepository repositorio;

    /**
     * Objetivo: Verificar se a busca por id realmente retorna o veterinário
     * correto.
     * monta o cenário,
     * - arquivo import.sql carrega o cenário (veterinários cadastrados)
     * - definir o id de um veterinário que exista em import.sql
     * executa a ação
     * - executar o método de buscar por id
     * e valida a saída.
     * - verifica se foi retornado um objeto.
     * - verifica se o nome e o email do veterinário retornado corresponde ao
     * esperado.
     */
    @Test
    @DisplayName("Verificar se a busca por id realmente retorna o veterinário correto.")
    public void testarBuscaPorIdExistenteRetornaObjetoCorreto() {
        // Arrange
        Integer idExistente = 1;
        String nomeExistente = "Conceição Evaristo";
        String emailExistente = "conceicao@gmail.com";
        // Act
        Veterinario objetoRetornado = repositorio.getById(idExistente);
        // Assign
        assertNotNull(objetoRetornado);
        assertEquals(nomeExistente, objetoRetornado.getNome());
        assertEquals(emailExistente, objetoRetornado.getEmail());
    }

    /**
     * Objetivo: Verificar se a busca por id realmente retorna o cliente correto.
     * monta o cenário,
     * - arquivo import.sql carrega o cenário (veterinários cadastrados)
     * - definir o id de um veterinário que exista em import.sql
     * executa a ação
     * - executar o método de buscar por id
     * e valida a saída.
     * - verifica se foi retornado um objeto.
     * - verifica se o nome e o email do veterinário retornado corresponde ao
     * esperado.
     */
    @Test
    @DisplayName("Verificar se a busca por id realmente retorna o Veterinário encapsulado.")
    public void testarBuscaPorIdExistenteRetornaObjetoCorretoEncapsulado() {
        // Arrange
        Integer idExistente = 1;
        String nomeExistente = "Conceição Evaristo";
        String emailExistente = "conceicao@gmail.com";
        // Act
        Optional<Veterinario> objetoRetornado = repositorio.findById(idExistente);
        // Assign
        assertTrue(objetoRetornado.isPresent());
        assertEquals(nomeExistente, objetoRetornado.get().getNome());
        assertEquals(emailExistente, objetoRetornado.get().getEmail());
    }

    /**
     * Objetivo: Verificar se a busca por id inexistente retorna nenhum veterinário.
     * monta o cenário,
     * - arquivo import.sql carrega o cenário (veterinários cadastrados)
     * - definir o id de um veterinário que não exista em import.sql
     * executa a ação
     * - executar o método de buscar por id
     * e valida a saída.
     * - verifica se não foi retornado um objeto.
     */
    @Test
    @DisplayName("Verificar se a busca por id inexistente retorna nenhum veterinário.")
    public void testarBuscaPorIdNaoExistenteRetornaNulo() {
        // Arrange
        Integer idExistente = 10;
        // Act
        Veterinario objetoRetornado = repositorio.getById(idExistente);
        // Assign
        assertNull(objetoRetornado);
    }

    /**
     * Objetivo: Verificar se a busca por id inexistente retorna nenhum veterinário.
     * monta o cenário,
     * - arquivo import.sql carrega o cenário (veterinários cadastrados)
     * - definir o id de um veterinário que não exista em import.sql
     * executa a ação
     * - executar o método de buscar por id
     * e valida a saída.
     * - verifica se não foi retornado um objeto.
     */
    @Test
    @DisplayName("Verificar se a busca por id inexistente retorna nenhum veterinário.")
    public void testarBuscaPorIdNaoExistenteRetornaOptionalVazio() {
        // Arrange
        Integer idExistente = 10;
        // Act
        Optional<Veterinario> objetoRetornado = repositorio.findById(idExistente);
        // Assign
        assertTrue(objetoRetornado.isEmpty());
    }

    /**
     * Objetivo: Verificar se a exclusão realmente apaga um registro existente.
     * monta o cenário,
     * - arquivo import.sql carrega o cenário (veterinarios cadastrados)
     * - definir o id de um veterinario que exista em import.sql
     * executa a ação
     * - executar o método de exclusão por id
     * - executar o método de buscar por id
     * e valida a saída.
     * - verificar se o resultado do método de busca é falso ou nulo.
     */
    @Test
    @DisplayName("Verificar se a exclusão realmente apaga um registro existente.")
    public void testarExcluirPorIdApagaVeterinarioExistente() {
        // Arrange
        Integer idExistente = 2;
        Long tamanhoBDEsperado = 1L;

        // Act
        repositorio.deleteById(idExistente);
        Optional<Veterinario> objetoResultante = repositorio.findById(idExistente);

        // Assign
        assertEquals(tamanhoBDEsperado, repositorio.count());
        assertTrue(objetoResultante.isEmpty());
    }

    /**
     * Objetivo: Verificar se a exclusão retorna um erro quando um id não existente
     * é informado.
     * monta o cenário,
     * - arquivo import.sql carrega o cenário (veterinarios cadastrados)
     * - definir o id de um veterinario que não exista em import.sql
     * executa a ação
     * - executar o método de exclusão por id
     * e valida a saída.
     * - verificar se ocorre o erro: EmptyResultDataAccessException
     */

    @Test
    @DisplayName("Verificar se a exclusão retorna um erro quando um id não existente é informado.")
    public void testarExcluirPorIdNaoExistenteGeraErro() {
        // arrange
        Integer idNaoExistente = 10;
        Long tamanhoBDEsperado = 2L;

        // Act e Assign
        assertThrows(EmptyResultDataAccessException.class,
                () -> {
                    repositorio.deleteById(idNaoExistente);
                });
        assertEquals(tamanhoBDEsperado, repositorio.count());
        

    }

}
