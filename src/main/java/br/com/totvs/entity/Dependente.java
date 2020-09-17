package br.com.totvs.entity;

import br.com.totvs.enuns.TipoDependente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dependente")
@SequenceGenerator(name="DependenteGenerator", sequenceName="dependente_seq", allocationSize=1)
public class Dependente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="DependenteGenerator", strategy= GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_dependente",nullable = false, length = 50)
    private TipoDependente tipoDependente;

    @JoinColumn(name = "pessoa_dependente_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoaDependenteId;

    public Dependente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoDependente getTipoDependente() {
        return tipoDependente;
    }

    public void setTipoDependente(TipoDependente tipoDependente) {
        this.tipoDependente = tipoDependente;
    }

    public Pessoa getPessoaDependenteId() {
        return pessoaDependenteId;
    }

    public void setPessoaDependenteId(Pessoa pessoaDependenteId) {
        this.pessoaDependenteId = pessoaDependenteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependente)) return false;
        Dependente that = (Dependente) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoDependente=" + tipoDependente +
                ", pessoaId=" + pessoaDependenteId +
                ", pessoaId=" + pessoaDependenteId +
                '}';
    }
}
