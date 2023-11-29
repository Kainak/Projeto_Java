package application.LoginInterface.AtualizarUsuario;

import db.DB;
import model.dao.impl.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarUsuario extends JFrame{
    private JTextField emailField;
    private JTextField senhaField;
    private JButton atualizarButton;
    private UsuarioDao usuarioDao;


    public AtualizarUsuario() {
        setTitle("Login");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        usuarioDao = new UsuarioDaoJDBC(DB.getConnection());
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
    }

    private void updateUser(){

        String email = emailField.getText();
        String password = senhaField.getText();

        Usuario user = new Usuario();
        user.setEmail(email);
        user.setPassword(password);

        usuarioDao.update(user);
    }
}
