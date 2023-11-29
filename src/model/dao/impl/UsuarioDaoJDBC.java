package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.entities.Fornecedor;
import model.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao {
    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
        Usuario obj = new Usuario();
        obj.setId(rs.getInt("Id"));
        obj.setEmail(rs.getString("Email"));
        obj.setPassword(rs.getString("Password"));
        return obj;
    }

    @Override
    public List<Usuario> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM usuario");
            rs = st.executeQuery();

            List<Usuario> list = new ArrayList<>();

            while (rs.next()) {
                Usuario obj = instantiateUsuario(rs);
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

    public Usuario findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        Usuario var5;
        try {
            st = this.conn.prepareStatement("SELECT * FROM usuario WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            Usuario obj;
            if (!rs.next()) {
                obj = null;
                return obj;
            }

            obj = new Usuario();
            obj.setId(rs.getInt("Id"));
            obj.setEmail(rs.getString("Email"));
            obj.setPassword(rs.getString("Password"));
            var5 = obj;
        } catch (SQLException var9) {
            throw new DbException(var9.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }

        return var5;
    }

    public Usuario findEmailSenha(String email, String senha) {
        PreparedStatement st = null;
        ResultSet rs = null;

        Usuario var5;
        try {
            st = this.conn.prepareStatement("SELECT * FROM usuario WHERE Email = ? AND Password = ?");
            st.setString(1, email);
            st.setString(2, senha);
            rs = st.executeQuery();
            Usuario obj;
            if (!rs.next()) {
                obj = null;
                return obj;
            }

            obj = new Usuario();
            obj.setId(rs.getInt("Id"));
            obj.setEmail(rs.getString("Email"));
            obj.setPassword(rs.getString("Password"));
            var5 = obj;
        } catch (SQLException var9) {
            throw new DbException(var9.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }

        return var5;
    }

    public void insert(Usuario obj) {
        PreparedStatement st = null;

        try {
            st = this.conn.prepareStatement("INSERT INTO usuario (Email, Password) VALUES (?, ?)", 1);
            st.setString(1, obj.getEmailUsuario());
            st.setString(2, obj.getPasswordUsuario());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected <= 0) {
                throw new DbException("Unexpected error! No rows affected!");
            }

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                obj.setId(id);
            }
        } catch (SQLException var9) {
            throw new DbException(var9.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    public void update(Usuario obj) {
        PreparedStatement st = null;

        try {
            st = this.conn.prepareStatement("UPDATE usuario (Email, Password) VALUES (?, ?)", 1);
            st.setString(1, obj.getEmailUsuario());
            st.setString(2, obj.getPasswordUsuario());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected <= 0) {
                throw new DbException("Unexpected error! No rows affected!");
            }

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                obj.setId(id);
            }
        } catch (SQLException var9) {
            throw new DbException(var9.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = this.conn.prepareStatement("DELETE FROM usuario WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException var7) {
            throw new DbIntegrityException(var7.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }
}
