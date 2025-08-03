package br.com.totvs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class DependenteRequestDTO {
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
