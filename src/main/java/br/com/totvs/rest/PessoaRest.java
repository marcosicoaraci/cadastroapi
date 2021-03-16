package br.com.totvs.rest;

import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Dependente;
import br.com.totvs.entity.Endereco;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ExceptionResponse;
import br.com.totvs.service.ContatoSrv;
import br.com.totvs.service.DependenteSrv;
import br.com.totvs.service.EnderecoSrv;
import br.com.totvs.service.PessoaSrv;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Api(value = "Pessoa", tags = {"Pessoa"})
@RestController
@RequestMapping(value = "/pessoa", produces = "application/json")
public class PessoaRest {

    @Autowired
    private PessoaSrv pessoaSrv;

    @Autowired
    private ContatoSrv contatoSrv;

    @Autowired
    private DependenteSrv dependenteSrv;

    @Autowired
    private EnderecoSrv enderecoSrv;

    @GetMapping(value = "/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Recupera uma pessoa passando o ID",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<Pessoa> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(pessoaSrv.getUm(id), HttpStatus.OK);
    }

    @GetMapping(value = "/",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna uma lista de pessoas ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<List<Pessoa>> get(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                    @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return new ResponseEntity<>(pessoaSrv.listarTodos(pageable), HttpStatus.OK);
    }

    @PostMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Incluir Pessoa", description = "Realiza o cadastro da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<Pessoa> post(@RequestBody Pessoa pessoa) {
        pessoaSrv.salvar(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }


    @PutMapping(path = "/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar Pessoa", description = "Realiza a atualização do cadastro da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<Pessoa> put(@RequestBody Pessoa pessoa) {
        pessoaSrv.salvar(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remover Pessoa", description = "Realiza a exclusão do cadastro da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        pessoaSrv.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/contatos/{idPessoa}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar Contatos Pessoa", description = "Retorna os contatos cadastrados da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido ao usuário",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<List<Contato>> getContatos(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(contatoSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }

    @GetMapping(value = "/enderecos/{idPessoa}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar Endereços Pessoa", description = "Retorna os endereços cadastrados da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido ao usuário",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<List<Endereco>> getEnderecos(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(enderecoSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }

    @GetMapping(value = "/dependentes/{idPessoa}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar Dependentes Pessoa", description = "Retorna os dependentes cadastrados da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "401", description = "Não autorizado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido ao usuário",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<List<Dependente>> getDependentes(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(dependenteSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }


}
