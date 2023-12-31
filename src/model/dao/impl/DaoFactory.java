package model.dao.impl;

import db.DB;
import model.entities.CategoriaFornecedores;

public class DaoFactory {

	public static ProdutorDao createProdutorDao() {
		return new ProdutorDaoJDBC(DB.getConnection());
	}

	public static FornecedorDao createFornecedorDao() {
		return new FornecedorDaoJDBC(DB.getConnection());
	}

	public static DocumentoDao createDocumentoDao() {
		return new DocumentoDaoJDBC(DB.getConnection());
	}

	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}

	public static CategoriaFornecedoresDao createCategoriaFornecedoresDao() {
		return new CategoriaFornecedoresDaoJDBC(DB.getConnection());
	}
}
