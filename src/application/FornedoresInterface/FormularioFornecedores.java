package application.FornedoresInterface;


import model.dao.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.entities.Fornecedor;
import db.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FormularioFornecedores extends JFrame {

    private JPanel formularioFornecedores;
    private JButton adicionarFornecedorButton;
    private JButton atualizarFornecedorButton;
    private JButton deletarFornecedorButton;
    private JButton voltarButton;
    private JTable tabelaFornecedores;
    private JScrollPane categoriasFornecedores;
    private JScrollBar scrollBar1;

    private FornecedorDao fornecedorDAO;

    public FormularioFornecedores() {
        setTitle("Formulário de Fornecedores");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        fornecedorDAO = new FornecedorDaoJDBC(DB.getConnection());

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
        setContentPane(formularioFornecedores);

        setVisible(true);
        createTable();
    }

    private void createTable() {
        List<Fornecedor> fornecedores = fornecedorDAO.findAll();
        Object[][] data = new Object[fornecedores.size()][2];
        for (int i = 0; i < fornecedores.size(); i++) {
            data[i][0] = fornecedores.get(i).getNome();
            data[i][1] = fornecedores.get(i).getTelefone();
        }
        tabelaFornecedores.setModel(new DefaultTableModel(
                data,
                new String[]{"Nome", "Telefone"}
        ));
    }
}
