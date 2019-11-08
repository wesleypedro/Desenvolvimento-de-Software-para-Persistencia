package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.DepartamentoDAO;
import dao.FuncionarioDAO;
import dao.ProjetosDAO;
import dao.jpa.DepartamentoJPADAO;
import dao.jpa.FuncionarioJPADAO;
import dao.jpa.ProjetosJPADAO;
import model.Departamento;
import model.Funcionario;
import model.Projetos;

public class GerenciarDepartamentos {
	static Scanner entrada = new Scanner(System.in);
	
	public void Gerenciar() {
		int opcao = 1;
		
		while(true) {
			System.out.println(" ___________________________________________________\n"
					  		  +"|                                                   |\n"
					  		  +"| Escolha uma opção:                                |\n"
					  		  +"|___________________________________________________|\n"
							  +"| 1. Cadastrar Departamento                         |\n"
							  +"| 2. Pesquisar Departamento                         |\n"
							  +"| 3. Listar Departamentos                           |\n"
							  +"| 4. Atualizar Departamento                         |\n"
							  +"| 5. Excluir Departamento                           |\n"
							  +"| 6. Associar Projeto                               |\n"
							  +"| 7. Associar Funcionário                           |\n"
							  +"| 8. Voltar ao Menu Anterior                        |\n"
							  +"| 9. Sair                                           |\n"
							  +"|___________________________________________________|");
			opcao = Integer.parseInt(entrada.nextLine());
			
			switch (opcao) {
			case 1:
				Cadastrar();
				break;
	
			case 2:
				Pesquisar();
				break;
	
			case 3:
				Listar();
				break;
	
			case 4:
				Atualizar();
				break;
	
			case 5:
				Excluir();
				break;
	
			case 6:
				AssociarProjeto();
				break;
	
			case 7:
				AssociarFuncionario();
				break;
	
			case 8:
				return;
	
			case 9:
				System.out.println("Encerrando Sistema");
				System.exit(0);
				break;
	
			default:
				System.out.println("Opção inválida!\nEscolha uma opção válida");
				break;
			}
		}
	}
	
