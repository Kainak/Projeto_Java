package model.dao.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DocumentoDao;
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
    public void Recuperar(int recuperar) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement("SELECT documento FROM documento WHERE IDdocumento = ?");
            st.setInt(1, recuperar);
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

    }

    @Override
    public void deleteById(int excluir) {

        Scanner sc = new Scanner(System.in);

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM documento WHERE IDdocumento = ?");
            st.setInt(1, excluir);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    private Documento instantiateDocumento(ResultSet rs) throws SQLException {
        Documento dec = new Documento();
        dec.setIDdocumentos(rs.getInt("IDdocumento"));
        dec.setTitulo(rs.getString("Titulo"));
        return dec;
    }

    @Override
    public List<Documento> findByProdutorId(int produtorId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Documento> documentos = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM documento WHERE IDprodutor = ?");
            st.setInt(1, produtorId);
            rs = st.executeQuery();

            while (rs.next()) {
                Documento documento = instantiateDocumento(rs);
                documentos.add(documento);
            }

            return documentos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}




