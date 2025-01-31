package br.com.totvs.entity;

import br.com.totvs.enuns.TipoContato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "contato")
@SequenceGenerator(name="ContatoGenerator", sequenceName="contato_seq", allocationSize=1)
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="ContatoGenerator", strategy= GenerationType.SEQUENCE)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @Column(name = "ddd",nullable = false, length = 2)
    private String ddd;

    @Column(name = "numero",nullable = false, length = 9)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contato",nullable = false, length = 30)
    private TipoContato tipoContato;

    @JoinColumn(name = "pessoa_contato_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoaContatoId;

    public Contato() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Pessoa getPessoaContatoId() {
        return pessoaContatoId;
    }

    public void setPessoaContatoId(Pessoa pessoaContatoId) {
        this.pessoaContatoId = pessoaContatoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato)) return false;
        Contato contato = (Contato) o;
        return Objects.equals(getId(), contato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", ddd='" + ddd + '\'' +
                ", numero='" + numero + '\'' +
                ", tipoContato=" + tipoContato +
                ", pessoaId=" + pessoaContatoId +
                '}';
    }
}
