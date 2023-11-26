package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaFornecedores implements Serializable {


    private static final long serialVersion = 1L;
    private Integer idCategoriaFornecedores;
    private String nomeCategoria;
    
    public CategoriaFornecedores() {
    }
    
    public CategoriaFornecedores(Integer idCategoriaFornecedores, String nomeCategoria) {

        this.idCategoriaFornecedores = idCategoriaFornecedores;
        this.nomeCategoria = nomeCategoria;
    }

    public Integer getIdCategoriaFornecedores() {  return idCategoriaFornecedores; }

    public void SetIdCategoriaFornecedores ( Integer idCategoriaFornecedores) { this.idCategoriaFornecedores = idCategoriaFornecedores; }

    public String getNomeCategoria () {
        return nomeCategoria;}

    public void SetNomeCategoria (String nomeCategoria) {
        this.nomeCategoria = nomeCategoria; }
    
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

    public void setIdCategoriaFornecedores(int id) {
    }

    public void setNomeCategoria(String nomeCategoria) {
    }
}
