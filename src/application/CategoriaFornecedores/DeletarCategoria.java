package application.CategoriaFornecedores;

import model.dao.impl.CategoriaFornecedoresDao;
import model.dao.impl.CategoriaFornecedoresDaoJDBC;
import model.entities.CategoriaFornecedores;
import db.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarCategoria extends JFrame {
    private JTextField idPesquisarCategoria;
    private JButton buscarButton;
    private JTextField NomeCategoria;
    private JButton voltarButton;
    private JButton excluirButton;
    private JPanel deletarCategoria;

    private CategoriaFornecedoresDao categoriaFornecedoresDao;

    public DeletarCategoria() {
        setTitle("Deletar Categoria");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(deletarCategoria);
        setVisible(true);
        setLocationRelativeTo(null);
        categoriaFornecedoresDao = new CategoriaFornecedoresDaoJDBC(DB.getConnection());

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCategoria();
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarCategoria();
                new ListaCategoria();
                dispose();
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
            NomeCategoria.setText(categoria.getNome());
        } else {
            JOptionPane.showMessageDialog(null, "Categoria não encontrada");
        }
    }

    private void deletarCategoria() {
        Integer id = Integer.parseInt(idPesquisarCategoria.getText());
        categoriaFornecedoresDao.deleteById(id);
        NomeCategoria.setText("");
        JOptionPane.showMessageDialog(null, "Deletado com sucesso", "Deletado", JOptionPane.INFORMATION_MESSAGE);
    }
}
