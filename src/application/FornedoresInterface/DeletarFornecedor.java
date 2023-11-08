package application.FornedoresInterface;

import javax.swing.*;

public class DeletarFornecedor extends JFrame {
    private JTextField idPesquisarFornecedor;
    private JTextField NomeFornecedortextField2;
    private JTextField TelefoneFornecedor;
    private JButton excluirButton;
    private JButton voltarButton;
    private JButton buscarButton;
    private JPanel deletarFornecedor;
public  DeletarFornecedor() {
    setTitle("Atualizar Fornecedor");
    setSize(350, 350);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setContentPane(deletarFornecedor);
    setVisible(true);
}
}
