package br.com.totvs.rest;

import br.com.totvs.dto.request.PessoaRequestDTO;
import br.com.totvs.dto.response.ContatoResponseDTO;
import br.com.totvs.dto.response.PessoaResponseDTO;
import br.com.totvs.entity.Dependente;
import br.com.totvs.entity.Endereco;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ExceptionResponse;
import br.com.totvs.service.ContatoSrv;
import br.com.totvs.service.DependenteSrv;
import br.com.totvs.service.EnderecoSrv;
import br.com.totvs.service.PessoaSrv;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Tag(name = "Pessoa", description = "Cadastro de pessoas")
@RestController
@RequestMapping(value = "/pessoa", produces = "application/json")
public class PessoaRest {


    private final PessoaSrv pessoaSrv;
    private final ContatoSrv contatoSrv;
    private final DependenteSrv dependenteSrv;
    private final EnderecoSrv enderecoSrv;

    public PessoaRest(PessoaSrv pessoaSrv, ContatoSrv contatoSrv, DependenteSrv dependenteSrv, EnderecoSrv enderecoSrv) {
        this.pessoaSrv = pessoaSrv;
        this.contatoSrv = contatoSrv;
        this.dependenteSrv = dependenteSrv;
        this.enderecoSrv = enderecoSrv;
    }

    @GetMapping(value = "/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Recupera uma pessoa passando o ID",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PessoaResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<PessoaResponseDTO> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(pessoaSrv.getUm(id), HttpStatus.OK);
    }

    @GetMapping(value = "/",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna uma lista de pessoas ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PessoaResponseDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<List<PessoaResponseDTO>> get(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                    @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return new ResponseEntity<>(pessoaSrv.listarTodos(pageable), HttpStatus.OK);
    }

    @PostMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Incluir Pessoa", description = "Realiza o cadastro da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PessoaResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<PessoaResponseDTO> post(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        PessoaResponseDTO pessoaResponseDTO = pessoaSrv.salvar(pessoaRequestDTO);

        return new ResponseEntity<>(pessoaResponseDTO, HttpStatus.OK);
    }


    @PutMapping(path = "/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar Pessoa", description = "Realiza a atualização do cadastro da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PessoaResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Objeto Não Encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    public ResponseEntity<PessoaResponseDTO> put(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        PessoaResponseDTO pessoaResponseDTO = pessoaSrv.salvar(pessoaRequestDTO);

        return new ResponseEntity<>(pessoaResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remover Pessoa", description = "Realiza a exclusão do cadastro da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PessoaResponseDTO.class))}),
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
                            schema = @Schema(implementation = ContatoResponseDTO.class))}),
            @ApiResponse(responseCode = "204", description = "Objeto não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<List<ContatoResponseDTO>> getContatos(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(contatoSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }

    @GetMapping(value = "/enderecos/{idPessoa}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar Endereços Pessoa", description = "Retorna os endereços cadastrados da pessoa ",tags = {"Pessoa"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pessoa.class))}),
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
            @ApiResponse(responseCode = "404", description = "Não encontrado",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE)})
    })
    public ResponseEntity<List<Dependente>> getDependentes(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(dependenteSrv.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }


}
