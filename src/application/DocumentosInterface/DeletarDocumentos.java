package application.DocumentosInterface;

import db.DB;
import model.dao.impl.DocumentoDaoJDBC;
import model.dao.impl.DocumentoDao;
import model.entities.Documento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletarDocumentos extends JFrame{

    private JPanel deletar;
    private JTextField idDocumento;
    private JButton buscarButton;
    private JButton excluirButton;
    private JButton voltarButton;
    private JTextField ID;
    private JTextField titulo;
    private DocumentoDao documentoDAO;

    public DeletarDocumentos(int idSelecionado){
        setTitle("Documentos");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setIdDocumentoValue(idSelecionado);
        documentoDAO = new DocumentoDaoJDBC(DB.getConnection());
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              excluirDocumento();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListaDocumentos();
            }
        });

        setVisible(true);
        setContentPane(deletar);
    }

    private void buscar(){
        int idSelecionado = Integer.parseInt(idDocumento.getText());
        Documento documento = documentoDAO.findById(idSelecionado);
        if (documento != null) {
            ID.setText(String.valueOf(documento.getIDdocumentos()));
            titulo.setText(documento.getTitulo());
        } else {
            JOptionPane.showMessageDialog(this, "Documento não encontrado");
        }
    }

    private void excluirDocumento() {
        int id = Integer.parseInt(idDocumento.getText());
        documentoDAO.deleteById(id);
        JOptionPane.showMessageDialog(this, "Documento deletado com sucesso");
    }

    public void setIdDocumentoValue(int idSelecionado) {
        idDocumento.setText(String.valueOf(idSelecionado));
    }

}
