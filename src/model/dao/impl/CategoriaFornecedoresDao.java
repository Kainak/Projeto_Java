package model.dao.impl;

import model.entities.CategoriaFornecedores;
import java.util.List;

public interface CategoriaFornecedoresDao {

    void insert(CategoriaFornecedores obj);

    void update(CategoriaFornecedores obj);

    void deleteByIdCategoriaFornecedores(Integer IdCategoriaFornecedores);

    CategoriaFornecedores findByIdCategoriaFornecedores(Integer IdCategoriaFornecedores);

    List<CategoriaFornecedores> findAll();
}
