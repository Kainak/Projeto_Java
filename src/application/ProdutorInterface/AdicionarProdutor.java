package application.ProdutorInterface;

import model.dao.impl.ProdutorDao;
import model.dao.impl.ProdutorDaoJDBC;
import model.entities.Produtor;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarProdutor extends JFrame {
    private JTextField nomeProdutor;
    private JTextField cpfProdutor;
    private JTextField emailProdutor;
    private JTextField telefoneProdutor;
    private JTextField telefone2Produtor;
    private JTextField cnpjProdutor;
    private JTextField razaoSocialProdutor;
    private JCheckBox producaoPropriaCheckBox;
    private JButton voltarButton;
    private JButton salvarButton;
    private JPanel adicionarProdutor;

    private ProdutorDao produtorDAO;

    public AdicionarProdutor() {
        setTitle("Adicionar Produtor");
        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(adicionarProdutor);
        setVisible(true);
        setLocationRelativeTo(null);
        produtorDAO = new ProdutorDaoJDBC(DB.getConnection());

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProdutor();
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaProdutores();
                dispose();
            }
        });
    }

    private void salvarProdutor() {
        String nome = nomeProdutor.getText();
        String cpf = cpfProdutor.getText();
        String email = emailProdutor.getText();
        String telefone = telefoneProdutor.getText();
        String telefone2 = telefone2Produtor.getText();
        String cnpj = cnpjProdutor.getText();
        String razaoSocial = razaoSocialProdutor.getText();
        boolean producaoPropria = producaoPropriaCheckBox.isSelected();

        Produtor produtor = new Produtor();
        produtor.setNome(nome);
        produtor.setCpf(cpf);
        produtor.setEmail(email);
        produtor.setTelefone(telefone);
        produtor.setTelefone2(telefone2);
        produtor.setCnpj(cnpj);
        produtor.setRazaoSocial(razaoSocial);
        produtor.setProducaoPropria(producaoPropria);

        produtorDAO.insert(produtor);
        JOptionPane.showMessageDialog(this, "Produtor cadastrado com sucesso");
    }
}
