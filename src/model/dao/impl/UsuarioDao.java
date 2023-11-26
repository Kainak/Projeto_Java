package model.dao.impl;

import model.entities.Usuario;

import java.util.List;

public interface UsuarioDao {
    void insert(Usuario var1);

    void update(Usuario var1);

    void deleteById(Integer var1);

    Usuario findById(Integer var1);

    List<Usuario> findAll();
}
