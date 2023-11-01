package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String telefone;

    public Fornecedor() {

    }

    public Fornecedor(Integer id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fornecedor fornecedor = (Fornecedor) obj;
        return Objects.equals(id, fornecedor.id) && Objects.equals(nome, fornecedor.nome) && Objects.equals(telefone, fornecedor.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone);
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
