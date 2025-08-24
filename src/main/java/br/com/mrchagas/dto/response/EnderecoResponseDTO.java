package br.com.mrchagas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnderecoResponseDTO {
    @Schema(type = "integer", example = "1")
    private Integer id;
    @Schema(type = "string", example = "RESIDENCIAL")
    private String tipoEndereco;
    @Schema(type = "string", example = "Biff Howard Tannen")
    private String nome;
    @Schema(type = "string", example = "981665544")
    private String numero;
    @Schema(type = "string", example = "Rod. Augusto Montenegro")
    private String complemento;
    @Schema(type = "string", example = "66820000")
    private String cep;
    @Schema(type = "string", example = "Tenoné")
    private String bairro;
    @Schema(type = "string", example = "Belém")
    private String cidade;
    @Schema(type = "string", example = "PA")
    private String estado;
    @Schema(type = "string", example = "Brasil")
    private String pais;
    @Schema(type = "integer", example = "1")
    private Integer pessoaEnderecoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getPessoaEnderecoId() {
        return pessoaEnderecoId;
    }

    public void setPessoaEnderecoId(Integer pessoaEnderecoId) {
        this.pessoaEnderecoId = pessoaEnderecoId;
    }
}
