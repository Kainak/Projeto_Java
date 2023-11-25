package application.FornedoresInterface;

import application.DocumentosInterface.ListaDocumentos;
import application.ProdutorInterface.ListaProdutores;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JButton fornecedoresButton;
    private JButton produtoresButton;
    private JPanel mainPanel;
    private JButton documentosButton;
    private JButton usuariosButton;

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Menu principal");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        fornecedoresButton.addActionListener(e -> {
            new ListaFornecedores();
            this.dispose();
        });

        produtoresButton.addActionListener(e -> {
            new ListaProdutores();
            this.dispose();
        });

        documentosButton.addActionListener(e -> {
            new ListaDocumentos();
            this.dispose();
        });


        mainPanel.setVisible(true);
    }
}

