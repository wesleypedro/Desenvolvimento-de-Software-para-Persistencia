package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.DependentesDAO;
import dao.FuncionarioDAO;
import dao.jpa.DependentesJPADAO;
import dao.jpa.FuncionarioJPADAO;
import model.Dependentes;
import model.Funcionario;

public class GerenciarDependentes {
	static Scanner entrada = new Scanner(System.in);
	
	public void Gerenciar() {
		int opcao = 1;
		
		while(true) {
			System.out.println(" ___________________________________________________\n"
					  		  +"|                                                   |\n"
							  +"| Escolha uma opção:                                |\n"
							  +"|___________________________________________________|\n"
							  +"| 1. Cadastrar Dependentes                          |\n"
							  +"| 2. Pesquisar Dependentes                          |\n"
							  +"| 3. Listar Dependentes                             |\n"
							  +"| 4. Atualizar Dependentes                          |\n"
							  +"| 5. Excluir Dependentes                            |\n"
							  +"| 6. Associar a Funcionario                         |\n"
							  +"| 7. Voltar ao Menu Anterior                        |\n"
							  +"| 8. Sair                                           |\n"
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
				AssociarFuncionario();
				break;
	
			case 7:
				return;
	
			case 8:
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
		int in = 1;
		
		System.out.println("Digite o nome do Dependente");
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
		System.out.println("Digite o Grau de Parentesco");
		String grau = entrada.nextLine();
		
		System.out.println("Deseja realmente cadastrar o Dependente?\n1. Sim\t2. Não");
		int opcao = Integer.parseInt(entrada.nextLine());
		
		if(opcao == 1) {
			DependentesDAO dDao = new DependentesJPADAO();
			
			Dependentes dependentes = new Dependentes(nome, sexo, nascimento, grau);			
			
			try {
				dDao.beginTransaction();
				dDao.save(dependentes);
				dDao.commit();
				
				System.out.println("Dependente salvo com sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				dDao.rollback();
				e.printStackTrace();
			} finally {
				dDao.close();
			}
		} else if(opcao == 2) {
			System.out.println("Cancelado!");
		}
	}
	
	public static void Pesquisar() {
		DependentesDAO dDao = new DependentesJPADAO();
		
		System.out.println("Digite o nome pelo qual você deseja pesquisar");
		String nome = entrada.nextLine();
		
		List<Dependentes> dependentes = dDao.findByNome(nome);
		
		for(Dependentes dependente : dependentes) System.out.println(dependente);
		
		dDao.close();
	}
	
	public static void Listar() {
		DependentesDAO dDao = new DependentesJPADAO();
		List<Dependentes> dependentes = dDao.findAll();
		
		for(Dependentes dependente : dependentes) System.out.println(dependente);	
		
		dDao.close();
	}
	
	public static void Atualizar() {
		int in = 1;
		
		System.out.println("Entre com o Id do Dependente a ser alterado");
		Long id = Long.parseLong(entrada.nextLine());
		
		DependentesDAO dDao = new DependentesJPADAO();

		Dependentes dependente = dDao.find(id);
		
		if(dependente != null) {
			System.out.println("Digite o nome do Dependente");
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
			System.out.println("Digite o grau de Parentesco");
			String parentesco = entrada.nextLine();
			
			dependente.setNome(nome);
			dependente.setSexo(sexo);
			dependente.setDataAniversario(nascimento);
			dependente.setGrauParentesco(parentesco);
			
			try {
				dDao.beginTransaction();
				dDao.save(dependente);
				dDao.commit();
				
			} catch (IllegalStateException | PersistenceException e) {
				dDao.rollback();
				e.printStackTrace();
			} finally {
				dDao.close();
			}
		} else System.out.println("Id do Dependente não encontrado!");
	}
	
	public static void Excluir() {
		System.out.println("Digite o Id do Parente a ser excluído");
		Long id = Long.parseLong(entrada.nextLine());
		
		DependentesDAO dDao = new DependentesJPADAO();

		Dependentes dependente = dDao.find(id);
		
		if(dependente != null) {
			try {
				dDao.beginTransaction();
				dDao.deleteById(id);
				dDao.close();
				dDao.commit();
				System.out.println("Deletado com sucesso!");
			} catch (IllegalStateException | PersistenceException e) {
				dDao.rollback();
				e.printStackTrace();
			} finally {
				dDao.close();
			}
		} else System.out.println("Id de Dependente não encontrado!");
	}
	
	public static void AssociarFuncionario() {	
		System.out.println("Digite o Id do Dependente");
		Long idDependente = Long.parseLong(entrada.nextLine());
		System.out.println("Digite o Código de Identificação do Funcionário");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		DependentesDAO dDao = new DependentesJPADAO();
		FuncionarioDAO fDao = new FuncionarioJPADAO();
		
		Funcionario funcionario = fDao.find(codigo);
		Dependentes dependente = dDao.find(idDependente);
		if(funcionario != null) {
			if(dependente != null) {
				dependente.setFuncionario(funcionario);
				funcionario.setAddDependentes(dependente);
				
				try {
					fDao.beginTransaction();
					fDao.save(funcionario);
					fDao.commit();
				} catch (IllegalStateException | PersistenceException e) {
					fDao.rollback();
					e.printStackTrace();
				} finally {
					fDao.close();
					dDao.close();
				}
			} else System.out.println("Dependente não encontrado com esse Id");
		} else System.out.println("Funcionário não encontrado com esse código");
	}
	
	public static List<Dependentes> CadastrarDependentes(){
		
		int in = 1, adicionar = 1;
		List<Dependentes> dependentes = new ArrayList<>();
		
		while(true) {
			System.out.println("Deseja adicionar um Dependente?\n1. Sim\t2. Não\t3. Cancelar");
			adicionar = Integer.parseInt(entrada.nextLine());
			if(adicionar == 1) {
				System.out.println("Digite o nome do Dependente");
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
				System.out.println("Digite o Grau de Parentesco");
				String grau = entrada.nextLine();
				
				System.out.println("Deseja realmente adicionar o Dependente?\n1. Sim\t2. Não");
				int opcao = Integer.parseInt(entrada.nextLine());
				if(opcao == 1) {
					Dependentes dependente = new Dependentes(nome, sexo, nascimento, grau);
					dependentes.add(dependente);
					in = 1;
					System.out.println("Dependente adicionado!");
				} else System.out.println("Dependente não adicionado!");
				
			} 
			else if(adicionar == 2) {
				System.out.println("Adicionando dependentes ao funcionário");
				return dependentes;
			} 
			else if(adicionar == 3) {
				System.out.println("Cancelado!\nNenhum dependente será adicionado.");
				break;
			}
			else System.out.println("Opção inválida");
		}
		
		return null;
	}
}
