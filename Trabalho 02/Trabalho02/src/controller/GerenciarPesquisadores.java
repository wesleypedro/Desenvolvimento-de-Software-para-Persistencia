package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.PesquisadorDAO;
import dao.PesquisadorProjetosDAO;
import dao.ProjetosDAO;
import dao.jpa.PesquisadorJPADAO;
import dao.jpa.PesquisadorProjetosJPADAO;
import dao.jpa.ProjetosJPADAO;
import model.Endereco;
import model.Pesquisador;
import model.PesquisadorProjetos;
import model.Projetos;

public class GerenciarPesquisadores {
	static Scanner entrada = new Scanner(System.in);
	
	public void Gerenciar() {
		int opcao = 1;
		
		while(true) {
			System.out.println(" ___________________________________________________\n"+
							   "|                                                   |\n"+
							   "| Escolha uma opção:                                |\n"+
							   "|___________________________________________________|\n"+
							   "| 1. Cadastrar Pesquisadores                        |\n"+
							   "| 2. Pesquisar Pesquisador                          |\n"+
							   "| 3. Listar Pesquisadores                           |\n"+
							   "| 4. Atualizar Pesquisador                          |\n"+
							   "| 5. Excluir Pesquisador                            |\n"+
							   "| 6. Associar Pesquisador a Projeto                 |\n"+
							   "| 7. Voltar ao Menu Anterior                        |\n"+
							   "| 8. Sair                                           |\n"+
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
				AssociarProjeto();
				break;
	
			case 7:
				return;
	
			case 8:
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
		int in = 1;
		
		System.out.println("Digite o nome do Pesquisador");
		String nome = entrada.nextLine();
		String sexo="";
		while(in != 0) {
			System.out.println("Informe o sexo\n1. Masculino\t2. Feminino");
			in = Integer.parseInt(entrada.nextLine());
			if(in == 1) {
				sexo = "Masculino";
				in = 0;
			} else if(in == 2){
				sexo = "Feminino";
				in = 0;
			}
			else System.out.println("Opção inválida!");
		}
		System.out.println("Digite a data de Nascimento (dd/MM/yyyy)");
		Date nascimento;
		try {
			nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
		} catch (ParseException e1) {
			nascimento = null;
			e1.printStackTrace();
		}
		System.out.println("Digite o Salário");
		double salario = Double.parseDouble(entrada.nextLine());
		System.out.println("Digite a Área de Atuação");
		String area = entrada.nextLine();
		System.out.println("Digite o Logradouro");
		String logradouro = entrada.nextLine();
		System.out.println("Digite o Bairro");
		String bairro = entrada.nextLine();
		System.out.println("Digite o Número");
		int numero = Integer.parseInt(entrada.nextLine());
		System.out.println("Digite a Cidade");
		String cidade = entrada.nextLine();
		
		
		System.out.println("Deseja realmente cadastrar o Pesquisador?\n1. Sim\t2. Não");
		int opcao = Integer.parseInt(entrada.nextLine());
		
		if(opcao == 1) {
//			EnderecoDAO eDao = new EnderecoJPADAO();
			PesquisadorDAO pDao = new PesquisadorJPADAO();
			
			Endereco endereco = new Endereco(logradouro, bairro, numero, cidade);
			Pesquisador pesquisador = new Pesquisador(nome, sexo, nascimento, salario, endereco, area);
			
			try {
				pDao.beginTransaction();
				pDao.save(pesquisador);
				pDao.commit();
				
				System.out.println("Pesquisador salvo com sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				pDao.rollback();
				e.printStackTrace();
			} finally {
				pDao.close();
			}
		}
	}
	
	public static void Pesquisar() {
		boolean loop = true;
		while(loop) {
			System.out.println("Pesquisar por:\n"
					+ "1. Nome\t2. Área de Atuação\t3. Salario\t4. Cancelar");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch (opcao) {
			case 1:
				System.out.println("Entre com o nome que você deseja pesquisar");
				String nome = entrada.nextLine();
				
				PesquisadorDAO pDaoNome = new PesquisadorJPADAO();
				List<Pesquisador> pesquisadorNome = pDaoNome.findByNome(nome);
//				pDaoNome.close();
				
				if(pesquisadorNome == null) {
					System.out.println("Não há Pesquisador com esse Nome");
				}
				else {
					for(Pesquisador pesquisador : pesquisadorNome) System.out.println(pesquisador);
				}
				pDaoNome.close();
				break;

			case 2:
				System.out.println("Entre com a Área de Atuação que você deseja pesquisar");
				String area = entrada.nextLine();
				
				PesquisadorDAO pDaoArea = new PesquisadorJPADAO();
				List<Pesquisador> pesquisadorArea = pDaoArea.findByArea(area);
//				pDaoArea.close();
				
				if(pesquisadorArea == null) {
					System.out.println("Não há Pesquisadores com essa Área de Atuação");
				}
				else {
					for(Pesquisador pesquisador : pesquisadorArea) System.out.println(pesquisador);
				}
				pDaoArea.close();
				break;
				
			case 3:
				System.out.println("Entre com o salario que você deseja pesquisar");
				double salario = Double.parseDouble(entrada.nextLine());
				
				PesquisadorDAO pDaoSalario = new PesquisadorJPADAO();
				List<Pesquisador> pesquisadorSalario = pDaoSalario.findBySalario(salario);
//				pDaoSalario.close();
				
				if(pesquisadorSalario == null) {
					System.out.println("Não há Pesquisador com esse Salário");
				}
				else {
					for(Pesquisador pesquisador : pesquisadorSalario) System.out.println(pesquisador);
				}
				pDaoSalario.close();
				break;
				
			case 4:
				loop = false;
				break;

			default:
				System.out.println("Opção inválida!");
				break;
			}
		}
	}
	
	public static void Listar() {
		PesquisadorDAO pDao = new PesquisadorJPADAO();
		List<Pesquisador> pesquisador = pDao.findAll();	
//		pDao.close();
		
		for(Pesquisador fPesquisador : pesquisador) System.out.println(fPesquisador);
		
		pDao.close();
	}
	
	public static void Atualizar() {
		int in = 1;
		PesquisadorDAO pDao = new PesquisadorJPADAO();
		
		System.out.println("Digite o Código do Funcionário de Limpeza a ser atualizado");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		Pesquisador pesquisador = pDao.find(codigo);
		
		if(pesquisador != null) {
			System.out.println("Digite o nome do Pesquisador");
			String nome = entrada.nextLine();
			String sexo="";
			while(in != 0) {
				System.out.println("Informe o sexo\n1. Masculino\t2. Feminino");
				in = Integer.parseInt(entrada.nextLine());
				if(in == 1) {
					sexo = "Masculino";
					in = 0;
				} else if(in == 2){
					sexo = "Feminino";
					in = 0;
				}
				else System.out.println("Opção inválida!");
			}
			System.out.println("Digite a data de Nascimento (dd/MM/yyyy)");
			Date nascimento;
			try {
				nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(entrada.nextLine());
			} catch (ParseException e1) {
				nascimento = null;
				e1.printStackTrace();
			}
			System.out.println("Digite o Salário");
			double salario = Double.parseDouble(entrada.nextLine());
			System.out.println("Digite a Área de Atuação");
			String area = entrada.nextLine();
			System.out.println("Digite o Logradouro");
			String logradouro = entrada.nextLine();
			System.out.println("Digite o Bairro");
			String bairro = entrada.nextLine();
			System.out.println("Digite o Número");
			int numero = Integer.parseInt(entrada.nextLine());
			System.out.println("Digite a Cidade");
			String cidade = entrada.nextLine();
			
			Endereco endereco = new Endereco(logradouro, bairro, numero, cidade);
			
			pesquisador.setNome(nome);
			pesquisador.setSexo(sexo);
			pesquisador.setDataNascimento(nascimento);
			pesquisador.setSalario(salario);
			pesquisador.setAreaAtuacao(area);
			pesquisador.setEndereco(endereco);
			
			try {
				pDao.beginTransaction();
				pDao.save(pesquisador);
				pDao.commit();
				System.out.println("Atualizado com Sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				pDao.rollback();
				e.printStackTrace();
			} finally {
				pDao.close();
			}
			
		} else System.out.println("Pesquisador não encontrado com esse código!");
	}
	
	public static void Excluir() {
		System.out.println("Digite o Código do Pesquisador a ser excluído");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		PesquisadorDAO pDao = new PesquisadorJPADAO();
		try {
			pDao.beginTransaction();
			pDao.deleteById(codigo);
			pDao.close();
			pDao.commit();
			
			System.out.println("Excluído com Sucesso!");
		} catch (IllegalStateException | PersistenceException e) {
			pDao.rollback();
			e.printStackTrace();
			
			System.out.println("Erro ao Excluir");
		} finally {
			pDao.close();
		}
	}
	
	public static void AssociarProjeto() {
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
						
						System.out.println("Associado com sucesso!");
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
}
