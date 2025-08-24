package br.com.mrchagas.rest;


import br.com.mrchagas.dto.request.DependenteRequestDTO;
import br.com.mrchagas.dto.response.DependenteResponseDTO;
import br.com.mrchagas.entity.Dependente;
import br.com.mrchagas.exceptions.ExceptionResponse;
import br.com.mrchagas.service.DependenteSrvImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Dependente", description = "Cadastro de dependentes")
@RestController
@RequestMapping(value = "/dependente", produces = "application/json")
public class DependenteRest {

    private final DependenteSrvImpl dependenteSrvImpl;

    public DependenteRest(DependenteSrvImpl dependenteSrvImpl) {
        this.dependenteSrvImpl = dependenteSrvImpl;
    }

    @GetMapping(value = "/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Recupera um dependente passando o ID",tags = {"Dependente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DependenteResponseDTO.class))}),
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
    public ResponseEntity<DependenteResponseDTO> getUm(
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(dependenteSrvImpl.getUm(id), HttpStatus.OK);
    }

    @PostMapping(value = "/salvardependente",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria um dependente ",tags = {"Dependente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DependenteResponseDTO.class))}),
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
    public ResponseEntity<DependenteResponseDTO> post(@RequestBody DependenteRequestDTO dependenteRequestDTO) {
        DependenteResponseDTO dependente = dependenteSrvImpl.salvar(dependenteRequestDTO);

        return new ResponseEntity<>(dependente, HttpStatus.OK);
    }

    @PutMapping(value = "/{idDependente}/updatedependente",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um dependente ",tags = {"Dependente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DependenteResponseDTO.class))}),
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
    public ResponseEntity<DependenteResponseDTO> put(@PathVariable("idDependente") Integer idDependente,@RequestBody DependenteRequestDTO dependente) {
        dependente.setId(idDependente);
        DependenteResponseDTO dependenteResponseDTO = dependenteSrvImpl.atualizar(dependente);

        return new ResponseEntity<>(dependenteResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Exclui os dados de um dependente ",tags = {"Dependente"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso",
                    content = {@Content(mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Dependente.class))}),
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
        dependenteSrvImpl.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
