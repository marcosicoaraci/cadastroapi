package br.com.totvs.rest;

import br.com.totvs.dto.request.PessoaRequestDTO;
import br.com.totvs.dto.response.ContatoResponseDTO;
import br.com.totvs.dto.response.DependenteResponseDTO;
import br.com.totvs.dto.response.EnderecoResponseDTO;
import br.com.totvs.dto.response.PessoaResponseDTO;
import br.com.totvs.entity.Pessoa;
import br.com.totvs.exceptions.ExceptionResponse;
import br.com.totvs.service.ContatoSrvImpl;
import br.com.totvs.service.DependenteSrvImpl;
import br.com.totvs.service.EnderecoSrvImpl;
import br.com.totvs.service.PessoaSrvImpl;
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


    private final PessoaSrvImpl pessoaSrvImpl;
    private final ContatoSrvImpl contatoSrvImpl;
    private final DependenteSrvImpl dependenteSrvImpl;
    private final EnderecoSrvImpl enderecoSrvImpl;

    public PessoaRest(PessoaSrvImpl pessoaSrvImpl, ContatoSrvImpl contatoSrvImpl, DependenteSrvImpl dependenteSrvImpl, EnderecoSrvImpl enderecoSrvImpl) {
        this.pessoaSrvImpl = pessoaSrvImpl;
        this.contatoSrvImpl = contatoSrvImpl;
        this.dependenteSrvImpl = dependenteSrvImpl;
        this.enderecoSrvImpl = enderecoSrvImpl;
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
        return new ResponseEntity<>(pessoaSrvImpl.getUm(id), HttpStatus.OK);
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
        return new ResponseEntity<>(pessoaSrvImpl.listarTodos(pageable), HttpStatus.OK);
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
        PessoaResponseDTO pessoaResponseDTO = pessoaSrvImpl.salvar(pessoaRequestDTO);

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
        PessoaResponseDTO pessoaResponseDTO = pessoaSrvImpl.salvar(pessoaRequestDTO);

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
        pessoaSrvImpl.excluir(id);

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
        return new ResponseEntity<>(contatoSrvImpl.listarPorIdPessoa(idPessoa), HttpStatus.OK);
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
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecos(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(enderecoSrvImpl.listarPorIdPessoa(idPessoa), HttpStatus.OK);
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
    public ResponseEntity<List<DependenteResponseDTO>> getDependentes(@PathVariable("idPessoa") Integer idPessoa) {
        return new ResponseEntity<>(dependenteSrvImpl.listarPorIdPessoa(idPessoa), HttpStatus.OK);
    }


}
