package model.dao.impl;

import db.DB;
import db.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.entities.CategoriaFornecedores;

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
                    "INSERT INTO CategoriaFornecedores(IdCategoriaFornecedores, nomeCategoria) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getNomeCategoria());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
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
        public void update(CategoriaFornecedores obj) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                        "UPDATE CategoriaFornecedores SET nomeCategoria = ? WHERE IdCategoriaFornecedores = ?"
                );


            st.setString(1, obj.getNomeCategoria());
            st.setInt(2, obj.getIdCategoriaFornecedores());

            st.executeUpdate();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
            finally {
                DB.closeStatement(st);
            }
        }

        @Override
        public void deleteById(Integer id) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement("DELETE FROM CategoriaFornecedores WHERE IdCategoriaFornecedores = ?");

                st.setInt(1, id);

                st.executeUpdate();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
            finally {
                DB.closeStatement(st);
            }
        }

        @Override
        public CategoriaFornecedores findById(Integer id) {
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement("SELECT * FROM CategoriaFornecedores WHERE IdCategoriaFornecedores = ?");
                st.setInt(1, id);
                rs = st.executeQuery();
                if (rs.next()) {
                    CategoriaFornecedores obj = new CategoriaFornecedores();
                    obj.setIdCategoriaFornecedores(rs.getInt("IdCategoriaFornecedores"));
                    obj.setNomeCategoria(rs.getString("nomeCategoria"));
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
        public List<CategoriaFornecedores> findAll() {
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement("SELECT * FROM CategoriaFornecedores ORDER BY IdCategoriaFornecedores");
                rs = st.executeQuery();

                List<CategoriaFornecedores> list = new ArrayList<>();

                while (rs.next()) {
                    CategoriaFornecedores obj = new CategoriaFornecedores();
                    obj.setIdCategoriaFornecedores(rs.getInt("IdCategoriaFornecedores"));
                    obj.setNomeCategoria(rs.getString("nomeCategoria"));
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