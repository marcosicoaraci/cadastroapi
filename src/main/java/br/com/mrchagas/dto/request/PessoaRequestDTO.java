package br.com.mrchagas.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class PessoaRequestDTO {


    private Integer id;
    @Schema(type = "string", example = "Biff Howard Tannen")
    private String nome;
    @Schema(type = "string", example = "Biff")
    private String apelido;
    @Schema(type = "string", example = "99999999978")
    private String cpf;
    @Schema(type = "string", example = "Student")
    private String profissao;
    @Schema(type = "integer", example = "1000")
    private double salario;
    @Schema(type = "string", format = "date", example = "1990-05-15")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
