package application.FornedoresInterface;

import model.dao.impl.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.CategoriaFornecedoresDao;
import model.dao.impl.CategoriaFornecedoresDaoJDBC;
import model.entities.Fornecedor;
import model.entities.CategoriaFornecedores;
import db.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListaFornecedores extends JFrame {

    private JPanel formularioFornecedores;
    private JButton adicionarFornecedorButton;
    private JButton atualizarFornecedorButton;
    private JButton deletarFornecedorButton;
    private JButton voltarButton;
    private JTable tabelaFornecedores;
    private JScrollPane categoriasFornecedores;
    private JComboBox categoriaEscolha;
    private JButton buscarButton;

    private FornecedorDao fornecedorDAO;
    private CategoriaFornecedoresDao categoriaDAO;

    public ListaFornecedores() {
        setTitle("Formulário de Fornecedores");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        fornecedorDAO = new FornecedorDaoJDBC(DB.getConnection());
        categoriaDAO = new CategoriaFornecedoresDaoJDBC(DB.getConnection());

        voltarButton.addActionListener(e -> {
            new MainFrame();
            this.dispose();
        });

        adicionarFornecedorButton.addActionListener(e -> {
            new AdicionarFornecedor();
            this.dispose();
        });
        atualizarFornecedorButton.addActionListener(e -> {
            new AtualizarFornecedor();
            this.dispose();
        });
        deletarFornecedorButton.addActionListener(e -> {
            new DeletarFornecedor();
            this.dispose();
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFornecedoresPorCategoria();
            }
        });

        setContentPane(formularioFornecedores);

        setVisible(true);
        createTable();
        preencherCategorias();
    }

    private void createTable() {
        List<Fornecedor> fornecedores = fornecedorDAO.findAll();
        preencherTabela(fornecedores);
    }

    private void preencherCategorias() {
        List<CategoriaFornecedores> categorias = categoriaDAO.findAll();
        for (CategoriaFornecedores categoria : categorias) {
            categoriaEscolha.addItem(categoria.getNome());
        }
    }

    private void buscarFornecedoresPorCategoria() {
        String categoria = (String) categoriaEscolha.getSelectedItem();
        List<Fornecedor> fornecedores = fornecedorDAO.findByCategoria(categoria);
        preencherTabela(fornecedores);
    }

    private void preencherTabela(List<Fornecedor> fornecedores) {
        Object[][] data = new Object[fornecedores.size()][4];
        for (int i = 0; i < fornecedores.size(); i++) {
            data[i][0] = fornecedores.get(i).getId();
            data[i][1] = fornecedores.get(i).getNome();
            data[i][2] = fornecedores.get(i).getTelefone();
            data[i][3] = fornecedores.get(i).getCategoria();
        }
        tabelaFornecedores.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Nome", "Telefone", "Categoria"}
        ));
    }
}
