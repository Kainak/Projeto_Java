package application.FornedoresInterface;

import application.DocumentosInterface.Lista;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JButton fornecedoresButton;
    private JPanel mainPanel;
    private JButton usuariosButton;

    public MainFrame() {

        setContentPane(mainPanel);
        setTitle("Menu principal");
        setSize(450, 300);
setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

//        fornecedoresButton.addActionListener(e -> {
//            new FormularioFornecedores();
//
//            this.dispose();
//
//        });

        fornecedoresButton.addActionListener(e -> {
            new Lista();

            this.dispose();

        });

        mainPanel.setVisible(true);

    }

}
