package application.CategoriaFornecedores;

import application.FornedoresInterface.ListaFornecedores;
import model.dao.impl.CategoriaFornecedoresDao;
import model.dao.impl.CategoriaFornecedoresDaoJDBC;
import model.entities.CategoriaFornecedores;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarCategoria extends JFrame {
    private JTextField nomeCategoria;
    private JButton voltarButton;
    private JButton salvarButton;
    private JPanel adicionarCategoria;

    private CategoriaFornecedoresDao CategoriaFornecedoresDao;

    public AdicionarCategoria() {
        setTitle("Adicionar Categoria");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(adicionarCategoria);
        setVisible(true);
        setLocationRelativeTo(null);
        CategoriaFornecedoresDao = new CategoriaFornecedoresDaoJDBC(DB.getConnection());

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCategoria();
                new ListaCategoria();
                dispose();
            }

        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaCategoria();
            }
        });
    }

    private void salvarCategoria() {
        String nome = nomeCategoria.getText();

        CategoriaFornecedores categoriafornecedores = new CategoriaFornecedores();
        categoriafornecedores.setNome(nome);

        CategoriaFornecedoresDao.insert(categoriafornecedores);
        JOptionPane.showMessageDialog(null, "Salvo com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

    }
}
