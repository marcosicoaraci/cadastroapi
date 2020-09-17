package br.com.totvs.entity;

import br.com.totvs.enuns.TipoContato;

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
@Table(name = "contato")
@SequenceGenerator(name="ContatoGenerator", sequenceName="contato_seq", allocationSize=1)
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="ContatoGenerator", strategy= GenerationType.SEQUENCE)
    @Column(name = "id")
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
