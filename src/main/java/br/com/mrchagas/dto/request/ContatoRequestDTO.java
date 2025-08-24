package br.com.mrchagas.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class ContatoRequestDTO {
    private Integer id;
    @Schema(type = "string", example = "91")
    private String ddd;
    @Schema(type = "string", example = "981665544")
    private String numero;
    @Schema(type = "string", example = "RESIDENCIAL")
    private String tipoContato;
    @Schema(type = "integer", example = "1")
    private Integer pessoaContatoId;

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Integer getPessoaContatoId() {
        return pessoaContatoId;
    }

    public void setPessoaContatoId(Integer pessoaContatoId) {
        this.pessoaContatoId = pessoaContatoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
