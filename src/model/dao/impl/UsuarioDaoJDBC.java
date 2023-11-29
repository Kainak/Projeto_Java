package model.dao.impl;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.impl.UsuarioDao;
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

    public Usuario findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = this.conn.prepareStatement("SELECT * FROM usuarios WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("Id"));
                obj.setEmail(rs.getString("Email"));
                obj.setPassword(rs.getString("Password"));
                return obj;
            }
            return null;
        } catch (SQLException var9) {
            throw new DbException(var9.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public List<Usuario> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = this.conn.prepareStatement("SELECT * FROM usuarios ORDER BY Email");
            rs = st.executeQuery();
            List<Usuario> list = new ArrayList<>();

            while(rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("Id"));
                obj.setEmail(rs.getString("Email"));
                obj.setPassword(rs.getString("Password"));
                list.add(obj);
            }

            return list;
        } catch (SQLException var8) {
            throw new DbException(var8.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public void insert(Usuario obj) {
        PreparedStatement st = null;

        try {
            st = this.conn.prepareStatement("INSERT INTO usuarios (Email, Password) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getEmail());
            st.setString(2, obj.getPassword());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
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
            st = this.conn.prepareStatement("UPDATE usuarios SET Email = ?, Password = ? WHERE Id = ?");
            st.setString(1, obj.getEmail());
            st.setString(2, obj.getPassword());
            st.setInt(3, obj.getId());
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
            st = this.conn.prepareStatement("DELETE FROM usuarios WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException var7) {
            throw new DbIntegrityException(var7.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