	public static void Cadastrar() {
		System.out.println("Entre com o nome do Departamento");
		String nome = entrada.nextLine();
		System.out.println("Entre com o número do Departamento");
		int numero = Integer.parseInt(entrada.nextLine());
		System.out.println("Deseja realemte cadastrar o Departamento?\n1. Sim\t2. Não");
		int opcao = Integer.parseInt(entrada.nextLine());
		
		if(opcao == 1) {
			DepartamentoDAO dDao = new DepartamentoJPADAO();
			
			try {
				dDao.beginTransaction();
				dDao.save(new Departamento(nome, numero));
				dDao.commit();
				
				System.out.println("Departamento salvo com sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				dDao.rollback();
				e.printStackTrace();
			} finally {
				dDao.close();
			}
		}
		
		else {
			System.out.println("Cancelado");
		}
	}
	
	public static void Pesquisar() {
		boolean loop = true;
		while(loop) {
			System.out.println("Pesquisar por:\n"
					+ "1. Nome\t2. Número\t3. Cancelar");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch (opcao) {
			case 1:
				System.out.println("Entre com o nome que você deseja pesquisar");
				String nome = entrada.nextLine();
				
				DepartamentoDAO dDaoNome = new DepartamentoJPADAO();
				List<Departamento> departamentosNome = dDaoNome.findByNome(nome);
//				dDaoNome.close();
				
				if(departamentosNome == null) {
					System.out.println("Não há Departamentos com esse Nome");
				}
				else {
					for(Departamento departamentoNome : departamentosNome) System.out.println(departamentoNome);
				}
				dDaoNome.close();
				break;

			case 2:
				System.out.println("Entre com o número pelo qual você deseja pesquisar");
				int numero = Integer.parseInt(entrada.nextLine());
				
				DepartamentoDAO dDaoNumero = new DepartamentoJPADAO();
				List<Departamento> departamentosNumero = dDaoNumero.findByNumero(numero);
//				dDaoNumero.close();
				
				if(departamentosNumero == null) {
					System.out.println("Não há Departamentos com esse Número");
				}
				else {
					for(Departamento departamentoNumero : departamentosNumero) System.out.println(departamentoNumero);
				}
				dDaoNumero.close();
				break;
				
			case 3:
				loop = false;
				break;

			default:
				System.out.println("Opção inválida!");
				break;
			}
		}
	}
	
	public static void Listar() {
		DepartamentoDAO dDao = new DepartamentoJPADAO();
		List<Departamento> departamentos = dDao.findAll();
//		dDao.close();
		
		for(Departamento departamento : departamentos) System.out.println(departamento);
		
		dDao.close();
	}
	
	public static void Atualizar() {
		System.out.println("Digite o Id do departamento a ser alterado");
		Long id = Long.parseLong(entrada.nextLine());
		System.out.println("Digite o novo nome do Departamento");
		String nome = entrada.nextLine();
		System.out.println("Digite o novo número do Departamento");
		int numero = Integer.parseInt(entrada.nextLine());
		Departamento departamento = new Departamento(id, nome, numero);
		
		DepartamentoDAO dDao = new DepartamentoJPADAO();
		
		try {
			dDao.beginTransaction();
			dDao.save(departamento);
			dDao.commit();
			
			System.out.println("Atualizado com Sucesso!");
		} catch (IllegalStateException | PersistenceException e) {
			dDao.rollback();
			e.printStackTrace();
			
			System.out.println("Erro ao Atualizar");
		} finally {
			dDao.close();
		}
	}
	
	public static void Excluir() {
		System.out.println("Digite o Id do Departamento a ser excluído");
		Long id = Long.parseLong(entrada.nextLine());
		
		DepartamentoDAO dDao = new DepartamentoJPADAO();
		try {
			dDao.beginTransaction();
			dDao.deleteById(id);
			dDao.close();
			dDao.commit();
			
			System.out.println("Excluído com Sucesso!");
		} catch (IllegalStateException | PersistenceException e) {
			dDao.rollback();
			e.printStackTrace();
			
			System.out.println("Erro ao Excluir");
		} finally {
			dDao.close();
		}
	}
	
	public static void AssociarProjeto() {
		System.out.println("Digite o id do Departamento");
		Long idDepartamento = Long.parseLong(entrada.nextLine());
		System.out.println("Digite o id do Projeto");
		Long idProjeto = Long.parseLong(entrada.nextLine());
		
		DepartamentoDAO dDao = new DepartamentoJPADAO();
		ProjetosDAO pDao = new ProjetosJPADAO();
		
		Departamento departamento = dDao.find(idDepartamento);
		if(departamento != null) {
			Projetos projeto = pDao.find(idProjeto);
			if(projeto != null) {
				projeto.setDepartamento(departamento);
				departamento.setAddProjetos(projeto);
				try {
					dDao.beginTransaction();
					dDao.save(departamento);
					dDao.commit();
					
					System.out.println("Associado com Sucesso!");
				} catch (IllegalStateException | PersistenceException e) {
					dDao.rollback();
					e.printStackTrace();
					
					System.out.println("Erro ao Associar");
				} finally {
					dDao.close();
					pDao.close();
				}
			} else System.out.println("Projeto não encontrado com esse Id");
		} else System.out.println("Departamento não encontrado com esse Id");
	}
	
	public static void AssociarFuncionario() {
		System.out.println("Digite o id do Departamento");
		Long idDepartamento = Long.parseLong(entrada.nextLine());
		System.out.println("Digite o código de Identificação do Funcionario");
		Long codigoFuncionario = Long.parseLong(entrada.nextLine());
		
		DepartamentoDAO dDao = new DepartamentoJPADAO();
		FuncionarioDAO fDao = new FuncionarioJPADAO();
		
		Departamento departamento = dDao.find(idDepartamento);
		if(departamento != null) {
			Funcionario funcionario = fDao.find(codigoFuncionario);
			if(funcionario != null) {
				funcionario.setDepartamento(departamento);
				departamento.setAddFuncionario(funcionario);
				try {
					dDao.beginTransaction();
					dDao.save(departamento);
					dDao.commit();
					
					System.out.println("Pesquisador Associado com Sucesso!");
				} catch (IllegalStateException | PersistenceException e) {
					dDao.rollback();
					e.printStackTrace();
					
					System.out.println("Erro ao Associar");
				} finally {
					dDao.close();
					fDao.close();
				}
			} else System.out.println("Funcionário não encontrado com esse Código");
		} else System.out.println("Departamento não encontrado com esse Id");
	}
}
