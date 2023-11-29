package application.UsuariosInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import application.MainFrame;
import db.DB;
import model.dao.impl.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import java.util.List;

public class ListaUsuarios extends JFrame {

    private JScrollPane categoriasUsuarios;
    private JTable tabelaUsuarios;
    private JButton voltarButton;
    private JButton adicionarUsuarioButton;
    private JButton atualizarUsuarioButton;
    private JButton deletarUsuarioButton;
    private JPanel formularioUsuarios;
    private UsuarioDao usuarioDAO;


    public ListaUsuarios() {
        setTitle("Formulário de Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 650);
        setLocationRelativeTo(null);
        usuarioDAO = new UsuarioDaoJDBC(DB.getConnection());

        voltarButton.addActionListener(e -> {
            new MainFrame();
            this.dispose();
        });

        adicionarUsuarioButton.addActionListener(e -> {
            new AdicionarUsuario();
            this.dispose();
        });
        atualizarUsuarioButton.addActionListener(e -> {
            new AtualizarUsuario();
            this.dispose();
        });
        deletarUsuarioButton.addActionListener(e -> {
            new DeletarUsuario();
            this.dispose();
        });


        setContentPane(formularioUsuarios);
        setVisible(true);
        createTable();
    }

    private void createTable() {
        List<Usuario> usuarios = usuarioDAO.findAll();
        Object[][] data = new Object[usuarios.size()][3];
        for (int i = 0; i < usuarios.size(); i++) {
            data[i][0] = usuarios.get(i).getId();
            data[i][1] = usuarios.get(i).getEmail();
            data[i][2] = usuarios.get(i).getPassword();
        }
        tabelaUsuarios.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Email", "Password"}
        ));
    }
}
