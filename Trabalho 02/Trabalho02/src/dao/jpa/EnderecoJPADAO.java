package dao.jpa;

import dao.EnderecoDAO;
import model.Endereco;

public class EnderecoJPADAO extends GenericJPADAO<Endereco> implements EnderecoDAO {
	public EnderecoJPADAO() {
		super(Endereco.class);
	}
	
}
