package application.UsuariosInterface;

import db.DB;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarUsuario {

    private JTextField DELETARUSUARIOTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton deletarButton;
    private JButton voltarButton;
}

    private void createUIComponents() {
    }
    public DeletarUsuario() {
        setTitle("DELETAR USUARIO");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(DeletarUsuario);
        setVisible(true);
        setLocationRelativeTo(null);
        usuarioDAO = new UsuarioDaoJDBC(DB.getConnection());

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteById();
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

    private void salvarUsuario() {
        String nome = nomeUsuario.getText();
        String telefone = telefoneUsuario.getText();

        Usuario usuario = new Usuario();
        usuario.setEmail(nome);
        usuario.setPassword(telefone);

        usuarioDAO.insert(usuario);
    }
}