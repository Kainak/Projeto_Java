package model.dao.impl;

import db.DB;

public class DaoFactory {

	public static ProdutorDao createProdutorDao() {
		return new produtorDaoJDBC(DB.getConnection());
	}

	public static FornecedorDao createFornecedorDao() {
		return new FornecedorDaoJDBC(DB.getConnection());
	}

	public static DocumentoDao createDocumentoDao() {
		return new DocumentoDaoJDBC(DB.getConnection());                                             }
}

	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
}
