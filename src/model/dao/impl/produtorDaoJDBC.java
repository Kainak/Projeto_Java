package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.produtorDao;
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
                            + "(nome) "
                            + "VALUES "
                            + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            //st.setInt(1, obj.getIDprodutor());
            st.setString(1, obj.getNome());
//            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
//            st.setDouble(4, obj.getBaseSalary());
//            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    //obj.setId(id);
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
                Produtor obj = new Produtor();
                obj.setIDprodutor(rs.getInt("IDprodutor"));
                obj.setNome(rs.getString("nome"));
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
                            "SET Nome = ? " +
                            "WHERE IDprodutor = ?");
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getIDprodutor());
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
