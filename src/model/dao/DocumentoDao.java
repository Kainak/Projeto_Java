package model.dao;

import model.entities.Documento;
import model.entities.Produtor;

import java.util.Date;
import java.util.List;

public interface DocumentoDao {

    void insert(Documento obj);

    void recuperar(int recuperar);

    void deleteById(int excluir);

    List<Documento> findByProdutorId(int produtorId);
}
