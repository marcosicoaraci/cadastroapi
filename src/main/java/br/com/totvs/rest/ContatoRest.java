package br.com.totvs.rest;

import br.com.totvs.entity.Contato;
import br.com.totvs.service.ContatoSrv;
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
@RequestMapping(value = "/contatos", produces = "application/json")
public class ContatoRest {

    @Autowired
    private ContatoSrv contatoSrv;

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Contato> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(contatoSrv.getUm(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/{idPessoa}/salvarcontato",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Contato> post(@PathVariable("idPessoa") Integer idPessoa, @RequestBody Contato contato) {
        contatoSrv.salvar(contato,idPessoa);

        return new ResponseEntity<>(contato, HttpStatus.OK);
    }


    @RequestMapping(value = "/{idPessoa}/updatecontato",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Contato> put(@PathVariable("idPessoa") Integer idPessoa,@RequestBody Contato contato) {
        contatoSrv.salvar(contato,idPessoa);

        return new ResponseEntity<>(contato, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        contatoSrv.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
