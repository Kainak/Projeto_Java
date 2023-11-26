package model.dao.impl;

import db.DB;
import model.entities.CategoriaFornecedores;

public class DaoFactory {
	public static CategoriaFornecedoresDao createCategoriaFornecedoresDao() {
		return new CategoriaFornecedoresDaoJDBC(DB.getConnection());
	}
	public static ProdutorDao createProdutorDao() {
		return new produtorDaoJDBC(DB.getConnection());
	}
	public static FornecedorDao createFornecedorDao() { return new FornecedorDaoJDBC(DB.getConnection());
	}

	public static DocumentoDao createDocumentoDao() {   return new DocumentoDaoJDBC(DB.getConnection());                                             }
}
