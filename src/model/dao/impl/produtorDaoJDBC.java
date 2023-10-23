package model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.impl.produtorDao;
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
}
