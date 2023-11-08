package model.dao.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.entities.Documento;

public class DocumentoDaoJDBC implements DocumentoDao {

    private Connection conn;

    public DocumentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Documento obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO documento " +
                            "(titulo, data, data_venc, documento, IDprodutor) " + // Inclua todas as colunas aqui
                            "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getTitulo());
            st.setDate(2, new Date(obj.getData().getTime())); // Substitua pelo tipo de dados correto, se necessário
            st.setDate(3, new Date(obj.getData_venc().getTime())); // Substitua pelo tipo de dados correto, se necessário
            st.setBlob(4, obj.getDocumento()); // Substitua pelo tipo de dados correto, se necessário
            st.setInt(5, obj.getProdutor().getIDprodutor());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setIDdocumentos(id);
                }
            }
            else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void Recuperar() {

        PreparedStatement st = null;
        ResultSet rs = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDIGITE O ID DE UM DOCUMENTO QUE DESEJA RECUPERAR:");
        int Recuperar = sc.nextInt();

        try {

            st = conn.prepareStatement("SELECT documento FROM documento WHERE IDdocumento = ?");
            st.setInt(1, Recuperar);
            rs = st.executeQuery();


            if (rs.next()) {

                 //Recupere o BLOB da coluna "conteudo_blob"

                InputStream blobStream = rs.getBinaryStream("documento");
                File file = new File(String.valueOf(blobStream));
                //long fileSize = file.length();

                // Crie um arquivo para salvar o BLOB
                String nomeDoArquivo = "arquivo_recuperado"; // Nome do arquivo de destino

                try (FileOutputStream outputStream = new FileOutputStream(nomeDoArquivo)) {
                    byte[] buffer = new byte[1024];
                    while (blobStream.read(buffer) != -1) {
                        outputStream.write(buffer);
                            }
                } catch (IOException e) {
                  //  e.printStackTrace();
                }

                System.out.println("Arquivo BLOB recuperado com sucesso.");
            } else {
                System.out.println("Nenhum registro encontrado com o ID especificado.");

            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }



//        String jdbcURL = "jdbc:mysql://localhost:3306/coursejdbc";
//        String username = "root";
//        String password = "rootroot";
//        st = conn.prepareStatement(
//                "SELECT * FROM documento WHERE IDdocomento");
//        rs = st.executeQuery();
//
//        List<Produtor> list = new ArrayList<>();
//


//        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
//            String sql = "SELECT documento FROM documento WHERE IDdocumento = ?";
//
//            try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                statement.setInt(1, Recuperar);
//
//                try (ResultSet result = statement.executeQuery()) {


//                    if (result.next()) {
//                        // Recupere o BLOB da coluna "conteudo_blob"
//                        InputStream blobStream = result.getBinaryStream("documento");
//
//                        File file = new File(String.valueOf(blobStream));
//                        long fileSize = file.length();
//
//
//                        // Crie um arquivo para salvar o BLOB
//                        String nomeDoArquivo = "arquivo_recuperado"; // Nome do arquivo de destino
//
//                        try (FileOutputStream outputStream = new FileOutputStream(nomeDoArquivo)) {
//
//                            byte[] buffer = new byte[1024];
//                            while (blobStream.read(buffer) != -1) {
//                                outputStream.write(buffer);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//


//                        System.out.println("Arquivo BLOB recuperado com sucesso.");
//                    } else {
//                        System.out.println("Nenhum registro encontrado com o ID especificado.");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void deleteById() {

        Scanner sc = new Scanner(System.in);

        PreparedStatement st = null;

        System.out.println("DIGITE O ID DO DOCUMENTO QUE DESEJA EXCLUIR:");

        int Excluir = sc.nextInt();

        try {
            st = conn.prepareStatement(
                    "DELETE FROM documento WHERE IDdocumento = ?");
            st.setInt(1, Excluir);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

}














//package model.dao.impl;
//import java.sql.*;
//
//import db.DB;
//import db.DbException;
//import model.entities.Documento;
//
//public class DocumentoDaoJDBC implements DocumentoDao {
//
//    private Connection conn;
//
//    public DocumentoDaoJDBC(Connection conn) {
//        this.conn = conn;
//    }
//
//    @Override
//    public void insert(Documento obj) {
//        PreparedStatement st = null;
//        try {
//            st = conn.prepareStatement(
//                    "INSERT INTO documento " +
//                            "(titulo) " +
//                            "(data) " +
//                            "(data_venc) " +
//                            "(documento) " +
//                            "(produtor) " +
//                            "VALUES " +
//                            "(?)",
//                    Statement.RETURN_GENERATED_KEYS);
//
//            st.setString(1, obj.getTitulo());
//
//            int rowsAffected = st.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet rs = st.getGeneratedKeys();
//                if (rs.next()) {
//                    int id = rs.getInt(1);
//                    obj.setIDdocumentos(id);
//                }
//            }
//            else {
//                throw new DbException("Unexpected error! No rows affected!");
//            }
//        }
//        catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        }
//        finally {
//            DB.closeStatement(st);
//        }
//    }
//
//}
