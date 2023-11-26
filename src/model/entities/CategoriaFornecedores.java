package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaFornecedores implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer categoriaid;
    private String nomecategoria;

    public CategoriaFornecedores() {
    }

    public CategoriaFornecedores(Integer categoriaid, String nomecategoria) {
        this.categoriaid = categoriaid;
        this.nomecategoria = nomecategoria;
    }

    public Integer getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(Integer categoriaid) {
        this.categoriaid = categoriaid;
    }

    public String getNomecategoria() {
        return nomecategoria;
    }

    public void setNomecategoria(String nomecategoria) {
        this.nomecategoria = nomecategoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CategoriaFornecedores categoria = (CategoriaFornecedores) obj;
        return Objects.equals(categoriaid, categoria.categoriaid) &&
                Objects.equals(nomecategoria, categoria.nomecategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriaid, nomecategoria);
    }
}
