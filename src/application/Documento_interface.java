
package application;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.DaoFactory;
import model.dao.impl.DocumentoDao;
import model.entities.Department;
import model.entities.Documento;
import model.entities.Produtor;


public class Documento_interface {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Documento documento = new Documento();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DocumentoDao documentoDao = DaoFactory.createDocumentoDao();

        System.out.println("\nCADASTRO DE DOCUMENTO");

        System.out.println("Em qual produtor deseja inserir um arquivo?");
        Integer escolha = Integer.valueOf(sc.nextLine());
        Produtor IDprodutor = new Produtor(escolha, null);

        System.out.print("Digite o título: ");
        String titulo = sc.nextLine();

        Date dataAtual = new Date();
        System.out.println("Data atual: " + dateFormat.format(dataAtual));

        Date dataVencimento = new Date();
        System.out.print("Digite a data de vencimento (no formato yyyy-MM-dd): ");
        String dataVencimentoStr = sc.nextLine();

        try{
            // Parse da data de vencimento, como mencionado anteriormente
        } catch (Exception e) {
            System.out.println("Data de vencimento inválida. Certifique-se de usar o formato yyyy-MM-dd.");
            return;
        }


        //AQUI É ONDE É COLOCADO O DIRETÓRIO DO ARQUIVO
        System.out.print("Digite o caminho do arquivo a ser inserido: ");
        String filePath = sc.nextLine();


        //TUDO ISSO É SÓ PRA ADICIONAR UM ARQUIVO
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("O arquivo não existe.");
                return;
            }
            long fileSize = file.length();
            // Create a BufferedInputStream to read the file
            BufferedInputStream documentoStream = new BufferedInputStream(new FileInputStream(filePath));
            // Create a Documento object
            documento.setTitulo(titulo);
            documento.setData(dataAtual);
            documento.setData_venc(dataVencimento);
            // Define a buffer for reading chunks of data
            byte[] buffer = new byte[(int) fileSize]; // Adjust the buffer size as needed
            int bytesRead;
            // Loop to read and write data in chunks
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((bytesRead = documentoStream.read(buffer)) != -1) {
                // Write the bytesRead portion of data to the ByteArrayOutputStream
                outputStream.write(buffer, 0, bytesRead);
            }
            // Set the document content from the ByteArrayOutputStream
            documento.setDocumento(new ByteArrayInputStream(outputStream.toByteArray()));
            documento.setProdutor(IDprodutor);

            // Insert the document into the database
            documentoDao.insert(documento);

            System.out.println("Documento inserido!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }


        documentoDao.Recuperar();
        documentoDao.deleteById();


    }
}

