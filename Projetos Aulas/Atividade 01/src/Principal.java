import dao.ClienteDAO;
import dao.jdbc.ClienteJDBCDAO;

public class Principal {
	

	public static void main(String[] args) {
		
		ClienteDAO cdao = new ClienteJDBCDAO();
		/*
		// DELETAR
		boolean res = cdao.delete(5);
		System.out.println(res);
		*/
		
		/*
		// BUSCAR POR ID
		Cliente c = cdao.findById(12312);
		System.out.println(c);
		*/
		/*
		// ATUALIZAR
		Cliente c = new Cliente(6, "Robson", "999");
		cdao.update(c);
		*/
		
		/*
		// INSERIR
		Cliente c = new Cliente("Luis", "1000000");
		cdao.insert(c);		
		System.out.println(c);
		
		/*
		// BUSCAR TODOS
		List<Cliente> list = cdao.findAll();
		for(Cliente c : list) System.out.println(c);
		*/

		cdao.closeConnection();
		System.out.println("FIM");
	}
}



