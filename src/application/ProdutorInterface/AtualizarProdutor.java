package application.ProdutorInterface;

import db.DB;
import model.dao.impl.ProdutorDao;
import model.dao.impl.ProdutorDaoJDBC;
import model.entities.Produtor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarProdutor extends JFrame {
        private JTextField idPesquisarProdutor;
        private JButton buscarButton;
        private JTextField novoNomeProdutor;
        private JTextField novoCpfProdutor;
        private JTextField novoEmailProdutor;
        private JTextField novoTelefoneProdutor;
        private JTextField novoTelefone2Produtor;
        private JTextField novoCnpjProdutor;
        private JTextField novoRazaoSocialProdutor;
        private JCheckBox novaProducaoPropriaCheckBox;
        private JButton atualizarButton;
        private JButton voltarButton;
        private JPanel atualizarProdutor;

    private ProdutorDao produtorDAO;

        public AtualizarProdutor() {
            setTitle("Atualizar Produtor");
            setSize(700, 500);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setContentPane(atualizarProdutor);
            setLocationRelativeTo(null);
            setVisible(true);

            produtorDAO = new ProdutorDaoJDBC(DB.getConnection());

            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { buscarProdutor(); }
            });

            atualizarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { atualizarProdutor(); }
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
            novoNomeProdutor.setText(produtor.getNome());
            novoCpfProdutor.setText(produtor.getCpf());
            novoEmailProdutor.setText(produtor.getEmail());
            novoTelefoneProdutor.setText(produtor.getTelefone());
            novoTelefone2Produtor.setText(produtor.getTelefone2());
            novoCnpjProdutor.setText(produtor.getCnpj());
            novoRazaoSocialProdutor.setText(produtor.getRazaoSocial());
            novaProducaoPropriaCheckBox.setSelected(produtor.isProducaoPropria());

            novoNomeProdutor.setEnabled(true);
            novoCpfProdutor.setEnabled(true);
            novoEmailProdutor.setEnabled(true);
            novoTelefoneProdutor.setEnabled(true);
            novoTelefone2Produtor.setEnabled(true);
            novoCnpjProdutor.setEnabled(true);
            novoRazaoSocialProdutor.setEnabled(true);
            novaProducaoPropriaCheckBox.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Produtor não encontrado");
        }
    }
    private void atualizarProdutor() {
        int id = Integer.parseInt(idPesquisarProdutor.getText());
        String nome = novoNomeProdutor.getText();
        String cpf = novoCpfProdutor.getText();
        String email = novoEmailProdutor.getText();
        String telefone = novoTelefoneProdutor.getText();
        String telefone2 = novoTelefone2Produtor.getText();
        String cnpj = novoCnpjProdutor.getText();
        String razaoSocial = novoRazaoSocialProdutor.getText();
        boolean producaoPropria = novaProducaoPropriaCheckBox.isSelected();

        Produtor produtor = new Produtor();
        produtor.setIDprodutor(id);
        produtor.setNome(nome);
        produtor.setCpf(cpf);
        produtor.setEmail(email);
        produtor.setTelefone(telefone);
        produtor.setTelefone2(telefone2);
        produtor.setCnpj(cnpj);
        produtor.setRazaoSocial(razaoSocial);
        produtor.setProducaoPropria(producaoPropria);

        produtorDAO.update(produtor);
    }
}
