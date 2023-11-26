package application.ProdutorInterface;

import application.DocumentosInterface.ListaDocumentos;
import application.FornedoresInterface.MainFrame;
import db.DB;
import model.dao.impl.ProdutorDao;
import model.dao.impl.ProdutorDaoJDBC;
import model.entities.Produtor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListaProdutores extends JFrame {

    private JPanel formularioProdutores;
    private JButton adicionarProdutorButton;
    private JButton atualizarProdutorButton;
    private JButton deletarProdutorButton;
    private JButton voltarButton;
    private JTable tabelaProdutores;
    private JScrollPane categoriasProdutores;
    private JButton documentosButton;

    private ProdutorDao produtorDAO;

    public ListaProdutores() {
        setTitle("Formulário de Produtores");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        produtorDAO = new ProdutorDaoJDBC(DB.getConnection());

        voltarButton.addActionListener(e -> {
            new MainFrame();
            this.dispose();
        });

        adicionarProdutorButton.addActionListener(e -> {
            new AdicionarProdutor();
            this.dispose();
        });
        atualizarProdutorButton.addActionListener(e -> {
            new AtualizarProdutor();
            this.dispose();
        });
        deletarProdutorButton.addActionListener(e -> {
            new DeletarProdutor();
            this.dispose();
        });

        documentosButton.addActionListener(e -> {
            new ListaDocumentos();
            this.dispose();
        });

        setContentPane(formularioProdutores);

        setVisible(true);
        createTable();
    }

    private void createTable() {
        List<Produtor> produtores = produtorDAO.findAll();
        Object[][] data = new Object[produtores.size()][8];
        for (int i = 0; i < produtores.size(); i++) {
            Produtor produtor = produtores.get(i);
            data[i][0] = produtor.getIDprodutor();
            data[i][1] = produtor.getNome();
            data[i][2] = produtor.getCpf();
            data[i][3] = produtor.getEmail();
            data[i][4] = produtor.getTelefone();
            data[i][5] = produtor.getTelefone2();
            data[i][6] = produtor.getCnpj();
            data[i][7] = produtor.getRazaoSocial();

        }
        tabelaProdutores.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Nome", "CPF", "Email", "Telefone", "Telefone 2", "CNPJ", "Razão Social"}
        ));
    }
}

