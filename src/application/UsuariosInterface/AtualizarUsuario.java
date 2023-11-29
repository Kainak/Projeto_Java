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
    private JFormattedTextField ATUALIZAÇÃODEUSUARIOFormattedTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton atualizar;
    private JPasswordField passwordField3;

    private UsuarioDao usuarioDAO;

    private void createUIComponents() {
    }
    public AtualizarUsuario() {
        setTitle("ATUALIZAR USUARIO");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(AtualizarUsuario);
        setVisible(true);
        setLocationRelativeTo(null);
        usuarioDAO = new UsuarioDaoJDBC(DB.getConnection());

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
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
