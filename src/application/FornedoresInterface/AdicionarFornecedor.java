package application.FornedoresInterface;

import model.dao.impl.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.CategoriaFornecedoresDao;
import model.dao.impl.CategoriaFornecedoresDaoJDBC;
import model.entities.CategoriaFornecedores;
import model.entities.Fornecedor;

import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class AdicionarFornecedor extends JFrame {
    private JTextField nomeFornecedor;
    private JTextField telefoneFornecedor;
    private JButton voltarButton;
    private JButton salvarButton;
    private JPanel adicionarFornecedor;
    private JComboBox categoriaSeleciona;

    private FornecedorDao fornecedorDAO;
    private CategoriaFornecedoresDao categoriaDAO;
    private HashMap<String, Integer> categoriaMap;

    public AdicionarFornecedor() {
        setTitle("Adicionar Fornecedor");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(adicionarFornecedor);
        setVisible(true);
        setLocationRelativeTo(null);
        fornecedorDAO = new FornecedorDaoJDBC(DB.getConnection());
        categoriaDAO = new CategoriaFornecedoresDaoJDBC(DB.getConnection());
        categoriaMap = new HashMap<>();

        preencherCategorias();

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarFornecedor();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaFornecedores();
                dispose();
            }
        });
    }

    private void preencherCategorias() {
        List<CategoriaFornecedores> categorias = categoriaDAO.findAll();
        for (CategoriaFornecedores categoria : categorias) {
            categoriaSeleciona.addItem(categoria.getNome());
            categoriaMap.put(categoria.getNome(), categoria.getId());
        }
    }

    private void salvarFornecedor() {
        String nome = nomeFornecedor.getText();
        String telefone = telefoneFornecedor.getText();
        String categoria = (String) categoriaSeleciona.getSelectedItem();
        int categoriaid = categoriaMap.get(categoria);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setTelefone(telefone);
        fornecedor.setCategoriaid(categoriaid);

        fornecedorDAO.insert(fornecedor);
    }
}
