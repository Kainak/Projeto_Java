package application.FornedoresInterface;

import model.dao.impl.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.entities.Fornecedor;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarFornecedor extends JFrame {
    private JTextField idPesquisarFornecedor;
    private JTextField NomeFornecedor;
    private JTextField TelefoneFornecedor;
    private JButton excluirButton;
    private JButton voltarButton;
    private JButton buscarButton;
    private JPanel deletarFornecedor;

    private FornecedorDao fornecedorDAO;

    public DeletarFornecedor() {
        setTitle("Deletar Fornecedor");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(deletarFornecedor);
        setVisible(true);
        setLocationRelativeTo(null);
        fornecedorDAO = new FornecedorDaoJDBC(DB.getConnection());

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFornecedor();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarFornecedor();
                new ListaFornecedores();
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                new ListaFornecedores();
            }

        });

    }

    private void buscarFornecedor() {
        int id = Integer.parseInt(idPesquisarFornecedor.getText());
        Fornecedor fornecedor = fornecedorDAO.findById(id);
        if (fornecedor != null) {
            NomeFornecedor.setText(fornecedor.getNome());
            TelefoneFornecedor.setText(fornecedor.getTelefone());
        } else {
            JOptionPane.showMessageDialog(this, "Fornecedor não encontrado");
        }
    }

    private void deletarFornecedor() {
        int id = Integer.parseInt(idPesquisarFornecedor.getText());
        fornecedorDAO.deleteById(id);
        NomeFornecedor.setText("");
        TelefoneFornecedor.setText("");
        JOptionPane.showMessageDialog(null, "Deletado com sucesso", "Deletado", JOptionPane.INFORMATION_MESSAGE);
    }
}
