
package application;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.DaoFactory;
import model.dao.DocumentoDao;
import model.entities.Documento;
import model.entities.Produtor;


public class Documento_interface {
    public static void main(String[] args) {

        Documento documento = new Documento();

        Scanner sc = new Scanner(System.in);

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

        Date dataVencimento = null;
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");

        while (dataVencimento == null) {
            System.out.print("Digite a data de vencimento (no formato yyyy-MM-dd): ");
            String dataVencimentoStr = sc.nextLine();

            try {
                dataVencimento = formatoData.parse(dataVencimentoStr);
            } catch (ParseException e) {
                System.err.println("Data inválida. Tente novamente.");
            }
        }
        System.out.println("Data de vencimento: " + formatoData.format(dataVencimento));
        //AQUI É ONDE É COLOCADO O DIRETÓRIO DO ARQUIVO
        System.out.print("Digite o caminho do arquivo a ser inserido: ");
        String filePath = sc.nextLine();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("O arquivo não existe.");
                return;
            }
            // Lê todos os bytes do arquivo
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            documento.setTitulo(titulo);
            documento.setData(dataAtual);
            documento.setData_venc(dataVencimento);
            // Define os bytes do documento diretamente
            documento.setDocumento(new ByteArrayInputStream(fileBytes));
            documento.setProdutor(IDprodutor);
            // INSERE O DOCUMENTO NO BANCO
            documentoDao.insert(documento);
            System.out.println("Documento inserido!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }




        System.out.println("\nDIGITE O ID DE UM DOCUMENTO QUE DESEJA RECUPERAR:");
        int recuperar = sc.nextInt();
        documentoDao.recuperar(recuperar);

        System.out.println("DIGITE O ID DO DOCUMENTO QUE DESEJA EXCLUIR:");
        int excluir = sc.nextInt();
        documentoDao.deleteById(excluir);

        System.out.print("Digite o ID do produtor que deseja procurar: ");
        int produtorId = sc.nextInt();
        Documento documentoDAO = new Documento(); // Substitua DocumentoDAO pelo nome da classe que contém o método findByProdutorId.

        List<Documento> documentos = documentoDao.findByProdutorId(produtorId);
        if (documentos.isEmpty()) {
            System.out.println("Nenhum documento encontrado para o produtor com o ID " + produtorId);
        } else {
            System.out.println("Documentos encontrados para o produtor com o ID " + produtorId + ":");
            for (Documento documentoimp : documentos) {
                System.out.println("ID do Documento: " + documentoimp.getIDdocumentos());
                System.out.println("Título: " + documentoimp.getTitulo());
                // Outros atributos do documento
                System.out.println();
            }
        }


    }
}

