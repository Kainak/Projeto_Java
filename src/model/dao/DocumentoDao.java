package model.dao;

import model.entities.Documento;

import java.util.List;

public interface DocumentoDao {
    void insert(Documento obj);

    void Recuperar(int recuperar);

    void deleteById(int excluir);

    List<Documento> findByProdutorId(int produtorId);

}
