package application.FornedoresInterface;

import model.dao.impl.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.CategoriaFornecedoresDao;
import model.dao.impl.CategoriaFornecedoresDaoJDBC;
import model.entities.Fornecedor;
import model.entities.CategoriaFornecedores;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class AtualizarFornecedor extends JFrame {
    private JTextField idPesquisarFornecedor;
    private JButton buscarButton;
    private JTextField novoNomeFornecedor;
    private JTextField novoTelefoneFornecedor;
    private JButton atualizarButton;
    private JButton voltarButton;
    private JPanel atualizarFornecedor;
    private JComboBox categoriaSeleciona;

    private FornecedorDao fornecedorDAO;
    private CategoriaFornecedoresDao categoriaDAO;
    private HashMap<String, Integer> categoriaMap;

    public AtualizarFornecedor() {
        setTitle("Atualizar Fornecedor");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(atualizarFornecedor);
        setLocationRelativeTo(null);
        setVisible(true);

        fornecedorDAO = new FornecedorDaoJDBC(DB.getConnection());
        categoriaDAO = new CategoriaFornecedoresDaoJDBC(DB.getConnection());
        categoriaMap = new HashMap<>();

        preencherCategorias();

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idPesquisarFornecedor.getText());
                buscarFornecedor(id);
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarFornecedor();
                new ListaFornecedores();
                dispose();
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

    private void buscarFornecedor(int id) {
        Fornecedor fornecedor = fornecedorDAO.findById(id);
        if (fornecedor != null) {
            novoNomeFornecedor.setText(fornecedor.getNome());
            novoTelefoneFornecedor.setText(fornecedor.getTelefone());
            CategoriaFornecedores categoria = categoriaDAO.findById(fornecedor.getCategoriaid());
            if (categoria != null) {
                categoriaSeleciona.setSelectedItem(categoria.getNome());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Fornecedor não encontrado");
        }
    }

    private void atualizarFornecedor() {
        int id = Integer.parseInt(idPesquisarFornecedor.getText());
        String nome = novoNomeFornecedor.getText();
        String telefone = novoTelefoneFornecedor.getText();
        String categoriaNome = (String) categoriaSeleciona.getSelectedItem();
        int categoriaId = categoriaMap.get(categoriaNome);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        fornecedor.setNome(nome);
        fornecedor.setTelefone(telefone);
        fornecedor.setCategoriaid(categoriaId);

        fornecedorDAO.update(fornecedor);
        JOptionPane.showMessageDialog(null, "Atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
