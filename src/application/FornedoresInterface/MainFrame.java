package application.FornedoresInterface;

import javax.swing.*;


public class MainFrame extends JFrame {
    private JButton fornecedoresButton;
    private JPanel mainPanel;

    public MainFrame() {

        setContentPane(mainPanel);
        setTitle("Menu principal");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        fornecedoresButton.addActionListener(e -> {
            new FormularioFornecedores();
            this.dispose();

        });

        mainPanel.setVisible(true);

    }

}
