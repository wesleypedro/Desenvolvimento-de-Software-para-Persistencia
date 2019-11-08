package dao.jpa;

import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioJPADAO extends GenericJPADAO<Funcionario> implements FuncionarioDAO {

//	public FuncionarioJPADAO(Class<Funcionario> persistenceClass) {
//		super(persistenceClass);
//	}
	
	public FuncionarioJPADAO() {
		super(Funcionario.class);
	}

}
