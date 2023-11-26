package application.DocumentosInterface;

import db.DB;
import model.dao.impl.DocumentoDao;
import model.dao.impl.DocumentoDaoJDBC;
import model.entities.Documento;
import model.entities.Produtor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdicionarDocumentos extends JFrame{
    private JButton procurarArquivoButton;
    private JTextField dataVencimento;
    private JButton adicionarArquivoButton;
    private JButton voltarButton;
    private JPanel adicionar;
    private JTextField imprimirCaminho;
    private JTextField idInformado;
    private DocumentoDao documentoDao;
    private String caminho;
    private String data_venc;


    public AdicionarDocumentos() {
        setTitle("Documentos");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        documentoDao = new DocumentoDaoJDBC(DB.getConnection());
        procurarArquivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); //INSTANCIA UM FILE CHOOSER
                fileChooser.setCurrentDirectory(new File("."));//SELECIONA O ARQUIVO

                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {

                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());                     System.out.println(caminho);
                    caminho = String.valueOf(file); //SALVA NA VARIAVEL STRING O CAMINHO
                    System.out.println(caminho);
                    imprimirCaminho.setText(caminho);
                }
            }
        });

        adicionarArquivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {adicionar();}
        });

        setVisible(true);
        setContentPane(adicionar);

        voltarButton.addActionListener(e -> {
            new ListaDocumentos();
            this.dispose();
        });

    }

    private void adicionar(){

        Date dataAtual = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date data_venc_date = null;
        Produtor idProdutor = new Produtor();


        //VERIFICA O ID
        try {
            int id = Integer.parseInt(idInformado.getText());
            idProdutor.setIDprodutor(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um ID válido!");
            return; // Retorna se o ID não for um número válido
        }

        //VERIFICA A DATA
        data_venc = dataVencimento.getText();
        try {
            formatoData.setLenient(false); // Define o SimpleDateFormat como estrito
            formatoData.parse(data_venc); // Tenta fazer o parse da data
            data_venc_date = formatoData.parse(data_venc);
        } catch (Exception ex) {
            //throw new RuntimeException(ex);
            JOptionPane.showMessageDialog(this,"Data Inválida!");
        }

        //VERIFICA O CAMINHO
        if (caminho == null || caminho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um arquivo!");
            return; // Retorna se o caminho do arquivo estiver vazio ou nulo
        }

        Documento documento = new Documento();

        //VERIFICA A INSERÇÃO NO BANCO
        try {
            File file = new File(caminho);

            if (!file.exists()) {
                System.out.println("O arquivo não existe.");
                return;
            }

            // LÊ TODOS OS BYTES DO ARQUIVO
            byte[] fileBytes = Files.readAllBytes(Paths.get(caminho));
            String titulo = file.getName();
            documento.setTitulo(titulo);
            System.out.println(titulo);
            documento.setData(dataAtual);
            System.out.println(dataAtual);
            documento.setData_venc(data_venc_date);
            System.out.println(data_venc_date);

            // Define os bytes do documento diretamente
            documento.setDocumento(new ByteArrayInputStream(fileBytes));

            System.out.println(caminho);
            documento.setProdutor(idProdutor);
            System.out.println(documento);

            // INSERE O DOCUMENTO NO BANCO

            documentoDao.insert(documento);

            System.out.println("Documento inserido!");
            JOptionPane.showMessageDialog(this, "Adicionado com sucesso!");

        } catch (Exception ex) {
            System.out.println("Erro ao carregar o arquivo: " + ex.getMessage());
            JOptionPane.showMessageDialog(this,"Erro ao adicionar o arquivo!");
        }
    }
}
