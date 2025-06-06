package br.com.totvs.service;

import br.com.totvs.dto.request.ContatoRequestDTO;
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
public class ContatoServiceImplTest {

    @Autowired
    private PessoaSrvImpl pessoaSrv;
    @Autowired
    private ContatoSrvImpl contatoSrv;
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
        var contato = new ContatoRequestDTO();
        contato.setPessoaContatoId(1);
        contato.setTipoContato("CONTATO");
        contato.setDdd("91");
        contato.setNumero("988955455");
        var contatoResponse = this.contatoSrv.salvar(contato);

        assertEquals(2,contatoResponse.getId());
    }
    @Test
    public void listarTodosTest() {
        var contato = new ContatoRequestDTO();
        contato.setPessoaContatoId(1);
        contato.setTipoContato("CONTATO");
        contato.setDdd("93");
        contato.setNumero("988955455");
        this.contatoSrv.salvar(contato);

        var pageable = PageRequest.of(0,10);
        var contatoList = this.contatoSrv.listarTodos(pageable);
        assertEquals(1, contatoList.size());
    }
    @Test
    public void getUmTest() {
        var contato = this.contatoSrv.getUm(1);
        assertEquals(1,contato.getId());
    }
    @Test
    public void updateTest() {
        var contato = new ContatoRequestDTO();
        contato.setPessoaContatoId(1);
        contato.setId(1);
        contato.setTipoContato("CONTATO");
        contato.setDdd("92");
        contato.setNumero("988955455");
        var contatoResponse = this.contatoSrv.atualizar(contato);
        assertEquals("92",contatoResponse.getDdd());
    }
    @Test
    void excluirTest() {
        this.contatoSrv.excluir(1);
        assertThrows(
                Exception.class,
                () -> this.contatoSrv.getUm(1)
        );
    }}
