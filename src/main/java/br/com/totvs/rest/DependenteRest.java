package br.com.totvs.rest;


import br.com.totvs.entity.Dependente;
import br.com.totvs.service.DependenteSrv;
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
@RequestMapping(value = "/dependentes", produces = "application/json")
public class DependenteRest {

    @Autowired
    private DependenteSrv dependenteSrv;

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<Dependente> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(dependenteSrv.getUm(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{idPessoa}/salvardependente",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Dependente> post(@PathVariable("idPessoa") Integer idPessoa, @RequestBody Dependente dependente) {
        dependenteSrv.salvar(dependente,idPessoa);

        return new ResponseEntity<>(dependente, HttpStatus.OK);
    }

    @RequestMapping(value = "/{idPessoa}/updatedependente",method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Dependente> put(@PathVariable("idPessoa") Integer idPessoa,@RequestBody Dependente dependente) {
        dependenteSrv.salvar(dependente,idPessoa);

        return new ResponseEntity<>(dependente, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        dependenteSrv.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
