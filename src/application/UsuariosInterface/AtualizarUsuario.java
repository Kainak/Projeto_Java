package application.UsuariosInterface;

import db.DB;
import model.dao.impl.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarUsuario {
    private JTextField getAtualizaçãoDoUsuarioTextFieldUsuario;
    private JButton voltarButton;
    private JPasswordField passwordField1;
    private JButton atualizar;
    private JTextField textField1;
    private JTextField eMailTextField;

    private UsuarioDao usuarioDAO;

    private void createUIComponents() {
    }
    public AtualizarUsuario() {
        setTitle("ATUALIZAR USUARIO");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(atualizarUsuario);
        setVisible(true);
        setLocationRelativeTo(null);
        usuarioDAO = new UsuarioDaoJDBC(DB.getConnection());

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtualizarUsuario();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaUsuarios();
                dispose();
            }

        });
    }

    private void AtualizarUsuario() {
        String Id = idUsuario.getText();
        String Email = emailUsuario.getText();
        String Password = passwordField1.getSelectedText();

        Usuario usuario = new Usuario();
        usuario.setId(Id);
        usuario.setEmail(Email);
        usuario.setPassword(Password);

        usuarioDAO.update(usuario);
    }
}
