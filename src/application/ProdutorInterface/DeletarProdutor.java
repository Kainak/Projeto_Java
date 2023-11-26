package application.ProdutorInterface;

import model.dao.impl.ProdutorDao;
import model.dao.impl.ProdutorDaoJDBC;
import model.entities.Produtor;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarProdutor extends JFrame {
    private JTextField idPesquisarProdutor;
    private JTextField nomeProdutor;
    private JTextField cpfProdutor;
    private JTextField emailProdutor;
    private JTextField telefoneProdutor;
    private JTextField telefone2Produtor;
    private JTextField cnpjProdutor;
    private JTextField razaoSocialProdutor;
    private JCheckBox ProducaoPropriaCheckBox;
    private JButton excluirButton;
    private JButton voltarButton;
    private JButton buscarButton;
    private JPanel deletarProdutor;
    private ProdutorDao produtorDAO;

    public DeletarProdutor() {
        setTitle("Deletar Produtor");
        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(deletarProdutor);
        setVisible(true);
        setLocationRelativeTo(null);
        produtorDAO = new ProdutorDaoJDBC(DB.getConnection());

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProdutor();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarProdutor();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { new ListaProdutores(); }
        });
    }

    private void buscarProdutor() {
        int id = Integer.parseInt(idPesquisarProdutor.getText());
        Produtor produtor = produtorDAO.findById(id);
        if (produtor != null) {
            nomeProdutor.setText(produtor.getNome());
            cpfProdutor.setText(produtor.getCpf());
            emailProdutor.setText(produtor.getEmail());
            telefoneProdutor.setText(produtor.getTelefone());
            telefone2Produtor.setText(produtor.getTelefone2());
            cnpjProdutor.setText(produtor.getCnpj());
            razaoSocialProdutor.setText(produtor.getRazaoSocial());
            ProducaoPropriaCheckBox.setSelected(produtor.isProducaoPropria());
        } else {
            JOptionPane.showMessageDialog(this, "Produtor não encontrado");
        }
    }


    private void deletarProdutor() {
        int id = Integer.parseInt(idPesquisarProdutor.getText());
        produtorDAO.deleteById(id);
        nomeProdutor.setText("");
        cpfProdutor.setText("");
        emailProdutor.setText("");
        telefoneProdutor.setText("");
        telefone2Produtor.setText("");
        cnpjProdutor.setText("");
        razaoSocialProdutor.setText("");
        ProducaoPropriaCheckBox.setSelected(false);
        JOptionPane.showMessageDialog(this, "Produtor deletado com sucesso");
    }
}
