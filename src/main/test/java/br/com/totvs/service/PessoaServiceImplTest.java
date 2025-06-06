package br.com.totvs.service;

import br.com.totvs.dto.request.PessoaRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class PessoaServiceImplTest {

    @Autowired
    private PessoaSrvImpl pessoaSrvMock;
    @BeforeEach
    void setup(){

        var pessoa = new PessoaRequestDTO();
        pessoa.setNome("Marcos Chagas");
        pessoa.setCpf("80553400215");
        pessoa.setProfissao("DEV");
        pessoa.setApelido("Marquito");
        pessoa.setSalario(1000);
        pessoa.setDataNascimento(new Date());
        this.pessoaSrvMock.salvar(pessoa);
    }
    @Test
    public void getUmTest() {
        var pessoa = this.pessoaSrvMock.getUm(1);
        assertEquals(1,pessoa.getId());
    }
    @Test
    public void listarTodosTest() {
        var pageable = PageRequest.of(0,10);
        var pessoaList = this.pessoaSrvMock.listarTodos(pageable);
        assertEquals(1, pessoaList.size());
    }
    @Test
    public void salvarTest() {
        var pessoa = new PessoaRequestDTO();
        pessoa.setNome("Francisca Daiane");
        pessoa.setCpf("80553400215");
        pessoa.setProfissao("PROF");
        pessoa.setApelido("Day");
        pessoa.setSalario(1000);
        pessoa.setDataNascimento(new Date());

        var pessoaResponse = this.pessoaSrvMock.salvar(pessoa);
        assertEquals(3,pessoaResponse.getId());
    }
    @Test
    public void updateTest() {
        var pessoa = new PessoaRequestDTO();
        pessoa.setNome("Marcos Chagas");
        pessoa.setCpf("80553400215");
        pessoa.setProfissao("DEV");
        pessoa.setApelido("Ganso");
        pessoa.setSalario(1000);
        pessoa.setDataNascimento(new Date());
        pessoa.setId(1);

        var pessoaResponse = this.pessoaSrvMock.salvar(pessoa);
        assertEquals("Ganso",pessoaResponse.getApelido());
    }
    @Test
    void excluirTest() {
        this.pessoaSrvMock.excluir(1);
        assertThrows(
                Exception.class,
                () -> this.pessoaSrvMock.getUm(1)
        );
    }}
