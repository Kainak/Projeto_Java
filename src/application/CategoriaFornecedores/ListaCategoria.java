package application.CategoriaFornecedores;

import application.FornedoresInterface.MainFrame;
import model.dao.impl.CategoriaFornecedoresDao;
import model.dao.impl.CategoriaFornecedoresDaoJDBC;
import model.entities.CategoriaFornecedores;
import db.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListaCategoria extends JFrame {
    private JPanel listaCategoria;
    private JButton adicionarCategoriaButton;
    private JButton atualizarCategoriaButton;
    private JButton deletarCategoriaButton;
    private JButton voltarButton;
    private JTable tabelaCategoria;
    private JScrollPane categoriasCategoria;

    private CategoriaFornecedoresDao CategoriaFornecedoresDao;

    public ListaCategoria() {
        setTitle("Lista de Categorias");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        CategoriaFornecedoresDao = new CategoriaFornecedoresDaoJDBC(DB.getConnection());

        voltarButton.addActionListener(e -> {
            new MainFrame();
            this.dispose();
        });

        adicionarCategoriaButton.addActionListener(e -> {
            new AdicionarCategoria();
            this.dispose();
        });
        atualizarCategoriaButton.addActionListener(e -> {
            new AtualizarCategoria();
            this.dispose();
        });
        deletarCategoriaButton.addActionListener(e -> {
            new DeletarCategoria();
            this.dispose();
        });

        setContentPane(listaCategoria);

        setVisible(true);
        createTable();
    }

    private void createTable() {
        List<CategoriaFornecedores> categorias = CategoriaFornecedoresDao.findAll();
        Object[][] data = new Object[categorias.size()][2];
        for (int i = 0; i < categorias.size(); i++) {
            data[i][0] = categorias.get(i).getId();
            data[i][1] = categorias.get(i).getNome();
        }
        tabelaCategoria.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Nome"}
        ));
    }
}
