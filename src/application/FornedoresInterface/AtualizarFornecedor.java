package application.FornedoresInterface;


import model.dao.impl.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.entities.Fornecedor;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarFornecedor extends JFrame {
    private JTextField idPesquisarFornecedor;
    private JButton buscarButton;
    private JTextField novoNomeFornecedor;
    private JTextField novoTelefoneFornecedor;
    private JButton atualizarButton;
    private JButton voltarButton;
    private JPanel atualizarFornecedor;

    private FornecedorDao fornecedorDAO;

    public AtualizarFornecedor() {

        setTitle("Atualizar Fornecedor");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(atualizarFornecedor);
        setLocationRelativeTo(null);
        setVisible(true);

        fornecedorDAO = new FornecedorDaoJDBC(DB.getConnection());

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFornecedor();
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

           }

       });
    }

    private void buscarFornecedor() {
        int id = Integer.parseInt(idPesquisarFornecedor.getText());
        Fornecedor fornecedor = fornecedorDAO.findById(id);
        if (fornecedor != null) {
            novoNomeFornecedor.setText(fornecedor.getNome());
            novoTelefoneFornecedor.setText(fornecedor.getTelefone());
            novoNomeFornecedor.setEnabled(true);
            novoTelefoneFornecedor.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Fornecedor não encontrado");
        }
    }

    private void atualizarFornecedor() {
        int id = Integer.parseInt(idPesquisarFornecedor.getText());
        String nome = novoNomeFornecedor.getText();
        String telefone = novoTelefoneFornecedor.getText();

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        fornecedor.setNome(nome);
        fornecedor.setTelefone(telefone);

        fornecedorDAO.update(fornecedor);
        JOptionPane.showMessageDialog(null, "Atualizado com sucesso", "Atualizado", JOptionPane.INFORMATION_MESSAGE);
    }
}
