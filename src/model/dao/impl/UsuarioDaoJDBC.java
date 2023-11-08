package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {
    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
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

    public List<Usuario> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = this.conn.prepareStatement("SELECT * FROM usuario ORDER BY Email");
            rs = st.executeQuery();
            List<Usuario> list = new ArrayList();

            while(rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("Id"));
                obj.setEmail(rs.getString("Email"));
                obj.setPassword(rs.getString("Password"));
                list.add(obj);
            }

            List<Usuario> var10 = list;
            return var10;
        } catch (SQLException var8) {
            throw new DbException(var8.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }
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
            st = this.conn.prepareStatement("UPDATE usuario SET (Email, Password) VALUE (?, ?) WHERE Id = ?");
            st.setString(1, obj.getEmailUsuario());
            st.setString(2, obj.getPasswordUsuario());
            st.setInt(3, obj.getIDUsuario());
            st.executeUpdate();
        } catch (SQLException var7) {
            throw new DbException(var7.getMessage());
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
