package br.com.totvs.rest;

import br.com.totvs.entity.Endereco;
import br.com.totvs.exceptions.ExceptionResponse;
import br.com.totvs.service.EnderecoSrv;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Endereco", description = "Cadastro de enderecos")
@RestController
@RequestMapping(value = "/endereco", produces = "application/json")
public class EnderecoRest {

    @Autowired
    private EnderecoSrv enderecoSrv;

    @GetMapping(value = "/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Recupera um endereço passando o ID",tags = {"Endereco"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Endereco.class))}),
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
    public ResponseEntity<Endereco> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(enderecoSrv.getUm(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{idPessoa}/salvarendereco",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria um endereço ",tags = {"Endereco"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Endereco.class))}),
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
    public ResponseEntity<Endereco> post(@PathVariable("idPessoa") Integer idPessoa, @RequestBody Endereco endereco) {
        enderecoSrv.salvar(endereco,idPessoa);

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PutMapping(value = "/{idPessoa}/updateendereco",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um endereço ",tags = {"Endereco"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Endereco.class))}),
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
    public ResponseEntity<Endereco> put(@PathVariable("idPessoa") Integer idPessoa, @RequestBody Endereco endereco) {
        enderecoSrv.salvar(endereco,idPessoa);

        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Exclui os dados de um endereço ",tags = {"Endereco"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Endereco.class))}),
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
        enderecoSrv.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
