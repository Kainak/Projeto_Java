package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.dao.impl.produtorDaoJDBC;
import model.dao.impl.FornecedorDao;
import model.dao.impl.FornecedorDaoJDBC;
import model.entities.Fornecedor;
import model.dao.impl.*;
import model.entities.Produtor;

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
	public static FornecedorDao createFornecedorDao() { return new FornecedorDaoJDBC(DB.getConnection());
	}

	public static DocumentoDao createDocumentoDao() {   return new DocumentoDaoJDBC(DB.getConnection());                                             }
}
