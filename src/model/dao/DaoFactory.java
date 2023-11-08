package model.dao;

import db.DB;
import model.dao.impl.*;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
	public static produtorDao createProdutorDao() {
		return new produtorDaoJDBC(DB.getConnection());
	}
	public static DocumentoDao createDocumentoDao() {   return new DocumentoDaoJDBC(DB.getConnection());                                             }
}
