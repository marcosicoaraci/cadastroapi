package br.com.totvs.service;

import br.com.totvs.dto.request.EnderecoRequestDTO;
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
public class EnderecoServiceImplTest {

    @Autowired
    private PessoaSrvImpl pessoaSrv;
    @Autowired
    private EnderecoSrvImpl enderecoSrv;
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
        var endereco = new EnderecoRequestDTO();
        endereco.setPessoaEnderecoId(1);
        endereco.setCep("66820222");
        endereco.setNumero("988955455");
        endereco.setNome("Biff");
        endereco.setComplemento("xxxxxxxxx");
        endereco.setBairro("Tenoné");
        endereco.setCidade("Belém");
        endereco.setEstado("PA");
        endereco.setPais("Brasil");
        endereco.setTipoEndereco("RESIDENCIAL");
        var enderecoResponse = this.enderecoSrv.salvar(endereco);

        assertEquals(2,enderecoResponse.getId());
    }
    @Test
    public void listarTodosTest() {
        var endereco = new EnderecoRequestDTO();
        endereco.setPessoaEnderecoId(1);
        endereco.setCep("66820222");
        endereco.setNumero("988955455");
        endereco.setNome("Biff");
        endereco.setComplemento("xxxxxxxxx");
        endereco.setBairro("Parque Verde");
        endereco.setCidade("Belém");
        endereco.setEstado("PA");
        endereco.setPais("Brasil");
        endereco.setTipoEndereco("RESIDENCIAL");

        this.enderecoSrv.salvar(endereco);

        var pageable = PageRequest.of(0,10);
        var enderecoList = this.enderecoSrv.listarTodos(pageable);
        assertEquals(1, enderecoList.size());
    }
    @Test
    public void getUmTest() {
        var endereco = this.enderecoSrv.getUm(1);
        assertEquals(1,endereco.getId());
    }
    @Test
    public void updateTest() {
        var endereco = new EnderecoRequestDTO();
        endereco.setPessoaEnderecoId(1);
        endereco.setCep("66820222");
        endereco.setNumero("988955455");
        endereco.setNome("Yasmin");
        endereco.setComplemento("xxxxxxxxx");
        endereco.setBairro("Parque Verde");
        endereco.setCidade("Belém");
        endereco.setEstado("PA");
        endereco.setPais("Brasil");
        endereco.setId(1);
        endereco.setTipoEndereco("RESIDENCIAL");

        var enderecoResponse = this.enderecoSrv.atualizar(endereco);
        assertEquals("Yasmin",enderecoResponse.getNome());
    }
    @Test
    void excluirTest() {
        this.enderecoSrv.excluir(1);
        assertThrows(
                Exception.class,
                () -> this.enderecoSrv.getUm(1)
        );
    }}
