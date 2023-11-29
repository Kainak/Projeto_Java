package application.LoginInterface.DeletarUsuario;

import db.DB;
import model.dao.impl.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarUsuario extends JFrame{
    private JTextField emailField;
    private JTextField senhaField;
    private JTextField idField;
    private JButton deleteButton;
    private UsuarioDao usuarioDao;


    public DeletarUsuario() {
        setTitle("Login");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        usuarioDao = new UsuarioDaoJDBC(DB.getConnection());
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });
    }

    private void removeUser(){

        int id = Integer.parseInt(idField.getText());

        String email = emailField.getText();
        String password = senhaField.getText();

        Usuario user = new Usuario();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);

        usuarioDao.deleteById(id);
    }
}
