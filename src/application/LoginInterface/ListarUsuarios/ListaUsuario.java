package application.LoginInterface.ListarUsuarios;

import application.LoginInterface.CriarUsuario.CriarUsuario;
import application.LoginInterface.AtualizarUsuario.AtualizarUsuario;
import application.LoginInterface.DeletarUsuario.DeletarUsuario;
import application.FornedoresInterface.MainFrame;
import db.DB;
import model.dao.impl.UsuarioDao;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListaUsuario extends JFrame {

    private JPanel formularioUsuario;
    private JButton adicionarUsuarioButton;
    private JButton atualizarUsuarioButton;
    private JButton deletarUsuarioButton;
    private JButton voltarButton;
    private JTable tabelaUsuario;
    private JScrollPane categoriasUsuario;

    private UsuarioDao usuarioDao;

    public ListaUsuario() {
        setTitle("Formulário de Usuario");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        usuarioDao = new UsuarioDaoJDBC(DB.getConnection());

        voltarButton.addActionListener(e -> {
            new MainFrame();
            this.dispose();
        });

        adicionarUsuarioButton.addActionListener(e -> {
            new CriarUsuario();
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

        setContentPane(formularioUsuario);

        setVisible(true);
        createTable();
    }

    private void createTable() {
        List<Usuario> usuarios = usuarioDao.findAll();
        Object[][] data = new Object[usuarios.size()][3];
        for (int i = 0; i < usuarios.size(); i++) {
            data[i][0] = usuarios.get(i).getIDUsuario();
            data[i][1] = usuarios.get(i).getEmailUsuario();
            data[i][2] = usuarios.get(i).getPasswordUsuario();
        }
        tabelaUsuario.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Email", "Password"}
        ));
    }

}
