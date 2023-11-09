package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.entities.CategoriaFornecedores;
import db.DB;
import db.DbException;
import model.entities.Fornecedor;

public class CategoriaFornecedoresDaoJDBC implements CategoriaFornecedoresDao{
    private Connection conn;
    public CategoriaFornecedoresDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(CategoriaFornecedores obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO CategoriaFornecedores (ForeignKey, NomeCategoria) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getForeignKey());
            st.setString(2, obj.getNomeCategoria());


            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int IdCategoriaFornecedores = rs.getInt(1);
                    obj.setIdCategoriaFornecedores(id);
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
                    "UPDATE fornecedores SET nome = ?, telefone = ? WHERE id = ?"
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getTelefone());
            st.setInt(3, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
    @Override
    public void deleteByIdCategoriaFornecedores(Integer IdCategoriaFornecedores) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM CategoriaFornecedores WHERE IdCategoriasFornecedores = ?");

            st.setInt(1, IdCategoriaFornecedores);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Fornecedor findByIdCategoriaFornecedores(Integer IdCategoriaFornecedores) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM CategoriaFornecedores WHERE IdCategoriaFornecedores = ?");
            st.setInt(1, IdCategoriaFornecedores);
            rs = st.executeQuery();
            if (rs.next()) {
                CategoriaFornecedores obj = instantiateCategoriaFornecedores(rs);
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

    private CategoriaFornecedores instantiateCategoriaFornecedores(ResultSet rs) throws SQLException {
        CategoriaFornecedores obj = new CategoriaFornecedores();
        obj.setIdCategoriaFornecedores(rs.getInt("IdCategoriaFornecedores"));
        obj.setForeignKey(rs.getString("ForeignKey"));
        obj.setNomeCategoria(rs.getString("NomeCategoria"));
        return obj;
    }
    @Override
    public List<CategoriaFornecedores> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM fornecedores");
            rs = st.executeQuery();

            List<Fornecedor> list = new ArrayList<>();

            while (rs.next()) {
                Fornecedor obj = instantiateCategoriaFornecedores(rs);
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
}
