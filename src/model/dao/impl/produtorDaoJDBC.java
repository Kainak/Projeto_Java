package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.produtorDao;
import db.DbIntegrityException;
import model.entities.Produtor;

public class produtorDaoJDBC implements produtorDao {

    private Connection conn;

    public produtorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produtor obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO produtor "
                            + "(IDprodutor, nome, cpf, email, telefone, telefone2, cnpj, razaosocial, producao_propria) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getIDprodutor());
            st.setString(2, obj.getNome());
            st.setString(3, obj.getCpf());
            st.setString(4, obj.getEmail());
            st.setString(5, obj.getTelefone());
            st.setString(6, obj.getTelefone2());
            st.setString(7, obj.getCnpj());
            st.setString(8, obj.getRazaoSocial());
            st.setBoolean(9, obj.isProducaoPropria());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setIDprodutor(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error! No rows affected!");
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
    public Produtor findById(Integer IDprodutor) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM produtor WHERE IDprodutor = ?");
            st.setInt(1, IDprodutor);
            rs = st.executeQuery();
            if (rs.next()) {
                Produtor obj;
                obj = new Produtor();
                obj.setIDprodutor(rs.getInt("IDprodutor"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setTelefone2(rs.getString("telefone2"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setRazaoSocial(rs.getString("razaosocial"));
                obj.setProducaoPropria(rs.getBoolean("producao_propria"));
                return obj;
            }
            return null;
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
    public List<Produtor> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM produtor ORDER BY Nome");
            rs = st.executeQuery();

            List<Produtor> list = new ArrayList<>();

            while (rs.next()) {
                Produtor obj = new Produtor();
                obj.setIDprodutor(rs.getInt("IDprodutor"));
                obj.setNome(rs.getString("Nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setTelefone2(rs.getString("telefone2"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setRazaoSocial(rs.getString("razaosocial"));
                obj.setProducaoPropria(rs.getBoolean("producao_propria"));
                list.add(obj);
            }
            return list;
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
    public void deleteById(Integer IDprodutor) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(

                    "DELETE FROM produtor WHERE IDprodutor = ?");

            st.setInt(1, IDprodutor);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void update(Produtor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE produtor " +
                            "SET Nome = ?, " +
                            "CPF = ?, " +
                            "Email = ?, " +
                            "Telefone = ?, " +
                            "Telefone2 = ?, " +
                            "CNPJ = ?, " +
                            "RazaoSocial = ?, " +
                            "Producao_Propria = ? " +
                            "WHERE IDprodutor = ?");
            st.setString(1, obj.getNome());
            st.setString(2, obj.getCpf());
            st.setString(3, obj.getEmail());
            st.setString(4, obj.getTelefone());
            st.setString(5, obj.getTelefone2());
            st.setString(6, obj.getCnpj());
            st.setString(7, obj.getRazaoSocial());
            st.setBoolean(8, obj.isProducaoPropria());
            st.setInt(9, obj.getIDprodutor());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }



}
