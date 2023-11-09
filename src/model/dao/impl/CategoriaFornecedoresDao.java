package model.dao.impl;

import model.entities.CategoriaFornecedores;
import model.entities.Fornecedor;

import java.util.List;
public interface CategoriaFornecedoresDao {
    void insert(CategoriaFornecedores obj);
    Fornecedor findByIdCategoriaFornecedores(Integer IdCategoriaFornecedores);
    List<CategoriaFornecedores> findAll();
    void deleteByIdCategoriaFornecedores(Integer IdCategoriaFornecedores);
    void update(CategoriaFornecedores obj);
}}
