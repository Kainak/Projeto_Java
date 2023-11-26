package model.dao.impl;

import model.entities.CategoriaFornecedores;

import java.util.List;

public interface CategoriaFornecedoresDao {

    void insert(CategoriaFornecedores obj);
    CategoriaFornecedores findById(Integer IdCategoriaFornecedores);

    List<CategoriaFornecedores> findAll();

    void deleteById(Integer IdCategoriaFornecedores);

    void update(CategoriaFornecedores obj);

}
