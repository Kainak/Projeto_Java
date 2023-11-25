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
    private DocumentoDao documentoDAO;

    private int idSelecionado;
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

        deletarButton1.addActionListener(e ->{
            new Deletar(idSelecionado);
            this.dispose();
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
        int id = Integer.parseInt(idProdutor.getText());
        createTable(id);
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

            tabelaDocumentos.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tabelaDocumentos.getSelectedRow();
                    if (selectedRow != -1) {
                        idSelecionado = (int) tabelaDocumentos.getValueAt(selectedRow, 0);
                        // Chame a função que você deseja com o ID selecionado
                    }
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Documento não encontrado");
        }
    }

}
