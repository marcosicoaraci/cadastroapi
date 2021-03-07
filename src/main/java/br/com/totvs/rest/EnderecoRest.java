package br.com.totvs.rest;

import br.com.totvs.entity.Endereco;
import br.com.totvs.service.EnderecoSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/enderecos", produces = "application/json")
public class EnderecoRest {

    @Autowired
    private EnderecoSrv enderecoSrv;

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Endereco> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(enderecoSrv.getUm(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{idPessoa}/salvarendereco",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Endereco> post(@PathVariable("idPessoa") Integer idPessoa, @RequestBody Endereco endereco) {
        enderecoSrv.salvar(endereco,idPessoa);

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @RequestMapping(value = "/{idPessoa}/updateendereco",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Endereco> put(@PathVariable("idPessoa") Integer idPessoa, @RequestBody Endereco endereco) {
        enderecoSrv.salvar(endereco,idPessoa);

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        enderecoSrv.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
