package application.FornedoresInterface;

import javax.swing.*;

public class AtualizarFornecedor extends JFrame {
    private JTextField idPesquisarFornecedor;
    private JButton buscarButton;
    private JTextField novoNomeFornecedortextField2;
    private JTextField novoTelefoneFornecedor;
    private JButton atualizarButton;
    private JButton voltarButton;
    private JPanel atualizarFornecedor;

    public AtualizarFornecedor() {

        setTitle("Atualizar Fornecedor");
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(atualizarFornecedor);
        setVisible(true);
    }
}
