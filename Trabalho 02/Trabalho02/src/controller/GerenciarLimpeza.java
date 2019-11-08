package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.LimpezaDAO;
import dao.jpa.LimpezaJPADAO;
import model.Endereco;
import model.Limpeza;

public class GerenciarLimpeza {
	static Scanner entrada = new Scanner(System.in);
	
	public void Gerenciar() {
		int opcao = 1;
		
		while(true) {
			System.out.println(" ___________________________________________________\n"+
							   "|                                                   |\n"+
							   "| Escolha uma opção:                                |\n"+
							   "|___________________________________________________|\n"+ 
							   "| 1. Cadastrar Funcionário de Limpeza               |\n"+ 
							   "| 2. Pesquisar Funcionário de Limpeza               |\n"+ 
							   "| 3. Listar Funcionários de Limpeza                 |\n"+ 
							   "| 4. Atualizar Funcionário de Limpeza               |\n"+ 
							   "| 5. Excluir Funcionário de Limpeza                 |\n"+ 
							   "| 6. Associar Superior                              |\n"+ 
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
				AssociarSuperior();
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
		
		System.out.println("Digite o nome do Funcionário de Limpeza");
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
		System.out.println("Digite o Cargo");
		String cargo = entrada.nextLine();
		System.out.println("Digite a Jornada de Trabalho");
		int jornada = Integer.parseInt(entrada.nextLine());
		System.out.println("Digite o Logradouro");
		String logradouro = entrada.nextLine();
		System.out.println("Digite o Bairro");
		String bairro = entrada.nextLine();
		System.out.println("Digite o Número");
		int numero = Integer.parseInt(entrada.nextLine());
		System.out.println("Digite a Cidade");
		String cidade = entrada.nextLine();

		System.out.println("Deseja realmente cadastrar o Funcionário de Limpeza?\n1. Sim\t2. Não");
		int opcao = Integer.parseInt(entrada.nextLine()); 
		
		if(opcao == 1) {
			LimpezaDAO lDao = new LimpezaJPADAO();
			
			Endereco endereco = new Endereco(logradouro, bairro, numero, cidade);
			Limpeza limpeza = new Limpeza(nome, sexo, nascimento, salario, endereco, cargo, jornada);
			
			try {
				lDao.beginTransaction();
				lDao.save(limpeza);
				lDao.commit();
				
				System.out.println("Funcionário salvo com sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				lDao.rollback();
				e.printStackTrace();
			} finally {
				lDao.close();
			}
		}
		
		else if(opcao == 2) {
			System.out.println("Cancelado!");
		}
	}
	
	public static void Pesquisar() {
		boolean loop = true;
		while(loop) {
			System.out.println("Pesquisar por:\n"
					+ "1. Nome\t2. Cargo\t3. Salario\t4. Jornada de Trabalho\t5. Cancelar");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch (opcao) {
			case 1:
				System.out.println("Entre com o nome que você deseja pesquisar");
				String nome = entrada.nextLine();
				
				LimpezaDAO lDaoNome = new LimpezaJPADAO();
				List<Limpeza> limpezaNome = lDaoNome.findByNome(nome);
//				lDaoNome.close();
				
				if(limpezaNome == null) {
					System.out.println("Não há Funcionários de Limpeza com esse Nome");
				}
				else {
					for(Limpeza limpeza : limpezaNome) System.out.println(limpeza);
				}
				lDaoNome.close();
				break;

			case 2:
				System.out.println("Entre com o cargo que você deseja pesquisar");
				String cargo = entrada.nextLine();
				
				LimpezaDAO lDaoCargo = new LimpezaJPADAO();
				List<Limpeza> limpezaCargo = lDaoCargo.findByCargo(cargo);
//				lDaoCargo.close();
				
				if(limpezaCargo == null) {
					System.out.println("Não há Funcionários de Limpeza com esse cargo");
				}
				else {
					for(Limpeza limpeza : limpezaCargo) System.out.println(limpeza);
				}
				lDaoCargo.close();
				break;
				
			case 3:
				System.out.println("Entre com o salario que você deseja pesquisar");
				double salario = Double.parseDouble(entrada.nextLine());
				
				LimpezaDAO lDaoSalario = new LimpezaJPADAO();
				List<Limpeza> limpezaSalario = lDaoSalario.findBySalario(salario);
//				lDaoSalario.close();
				
				if(limpezaSalario == null) {
					System.out.println("Não há Funcionários de Limpeza com esse salario");
				}
				else {
					for(Limpeza limpeza : limpezaSalario) System.out.println(limpeza);
				}
				lDaoSalario.close();
				break;
				
			case 4:
				System.out.println("Entre com jornada de trabalho que você deseja pesquisar");
				int jornada = Integer.parseInt(entrada.nextLine());
				
				LimpezaDAO lDaoJornada = new LimpezaJPADAO();
				List<Limpeza> limpezaJornada = lDaoJornada.findByJornada(jornada);
//				lDaoJornada.close();
				
				if(limpezaJornada == null) {
					System.out.println("Não há Funcionários de Limpeza com esse jornada de trabalho");
				}
				else {
					for(Limpeza limpeza : limpezaJornada) System.out.println(limpeza);
				}
				lDaoJornada.close();
				break;
				
			case 5:
				loop = false;
				break;

			default:
				System.out.println("Opção inválida!");
				break;
			}
		}
	}
	
	public static void Listar() {
		LimpezaDAO lDao = new LimpezaJPADAO();
		List<Limpeza> fLimpeza = lDao.findAll();	
//		lDao.close();
		
		for(Limpeza limpeza : fLimpeza) System.out.println(limpeza);
		
		lDao.close();
	}
	
	public static void Atualizar() {
		int in = 1;
		LimpezaDAO lDao = new LimpezaJPADAO();
		
		System.out.println("Digite o Código do Funcionário de Limpeza a ser atualizado");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		Limpeza limpeza = lDao.find(codigo);
		
		if(limpeza != null) {
			System.out.println("Digite o nome do Funcionário de Limpeza");
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
			System.out.println("Digite o Cargo");
			String cargo = entrada.nextLine();
			System.out.println("Digite a Jornada de Trabalho");
			int jornada = Integer.parseInt(entrada.nextLine());
			System.out.println("Digite o Logradouro");
			String logradouro = entrada.nextLine();
			System.out.println("Digite o Bairro");
			String bairro = entrada.nextLine();
			System.out.println("Digite o Número");
			int numero = Integer.parseInt(entrada.nextLine());
			System.out.println("Digite a Cidade");
			String cidade = entrada.nextLine();
	
			Endereco endereco = new Endereco(logradouro, bairro, numero, cidade);

			limpeza.setNome(nome);
			limpeza.setSexo(sexo);
			limpeza.setDataNascimento(nascimento);
			limpeza.setSalario(salario);
			limpeza.setCargo(cargo);
			limpeza.setJornadaTrabalho(jornada);
			limpeza.setEndereco(endereco);
			
			try {
				lDao.beginTransaction();
				lDao.save(limpeza);
				lDao.commit();
				System.out.println("Alterado com sucesso!");
			} catch (IllegalStateException | PersistenceException e) {
				lDao.rollback();
				e.printStackTrace();
			} finally {
				lDao.close();
			}
		} else System.out.println("Código de Funcionário não encontrado!");
	}
	
	public static void Excluir() {
		System.out.println("Digite o codigo do Funcionário de Limpeza a ser excluído");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		LimpezaDAO lDao = new LimpezaJPADAO();
		try {
			lDao.beginTransaction();
			lDao.deleteById(codigo);
			lDao.close();
			lDao.commit();
		} catch (IllegalStateException | PersistenceException e) {
			lDao.rollback();
			e.printStackTrace();
		} finally {
			lDao.close();
		}
	}
	
	public static void AssociarSuperior() {
		System.out.println("Digite o Id do Funcionário de Limpeza");
		Long idFuncionario = Long.parseLong(entrada.nextLine());
		System.out.println("Digite o Id do Superior de Limpeza");
		Long idSuperior = Long.parseLong(entrada.nextLine());
		
		LimpezaDAO lDaoFuncionario = new LimpezaJPADAO();
		LimpezaDAO lDaoSuperior = new LimpezaJPADAO();
		
		Limpeza lFuncionario = lDaoFuncionario.find(idFuncionario);
		Limpeza lSuperior = lDaoSuperior.find(idSuperior);
		
		if(lFuncionario != null) {
			if(lSuperior != null) {
				lFuncionario.setCodigoIdentificacaoGerente(lSuperior.getCodigoIdentificacao());
				
				try {
					lDaoFuncionario.beginTransaction();
					lDaoFuncionario.save(lFuncionario);
					lDaoFuncionario.commit();
				} catch (IllegalStateException | PersistenceException e) {
					lDaoFuncionario.rollback();
					e.printStackTrace();
				} finally {
					lDaoFuncionario.close();
					lDaoSuperior.close();
				}
				
			} else System.out.println("Id do Superior não encontrado");
		} else System.out.println("Id de funcionário não encontrado!");
	}
}
