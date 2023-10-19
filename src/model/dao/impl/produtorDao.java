package model.dao.impl;

import model.entities.Produtor;

import java.util.List;

public interface produtorDao {
    void insert(Produtor obj);
    Produtor findById(Integer IDprodutor);

    List<Produtor> findAll();
}
