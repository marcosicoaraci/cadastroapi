package br.com.totvs.service;

import br.com.totvs.dto.request.DependenteRequestDTO;
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
public class DependenteServiceImplTest {

    @Autowired
    private PessoaSrvImpl pessoaSrv;
    @Autowired
    private DependenteSrvImpl dependenteSrv;
    @BeforeEach
    void setup(){

        var pessoa = new PessoaRequestDTO();
        pessoa.setNome("Marcos Chagas");
        pessoa.setCpf("80553400215");
        pessoa.setProfissao("DEV");
        pessoa.setApelido("Marquito");
        pessoa.setSalario(1000);
        pessoa.setDataNascimento(new Date());
        this.pessoaSrv.salvar(pessoa);

    }
    @Test
    public void salvarTest() {

        var dependente = new DependenteRequestDTO();
        dependente.setNome("Doctor Emmet Brown");
        dependente.setTipoDependente("PAI");
        dependente.setIdPessoa(1);
        var dependenteResponse = this.dependenteSrv.salvar(dependente);
        assertEquals(2,dependenteResponse.getId());
    }
    @Test
    public void listarTodosTest() {
        var dependente = new DependenteRequestDTO();
        dependente.setNome("Lea Thompson");
        dependente.setTipoDependente("MAE");
        dependente.setIdPessoa(1);
        this.dependenteSrv.salvar(dependente);

        var pageable = PageRequest.of(0,10);
        var dependenteList = this.dependenteSrv.listarTodos(pageable);
        assertEquals(1, dependenteList.size());
    }
    @Test
    public void getUmTest() {
        var dependente = this.dependenteSrv.getUm(1);
        assertEquals(1,dependente.getId());
    }
    @Test
    public void updateTest() {
        var dependente = new DependenteRequestDTO();
        dependente.setNome("Lea Thompson Mcfly");
        dependente.setTipoDependente("MAE");
        dependente.setIdPessoa(1);
        dependente.setId(1);

        var dependenteResponse = this.dependenteSrv.atualizar(dependente);
        assertEquals("Lea Thompson Mcfly",dependenteResponse.getNome());
    }
    @Test
    void excluirTest() {
        this.dependenteSrv.excluir(1);
        assertThrows(
                Exception.class,
                () -> this.dependenteSrv.getUm(1)
        );
    }}
