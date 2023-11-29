package application.LoginInterface.CriarUsuario;

import db.DB;
import model.dao.impl.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarUsuario extends JFrame{
    private JTextField emailField;
    private JTextField senhaField;
    private JButton criarButton;
    private UsuarioDao usuarioDao;


    public CriarUsuario() {
        setTitle("Login");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        usuarioDao = new UsuarioDaoJDBC(DB.getConnection());
        criarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crateUser();
            }
        });
    }

    private void crateUser(){

        String email = emailField.getText();
        String password = senhaField.getText();

        Usuario user = new Usuario();
        user.setEmail(email);
        user.setPassword(password);

        usuarioDao.insert(user);
    }
}
