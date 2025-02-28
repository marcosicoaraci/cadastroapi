package br.com.totvs.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class DependenteResponseDTO {

    @Schema(type = "string", example = "Doctor Emmet Brown")
    private String nome;
    @Schema(type = "string", example = "PAI")
    private String tipoDependente;
    @Schema(type = "integer", example = "1")
    private Integer idPessoa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDependente() {
        return tipoDependente;
    }

    public void setTipoDependente(String tipoDependente) {
        this.tipoDependente = tipoDependente;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }
}
