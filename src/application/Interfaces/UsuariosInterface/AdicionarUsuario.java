package application.Interfaces.UsuariosInterface;

import model.dao.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarUsuario extends JFrame {
    private JTextField nomeUsuario;
    private JTextField telefoneUsuario;
    private JButton salvarButton;
    private JButton voltarButton;
    private JPanel adicionarUsuario;

    private UsuarioDao usuarioDAO;

    public AdicionarUsuario() {
        setTitle("Adicionar Usuário");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(adicionarUsuario);
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
                new FormularioUsuarios();
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
