package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaFornecedores implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCategoriaFornecedores;
    private String nomeCategoria;

    public CategoriaFornecedores() {

    }

    public CategoriaFornecedores(Integer idCategoriaFornecedores, String nomeCategoria) {
        this.idCategoriaFornecedores = idCategoriaFornecedores;
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getId() {
        return idCategoriaFornecedores;
    }

    public void setId(Integer idCategoriaFornecedores) {
        this.idCategoriaFornecedores = idCategoriaFornecedores;
    }

    public String getNome() {
        return nomeCategoria;
    }

    public void setNome(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoriaFornecedores categoria = (CategoriaFornecedores) obj;
        return Objects.equals(idCategoriaFornecedores, categoria.idCategoriaFornecedores) &&
                Objects.equals(nomeCategoria, categoria.nomeCategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoriaFornecedores, nomeCategoria);
    }

    @Override
    public String toString() {
        return "CategoriaFornecedores{" +
                "idCategoriaFornecedores=" + idCategoriaFornecedores +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                '}';
    }
}
