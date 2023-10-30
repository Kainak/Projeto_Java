package model.dao.impl;

import model.entities.Documento;

public interface DocumentoDao {
    void insert(Documento obj);

  //  void Recuperar(int args);

    void Recuperar();

    void deleteById();

}
