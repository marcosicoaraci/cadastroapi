package br.com.totvs.rest;

import br.com.totvs.entity.Contato;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ExceptionResponse;
import br.com.totvs.service.ContatoSrv;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Api(value = "Contato", tags = {"Contato"})
@RestController
@RequestMapping(value = "/contato", produces = "application/json")
public class ContatoRest {

    @Autowired
    private ContatoSrv contatoSrv;

    @GetMapping(value = "/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Recupera um contato passando o ID",tags = {"Contato"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Contato.class))}),
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
    public ResponseEntity<Contato> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(contatoSrv.getUm(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{idPessoa}/salvarcontato",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria um contato ",tags = {"Contato"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Contato.class))}),
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
    public ResponseEntity<Contato> post(@PathVariable("idPessoa") Integer idPessoa, @RequestBody Contato contato) {
        contatoSrv.salvar(contato,idPessoa);

        return new ResponseEntity<>(contato, HttpStatus.OK);
    }


    @PutMapping(value = "/{idPessoa}/updatecontato",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um contato ",tags = {"Contato"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Contato.class))}),
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
    public ResponseEntity<Contato> put(@PathVariable("idPessoa") Integer idPessoa,@RequestBody Contato contato) {
        contatoSrv.salvar(contato,idPessoa);

        return new ResponseEntity<>(contato, HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Exclui os dados de um contato ",tags = {"Contato"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Contato.class))}),
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
        contatoSrv.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
