package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaFornecedores implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer IdCategoriaFornecedores;
    private String ForeignKey;
    private String NomeCategoria;

    public CategoriaFornecedores() {
    }
    public CategoriaFornecedores(Integer IdCategoriaFornecedores, String ForeignKey, String NomeCategoria) {
        this.IdCategoriaFornecedores = IdCategoriaFornecedores;
        this.ForeignKey = ForeignKey;
        this.NomeCategoria = NomeCategoria;


        public Integer getIdCategoriaFornecedores() { return IdCategoriaFornecedores; }

        public void setId(Integer IdCategoriaFornecedores) { this.IdCategoriaFornecedores = IdCategoriaFornecedores;}

        public String getForeignKey() { return ForeignKey; }

        public void setNome(String ForeignKey) { this.ForeignKey = ForeignKey;}

        public String NomeCategoria() { return NomeCategoria; }

        public void NomeCategoria(String NomeCategoria) { this.NomeCategoria = NomeCategoria; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CategoriaFornecedores categoriaFornecedores = (CategoriaFornecedores) obj;
            return Objects.equals(IdCategoriaFornecedores, CategoriaFornecedores.IdCategoriaFornecedores) && Objects.equals(ForeignKey, CategoriaFornecedores.ForeignKey) && Objects.equals(NomeCategoria, CategoriaFornecedores.NomeCategoria);
        }


        @Override
        public int hashCode() {
            return Objects.hash(IdCategoriaFornecedores, ForeignKey, NomeCategoria);
        }

        @Override
        public String toString() {
            return "CategoriaFornecedores{" +
                    "IdCategoriaFornecedores=" + IdCategoriaFornecedores +
                    ", ForeignKey='" + ForeignKey + '\'' +
                    ", NomeCategoria='" + NomeCategoria + '\'' +
                    '}';
        }
    }