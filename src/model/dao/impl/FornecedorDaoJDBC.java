package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.entities.Fornecedor;
import db.DB;
import db.DbException;

public class FornecedorDaoJDBC implements FornecedorDao {

    private Connection conn;

    public FornecedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Fornecedor obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO fornecedores (nome, telefone, categoriaid) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setInt(3, obj.getCategoriaid());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Fornecedor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE fornecedores SET nome = ?, telefone = ?, categoriaid = ? WHERE id = ?"
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setInt(3, obj.getCategoriaid());
            st.setInt(4, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM fornecedores WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Fornecedor findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM fornecedores WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Fornecedor obj = instantiateFornecedor(rs);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Fornecedor> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM fornecedores");
            rs = st.executeQuery();

            List<Fornecedor> list = new ArrayList<>();

            while (rs.next()) {
                Fornecedor obj = instantiateFornecedor(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Fornecedor> findByCategoria(String categoria) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT f.* FROM fornecedores f " +
                            "INNER JOIN categoriafornecedores c ON f.categoriaid = c.categoriaid " +
                            "WHERE c.nomecategoria = ?"
            );
            st.setString(1, categoria);
            rs = st.executeQuery();

            List<Fornecedor> list = new ArrayList<>();

            while (rs.next()) {
                Fornecedor obj = instantiateFornecedor(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }


    private Fornecedor instantiateFornecedor(ResultSet rs) throws SQLException {
        Fornecedor obj = new Fornecedor();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setTelefone(rs.getString("telefone"));
        obj.setCategoriaid(rs.getInt("categoriaid"));
        return obj;
    }
}
