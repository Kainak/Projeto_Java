package model.entities;

import java.io.Serializable;
import java.util.Objects;
public class CategoriaFornecedores implements Serializable {

    private static final long serialVersion = 1L;
    private Integer IdCategoriaFornecedores;
    private String nomeCategoria;

    public CategoriaFornecedores(){

    }



    public CategoriaFornecedores(Integer categoriaid , String nomecategoria){
        this.categoriaid = categoriaid;
        this.nomecategoria = nomecategoria;
    }

    public Integer getCategoriaid() {  return categoriaid; }

    public void SetCategoriaid ( Integer categoriaid) { this.categoriaid = categoriaid; }

    public void getNomecategoria ( Integer nomecategoria ) {
        return nomecategoria; }

    public void SetNomecategoria (String nomecategoria) {
        this.nomecategoria = nomecategoria; }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fornecedor fornecedor = (CategoriaFornecedores) obj;
        return Objects.equals(categoriaid, CategoriaFornecedores.categoriaid) &&
                Objects.equals(nomecategoria, CategoriaFornecedores.nomecategoria)
    }
    @Override
    public int hashCode() {
        return Objects.hash(categoriaid, nomecategoria);
    }

}