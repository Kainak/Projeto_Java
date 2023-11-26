package application.DocumentosInterface;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import db.DB;
import model.dao.impl.DocumentoDao;
import model.dao.impl.DocumentoDaoJDBC;
import model.entities.Documento;
import model.dao.impl.ProdutorDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import application.FornedoresInterface.MainFrame;

public class Lista extends JFrame {
    private JPanel documentos;
    private JLabel Titulo;
    private JButton voltar;
    private JButton adicionarButton;
    private JButton deletarButton1;
    private JTable tabelaDocumentos;
    private JTextField idProdutor;
    private JButton procurarProdutor;
    private JButton downloadButton;
    private DocumentoDao documentoDAO;
    private Integer idDocumento;

    private ProdutorDao produtorDao;

    public Lista() {

        setTitle("Documentos");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        documentoDAO = new DocumentoDaoJDBC(DB.getConnection());

        voltar.addActionListener(e -> {
            new MainFrame();
            this.dispose();
        });

        adicionarButton.addActionListener(e -> {
            new Adicionar();
            this.dispose();
        });

        downloadButton.addActionListener(e -> {
            if(idDocumento != null) {
                download();
            }else{
                JOptionPane.showMessageDialog(this, "Selecione um documento para baixar");
            }
        });

        deletarButton1.addActionListener(e ->{
            if(idDocumento != null) {
                UIManager.put("OptionPane.yesButtonText", "Sim");
                UIManager.put("OptionPane.noButtonText", "Não");
                int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja apagar o documento do banco de dados?", "Essa ação né irreversível", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                        excluirDocumento();
                }
            }else{
                JOptionPane.showMessageDialog(this, "Selecione um documento para deletar!");
            }
        });

        procurarProdutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            buscarPorId();
            }

        });

        setVisible(true);
        setContentPane(documentos);
    }


    private void buscarPorId() {
        String idText = idProdutor.getText();
        if (idText.isEmpty()) { // VERIFICA SE O CAMPO ESTA VAZIO
            JOptionPane.showMessageDialog(this, "Informe o Id do Produtor!");
        } else {
            try {
                //CHAMA A TABELA
                Integer id = Integer.parseInt(idText);
                System.out.println(id);
                createTable(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Id do Produtor inválido!");
            }
        }
    }



    private void createTable(int id) {
        List<Documento> documentos = documentoDAO.findByProdutorId(id);
        if (documentos != null) {
            Object[][] data = new Object[documentos.size()][4];
            for (int i = 0; i < documentos.size(); i++) {
                data[i][0] = documentos.get(i).getIDdocumentos();
                data[i][1] = documentos.get(i).getTitulo();
                data[i][2] = documentos.get(i).getData();
                data[i][3] = documentos.get(i).getData_venc();
            }
            tabelaDocumentos.setModel(new DefaultTableModel(
                    data,
                    new String[]{"ID", "Titulo", "Data", "Vencimento"}
            ));

            //PEGA O ID DO DOCUMENTO SELECIONADO PELO USUÁRIO
            tabelaDocumentos.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabelaDocumentos.getSelectedRow();
                    if (selectedRow != -1) {
                        idDocumento = (Integer) tabelaDocumentos.getValueAt(selectedRow, 0);
                    }
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Documento não encontrado");
        }
    }

    private void excluirDocumento(){
            documentoDAO.deleteById(this.idDocumento);
            JOptionPane.showMessageDialog(this, "Documento deletado com sucesso");
    }

    private void download(){
        documentoDAO.recuperar(idDocumento);
        JOptionPane.showMessageDialog(this, "Documento salvo na Área de Trabalho");
    }

}
