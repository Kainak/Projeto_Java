package application.FornedoresInterface;

import model.dao.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.entities.Fornecedor;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarFornecedor extends JFrame {
    private JTextField nomeFornecedor;
    private JTextField telefoneFornecedor;
    private JButton voltarButton;
    private JButton salvarButton;
    private JPanel adicionarFornecedor;

    private FornecedorDao fornecedorDAO;

    public AdicionarFornecedor() {
        setTitle("Adicionar Fornecedor");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(adicionarFornecedor);
        setVisible(true);

        fornecedorDAO = new FornecedorDaoJDBC(DB.getConnection());

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarFornecedor();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormularioFornecedores();
            }
        });
    }

    private void salvarFornecedor() {
        String nome = nomeFornecedor.getText();
        String telefone = telefoneFornecedor.getText();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setTelefone(telefone);

        fornecedorDAO.insert(fornecedor);
    }
}
