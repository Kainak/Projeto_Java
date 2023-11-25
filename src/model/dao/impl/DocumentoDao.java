package model.dao.impl;

import model.entities.Documento;

import java.util.List;

public interface DocumentoDao {

    void insert(Documento obj);

    void recuperar(int recuperar);

    void deleteById(int excluir);

    List<Documento> findByProdutorId(int produtorId);

    Documento findById(int documentoId);
}
