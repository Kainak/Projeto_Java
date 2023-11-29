package model.dao.impl;

import model.entities.Fornecedor;

import java.util.List;

public interface FornecedorDao {
    void insert(Fornecedor obj);
    Fornecedor findById(Integer id);
    List<Fornecedor> findAll();
    void deleteById(Integer id);
    void update(Fornecedor obj);
    List<Fornecedor> findByCategoria(String categoria); // novo método
}
