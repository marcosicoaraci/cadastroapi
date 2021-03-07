package br.com.totvs.rest;

import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Dependente;
import br.com.totvs.entity.Endereco;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.service.ContatoSrv;
import br.com.totvs.service.DependenteSrv;
import br.com.totvs.service.EnderecoSrv;
import br.com.totvs.service.PessoaSrv;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/pessoas", produces = "application/json")
public class PessoaRest {

    @Autowired
    private PessoaSrv pessoaSrv;

    @Autowired
    private ContatoSrv contatoSrv;

    @Autowired
    private DependenteSrv dependenteSrv;

    @Autowired
    private EnderecoSrv enderecoSrv;

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Pessoa> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(pessoaSrv.getUm(id), HttpStatus.OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<Pessoa>> get(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                    @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return new ResponseEntity<>(pessoaSrv.listarTodos(pageable), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @Operation(summary = "Incluir Pessoa", description = "Realiza o cadastro da pessoa ")
    public ResponseEntity<Pessoa> post(@RequestBody Pessoa pessoa) {
        pessoaSrv.salvar(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }


    @RequestMapping(value = "/update",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Pessoa> put(@RequestBody Pessoa pessoa) {
        pessoaSrv.salvar(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        pessoaSrv.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/contatos/{idPessoa}",method = GET)
    public ResponseEntity<List<Contato>> getContatos(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(contatoSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }

    @RequestMapping(value = "/enderecos/{idPessoa}",method = GET)
    public ResponseEntity<List<Endereco>> getEnderecos(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(enderecoSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }

    @RequestMapping(value = "/dependentes/{idPessoa}",method = GET)
    public ResponseEntity<List<Dependente>> getDependentes(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(dependenteSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }


}
