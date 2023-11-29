package application.CategoriaFornecedores;

import model.dao.impl.CategoriaFornecedoresDao;
import model.dao.impl.CategoriaFornecedoresDaoJDBC;
import model.entities.CategoriaFornecedores;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarCategoria extends JFrame {
    private JTextField idPesquisarCategoria;
    private JButton buscarButton;
    private JTextField novoNomeCategoria;
    private JButton voltarButton;
    private JButton atualizarButton;
    private JPanel atualizarCategoria;

    private CategoriaFornecedoresDao categoriaFornecedoresDao;

    public AtualizarCategoria() {
        setTitle("Atualizar Categoria");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(atualizarCategoria);
        setVisible(true);
        setLocationRelativeTo(null);
        categoriaFornecedoresDao = new CategoriaFornecedoresDaoJDBC(DB.getConnection());

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCategoria();
            }
        });
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCategoria();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaCategoria();
                dispose();
            }
        });
    }

    private void buscarCategoria() {
        Integer id = Integer.parseInt(idPesquisarCategoria.getText());
        CategoriaFornecedores categoria = categoriaFornecedoresDao.findById(id);
        if (categoria != null) {
            novoNomeCategoria.setText(categoria.getNome());
        } else {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada");
        }
    }

    private void atualizarCategoria() {
        Integer id = Integer.parseInt(idPesquisarCategoria.getText());
        String nome = novoNomeCategoria.getText();

        CategoriaFornecedores categoria = new CategoriaFornecedores();
        categoria.setId(id);
        categoria.setNome(nome);

        categoriaFornecedoresDao.update(categoria);
    }
}
