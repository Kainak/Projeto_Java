package model.dao;

import model.entities.Fornecedor;

import java.util.List;

public interface FornecedorDao {
    void insert(Fornecedor obj);
    Fornecedor findById(Integer id);
    List<Fornecedor> findAll();
    void deleteById(Integer id);
    void update(Fornecedor obj);
}
