package model.entities;
import java.io.Serializable;
import java.util.Objects;

public class Produtor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer IDprodutor;

    public Produtor() {

    }

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

    public Produtor(Integer IDprodutor, String nome) {
        this.IDprodutor = IDprodutor;
        this.nome = nome;
    }

    private String nome;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produtor produtor = (Produtor) obj;
        return Objects.equals(IDprodutor, produtor.IDprodutor) && Objects.equals(nome, produtor.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDprodutor, nome);
    }

    @Override
    public String toString() {
        return "produtor{" +
                "IDprodutor=" + IDprodutor +
                ", nome='" + nome + '\'' +
                '}';
    }

}
