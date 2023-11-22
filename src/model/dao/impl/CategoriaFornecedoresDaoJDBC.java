package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.entities.CategoriaFornecedores;
import db.DB;
import db.DbException;
import model.entities.Fornecedor;

public class CategoriaFornecedoresDaoJDBC implements CategoriaFornecedoresDao {

    private Connection conn;

    public CategoriaFornecedoresDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(CategoriaFornecedores obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO categoriafornecedore (nomecategoria) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString( obj.getNomecategoria());

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

        @Override
        public void update(CategoriaFornecedores obj) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                        "UPDATE categoriafornecedores SET nomecategoria = ?, WHERE categoriaid = ?"
                );

                st.setString(1, obj.getNomecategoria());
                st.setInt(2, obj.getCategoriaid());

                st.executeUpdate();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
            }
        }

        @Override
        public void deleteById(Integer categoriaid) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement("DELETE FROM categoriafornecedores WHERE categoriaid = ?");

                st.setInt(1, categoriaid);

                st.executeUpdate();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
            }
        }


        @Override
        public CategoriaFornecedores findByCategoriaid(Integer categoriaid) {
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement("SELECT * FROM categoriafornecedores WHERE categoriaid = ?");
                st.setInt(1, categoriaid);
                rs = st.executeQuery();
                if (rs.next()) {
                    Fornecedor obj = instantiateCategoriaFornecedores(rs);
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

        private Fornecedor instantiateCategoriaFornecedores(ResultSet rs) throws SQLException {
            Fornecedor obj = new CategoriaFornecedores();
            obj.setId(rs.getInt("categoriaid"));
            obj.setNome(rs.getString("nomecategoria"));
            return obj;
        }

        @Override
        public List<CategoriaFornecedores> findAll() {
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement("SELECT * FROM categoriafornecedores");
                rs = st.executeQuery();

                List<CategoriaFornecedores> list = new ArrayList<>();

                while (rs.next()) {
                    CategoriaFornecedores obj = instantiateCategoriaFornecedores(rs);
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

