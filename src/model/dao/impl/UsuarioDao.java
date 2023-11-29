package model.dao.impl;

import model.entities.Fornecedor;
import model.entities.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> findAll();

    void insert(Usuario var1);

    void update(Usuario var1);

    void deleteById(Integer var1);

    Usuario findById(Integer var1);

    Usuario findEmailSenha(String email, String senha);

}
