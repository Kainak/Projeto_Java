package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Produtor;
import model.entities.Seller;

public class produtorDaoJDBC implements produtorDao{

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
                            + "(IDprodutor, nome) "
                            + "VALUES "
                            + "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getIDprodutor());
            st.setString(2, obj.getNome());
//            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
//            st.setDouble(4, obj.getBaseSalary());
//            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
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
}
