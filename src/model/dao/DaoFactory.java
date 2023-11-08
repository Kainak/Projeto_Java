package model.dao;

import db.DB;
import model.dao.impl.*;

public class DaoFactory {

	public static ProdutorDao createProdutorDao() {
		return new produtorDaoJDBC(DB.getConnection());
	}
	public static FornecedorDao createFornecedorDao() { return new FornecedorDaoJDBC(DB.getConnection());
	}

	public static DocumentoDao createDocumentoDao() {   return new DocumentoDaoJDBC(DB.getConnection());                                             }
}
