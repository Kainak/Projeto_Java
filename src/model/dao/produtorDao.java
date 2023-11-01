package model.dao;

import model.entities.Department;
import model.entities.Produtor;

import java.util.List;

public interface produtorDao {
    void insert(Produtor obj);
    Produtor findById(Integer IDprodutor);

    List<Produtor> findAll();

    void deleteById(Integer id);


    void update(Produtor obj);
}
