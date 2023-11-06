package model.dao;

import model.entities.Documento;

public interface DocumentoDao {
    void insert(Documento obj);

    void Recuperar();

    void deleteById();
}
