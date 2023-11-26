package model.dao.impl;

import model.entities.CategoriaFornecedores;

import java.util.List;

public interface CategoriaFornecedoresDao {

    void insert(CategoriaFornecedores obj);

    CategoriaFornecedores findById(Integer idCategoriaFornecedores);

    List<CategoriaFornecedores> findAll();

    void deleteById(Integer idCategoriaFornecedores);

    void update(CategoriaFornecedores obj);


}
