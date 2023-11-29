package application.LoginInterface.FazerLogin;

import db.DB;
import model.dao.impl.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FazerLogin extends JFrame{
    private JTextField emailField;
    private JTextField senhaField;
    private JButton login;
    private UsuarioDao usuarioDao;


    public FazerLogin() {
        setTitle("Login");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        usuarioDao = new UsuarioDaoJDBC(DB.getConnection());
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });
    }

    private void doLogin(){

        String email = emailField.getText();
        String password = senhaField.getText();

        Usuario user = new Usuario();
        user.setEmail(email);
        user.setPassword(password);

        usuarioDao.findEmailSenha(email, password);
    }
}
