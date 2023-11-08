package application.FornedoresInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormularioFornecedores extends JFrame {

    private JPanel formularioFornecedores;
    private JButton adicionarFornecedorButton;
    private JButton atualizarFornecedorButton;
    private JButton deletarFornecedorButton;
    private JButton voltarButton;
    private JTable tabelaFornecedores;
    private JScrollPane categoriasFornecedores;
    private JScrollBar scrollBar1;

    public FormularioFornecedores() {
        setTitle("Formulário de Fornecedores");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        voltarButton.addActionListener(e -> {
            new MainFrame();
            this.dispose();
        });

        adicionarFornecedorButton.addActionListener(e -> {
            new AdicionarFornecedor();
        });
        atualizarFornecedorButton.addActionListener(e -> {
            new AtualizarFornecedor();
        });
        deletarFornecedorButton.addActionListener(e -> {
            new DeletarFornecedor();
        });
        setContentPane(formularioFornecedores);

        setVisible(true);
        createTable();
    }

    private void createTable() {
        Object[][] data = {
                {"xixi", "45998039952"},
        };
        tabelaFornecedores.setModel(new DefaultTableModel(
                data,
                new String[]{"Nome", "Telefone"}
        ));
    }
}
