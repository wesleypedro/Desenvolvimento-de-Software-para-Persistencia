package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.DepartamentoDAO;
import dao.PesquisadorDAO;
import dao.PesquisadorProjetosDAO;
import dao.ProjetosDAO;
import dao.jpa.DepartamentoJPADAO;
import dao.jpa.PesquisadorJPADAO;
import dao.jpa.PesquisadorProjetosJPADAO;
import dao.jpa.ProjetosJPADAO;
import model.Departamento;
import model.Pesquisador;
import model.PesquisadorProjetos;
import model.Projetos;

public class GerenciarProjetos {
	static Scanner entrada = new Scanner(System.in);
	
	public void Gerenciar() {
		int opcao = 1;
		
		while(true) {
			System.out.println(" ___________________________________________________\n"+
							   "|                                                   |\n"+
							   "| Escolha uma opção:                                |\n"+
							   "|___________________________________________________|\n"+
							   "| 1. Cadastrar Projeto                              |\n"+
							   "| 2. Pesquisar Projeto                              |\n"+
							   "| 3. Listar Projetos                                |\n"+
							   "| 4. Atualizar Projeto                              |\n"+
							   "| 5. Excluir Projeto                                |\n"+
							   "| 6. Associar Projeto a Pesquisador                 |\n"+
							   "| 7. Associar Projeto a Departamento                |\n"+
							   "| 8. Voltar ao Menu Anterior                        |\n"+
							   "| 9. Sair                                           |\n"+
							   "|___________________________________________________|");
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
				AssociarPesquisador();
				break;
				
			case 7:
				AssociarDepartamento();
				break;
	
			case 8:
				return;
	
			case 9:
				System.out.println("Encerrando Sistema!");
				entrada.close();
				System.out.println("Sistema encerrado\n");
				System.exit(0);
				break;
	
			default:
				System.out.println("Opção inválida!\nEscolha uma opção válida");
				break;
			}
		}
	}
	
	public static void Cadastrar() {
		System.out.println("Digite o nome do Projeto");
		String nome = entrada.nextLine();
		System.out.println("Digite o número de identificação do Projeto");
		Long identificacao = Long.parseLong(entrada.nextLine());
		System.out.println("Digite a data de início do Projeto (dd/MM/yyyy)");
		Date inicio;
		try {
			inicio = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
		} catch (ParseException e1) {
			inicio = null;
			e1.printStackTrace();
		}
		System.out.println("Digite a data de fim do Projeto (dd/MM/yyyy)");
		Date fim;
		try {
			fim = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
		} catch (ParseException e1) {
			fim = null;
			e1.printStackTrace();
		}
		
		System.out.println("Deseja realemte cadastrar o Projeto?\n1. Sim\t2. Não");
		int opcao = Integer.parseInt(entrada.nextLine());
		
		if(opcao == 1) {
			ProjetosDAO pDao = new ProjetosJPADAO();
			
			try {
				pDao.beginTransaction();
				pDao.save(new Projetos(nome, identificacao, inicio, fim));
				pDao.commit();
				
				System.out.println("Projeto salvo com sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				pDao.rollback();
				e.printStackTrace();
			} finally {
				pDao.close();
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
					+ "1. Nome\t2. Número de Identificação\t3. Cancelar");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch (opcao) {
			case 1:
				System.out.println("Entre com o nome que você deseja pesquisar");
				String nome = entrada.nextLine();
				
				ProjetosDAO pDaoNome = new ProjetosJPADAO();
				List<Projetos> projetosNome = pDaoNome.findByNome(nome);
//				pDaoNome.close();
				
				if(projetosNome == null) {
					System.out.println("Não há Projetos com esse nome");
				}
				else {
					for(Projetos projetoNome : projetosNome) System.out.println(projetoNome);
				}
				pDaoNome.close();
				break;

			case 2:
				System.out.println("Entre com o Número de Identificação pelo qual você deseja pesquisar");
				Long numero = Long.parseLong(entrada.nextLine());
				
				ProjetosDAO pDaoNumero = new ProjetosJPADAO();
				List<Projetos> projetosNumero = pDaoNumero.findByNumero(numero);
//				pDaoNumero.close();
				
				if(projetosNumero == null) {
					System.out.println("Não há Projetos com esse Número de Identificação");
				}
				else {
					for(Projetos projetoNumero : projetosNumero) System.out.println(projetoNumero);
				}
				pDaoNumero.close();
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
		ProjetosDAO pDao = new ProjetosJPADAO();
		List<Projetos> projetos = pDao.findAll();
//		pDao.close();
		
		for(Projetos projeto : projetos) System.out.println(projeto);
		
		pDao.close();
	}
	
	public static void Atualizar() {
		System.out.println("Digite o Id do Projeto a ser alterado");
		Long id = Long.parseLong(entrada.nextLine());
		System.out.println("Digite o novo nome do Projeto");
		String nome = entrada.nextLine();
		System.out.println("Digite o novo Número de Identificação do Projeto");
		Long identificacao = Long.parseLong(entrada.nextLine());
		System.out.println("Digite a nova data de início do Projeto (dd/MM/yyyy)");
		Date inicio;
		try {
			inicio = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
		} catch (ParseException e1) {
			inicio = null;
			e1.printStackTrace();
		}
		System.out.println("Digite a nova data de fim do Projeto (dd/MM/yyyy)");
		Date fim;
		try {
			fim = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
		} catch (ParseException e1) {
			fim = null;
			e1.printStackTrace();
		}
		Projetos projeto = new Projetos(id, nome, identificacao, inicio, fim);
		
		ProjetosDAO pDao = new ProjetosJPADAO();
		
		try {
			pDao.beginTransaction();
			pDao.save(projeto);
			pDao.commit();
			System.out.println("Atualizado com Sucesso!");
		} catch (IllegalStateException | PersistenceException e) {
			pDao.rollback();
			e.printStackTrace();
			System.out.println("Erro ao Atualizar");
		} finally {
			pDao.close();
		}
	}
	
	public static void Excluir() {
		System.out.println("Digite o Id do Projeto a ser excluído");
		Long id = Long.parseLong(entrada.nextLine());
		
		ProjetosDAO pDao = new ProjetosJPADAO();
		try {
			pDao.beginTransaction();
			pDao.deleteById(id);
			pDao.close();
			pDao.commit();
			System.out.println("Excluído com sucesso!");
		} catch (IllegalStateException | PersistenceException e) {
			pDao.rollback();
			e.printStackTrace();
			System.out.println("Erro ao Excluir");
		} finally {
			pDao.close();
		}
	}
	
	public static void AssociarPesquisador() {
		System.out.println("Digite o Id do Projeto");
		Long id = Long.parseLong(entrada.nextLine());
		System.out.println("Digite o Código de Identificação do Funcionário");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		ProjetosDAO pjDao = new ProjetosJPADAO();
		PesquisadorDAO pqDao = new PesquisadorJPADAO();
		PesquisadorProjetosDAO ppDao = new PesquisadorProjetosJPADAO();
		
		Projetos projeto = pjDao.find(id);
		Pesquisador pesquisador = pqDao.find(codigo);
		
		
		if(projeto != null) {
			if(pesquisador != null) {
				PesquisadorProjetos pesquisadorProjetos = ppDao.findByRelation(pesquisador, projeto);
				
				if(pesquisadorProjetos == null) {
					System.out.println("Digite a quantidade de horas que o Pesquisador trabalho no Projeto\n"
							+ "Exemplo: 50h >> 50");
					int horas = Integer.parseInt(entrada.nextLine());
					pesquisadorProjetos.setNumeroHoras(horas);
					
					pesquisador.setAddPesquisadorProjetos(pesquisadorProjetos);
					pesquisadorProjetos.setPesquisador(pesquisador);
					
					projeto.setAddPesquisadorProjetos(pesquisadorProjetos);
					pesquisadorProjetos.setProjetos(projeto);
					
					try {
						ppDao.beginTransaction();
						ppDao.save(pesquisadorProjetos);
						ppDao.commit();
						
						System.out.println("Salvo com sucesso!");
					} catch (IllegalStateException | PersistenceException e) {
						ppDao.rollback();
						e.printStackTrace();
						
						System.out.println("Erro ao salvar");
					} finally {
						pjDao.close();
						pqDao.close();
						ppDao.close();
					}
					
				} else System.out.println("Relação entre Projeto e Pesquisador já existe!");
			} else System.out.println("Não há Funcionários cadastrado com esse código!");
		} else System.out.println("Não há Projeto cadastrado com esse Id!");
	}
	
	public static void AssociarDepartamento() {
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
					System.out.println("Departamento Associado com Sucesso!");
				} catch (IllegalStateException | PersistenceException e) {
					dDao.rollback();
					e.printStackTrace();
					System.out.println("Erro ao Associar com Departamento!");
				} finally {
					dDao.close();
					pDao.close();
				}
			} else System.out.println("Projeto não encontrado com esse Id");
		} else System.out.println("Departamento não encontrado com esse Id");
	}
}
