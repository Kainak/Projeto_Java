package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Produtor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer IDprodutor;
    private String nome;
    private String cpf;

    public Integer getIDprodutor() {
        return IDprodutor;
    }

    public void setIDprodutor(Integer IDprodutor) {
        this.IDprodutor = IDprodutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Produtor(Integer IDprodutor, String nome, String cpf) {
        this.IDprodutor = IDprodutor;
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produtor produtor = (Produtor) obj;
        return Objects.equals(IDprodutor, produtor.IDprodutor) && Objects.equals(nome, produtor.nome) && Objects.equals(cpf, produtor.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDprodutor, nome, cpf);
    }

    @Override
    public String toString() {
        return "Produtor{" +
                "IDprodutor=" + IDprodutor +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    public void setId(int id) {
    }
}
