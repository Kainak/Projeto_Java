package model.entities;

import java.io.Serializable;
import java.util.Objects;
public class CategoriaFornecedores implements Serializable {

    private static final long serialVersion = 1L;

    private Intenger categoriaid;
    private Intenger nomecategoria;

    public CategoriaFornecedores(){

    }



    public CategoriaFornecedores(Intenger categoriaid , Intenger nomecategoria){
        this.categoriaid = categoriaid;
        this.nomecategoria = nomecategoria;
    }

    public Intenger getCategoriaid() {  return categoriaid; }

    public void SetCategoriaid ( Intenger categoriaid) { this.categoriaid = categoriaid; }

    public string getNomecategoria () return nomecategoria;}

    public voide SetNomecategoria (String nomecategoria) { this.nomecategoria = nomecategoria; }


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