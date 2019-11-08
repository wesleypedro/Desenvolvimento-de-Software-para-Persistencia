package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.PersistenceException;

import dao.SecretarioDAO;
import dao.jpa.SecretarioJPADAO;
import model.Endereco;
import model.Secretario;

public class GerenciarSecretarios {
	static Scanner entrada = new Scanner(System.in);
	
	public void Gerenciar() {
		int opcao = 1;
		
		while(true) {
			System.out.println(" ___________________________________________________\n"+
							   "|                                                   |\n"+
							   "| Escolha uma opção:                                |\n"+
							   "|___________________________________________________|\n"+
							   "| 1. Cadastrar Secretário                           |\n"+
							   "| 2. Pesquisar Secretário                           |\n"+
							   "| 3. Listar Secretários                             |\n"+
							   "| 4. Atualizar Secretário                           |\n"+
							   "| 5. Excluir Secretários                            |\n"+
							   "| 6. Voltar ao Menu Anterior                        |\n"+
							   "| 7. Sair                                           |\n"+
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
				return;
	
			case 7:
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
		
		System.out.println("Digite o nome do Secretário");
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
		System.out.println("Digite o Grau de Escolaridade");
		String grau = entrada.nextLine();
		System.out.println("Digite o Logradouro");
		String logradouro = entrada.nextLine();
		System.out.println("Digite o Bairro");
		String bairro = entrada.nextLine();
		System.out.println("Digite o Número");
		int numero = Integer.parseInt(entrada.nextLine());
		System.out.println("Digite a Cidade");
		String cidade = entrada.nextLine();
		
		
		System.out.println("Deseja realmente cadastrar o Secretário?\n1. Sim\t2. Não");
		int opcao = Integer.parseInt(entrada.nextLine());
		
		if(opcao == 1) {
			SecretarioDAO sDao = new SecretarioJPADAO();
			
			Endereco endereco = new Endereco(logradouro, bairro, numero, cidade);
			Secretario secretario = new Secretario(nome, sexo, nascimento, salario, endereco, grau);
			
			try {
				sDao.beginTransaction();
				sDao.save(secretario);
				sDao.commit();
				
				System.out.println("Secretario salvo com sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				sDao.rollback();
				e.printStackTrace();
			} finally {
				sDao.close();
			}
		}
	}
	
	public static void Pesquisar() {
		boolean loop = true;
		while(loop) {
			System.out.println("Pesquisar por:\n"
					+ "1. Nome\t2. Grau de Escolaridade\t3. Salario\t4. Cancelar");
			int opcao = Integer.parseInt(entrada.nextLine());
			switch (opcao) {
			case 1:
				System.out.println("Entre com o nome que você deseja pesquisar");
				String nome = entrada.nextLine();
				
				SecretarioDAO sDaoNome = new SecretarioJPADAO();
				List<Secretario> secretarioNome = sDaoNome.findByNome(nome);
//				sDaoNome.close();
				
				if(secretarioNome == null) {
					System.out.println("Não há Secretários com esse Nome");
				}
				else {
					for(Secretario secretario : secretarioNome) System.out.println(secretario);
				}
				sDaoNome.close();
				break;

			case 2:
				System.out.println("Entre com o Grau de Escolaridade que você deseja pesquisar");
				String grau = entrada.nextLine();
				
				SecretarioDAO sDaoGrau = new SecretarioJPADAO();
				List<Secretario> secretarioGrau = sDaoGrau.findByArea(grau);
//				sDaoGrau.close();
				
				if(secretarioGrau == null) {
					System.out.println("Não há Secretários com esse Grau de Escolaridade");
				}
				else {
					for(Secretario secretario : secretarioGrau) System.out.println(secretario);
				}
				sDaoGrau.close();
				break;
				
			case 3:
				System.out.println("Entre com o salario que você deseja pesquisar");
				double salario = Double.parseDouble(entrada.nextLine());
				
				SecretarioDAO sDaoSalario = new SecretarioJPADAO();
				List<Secretario> secretarioSalario = sDaoSalario.findBySalario(salario);
//				sDaoSalario.close();
				
				if(secretarioSalario == null) {
					System.out.println("Não há Secretários com esse Salário");
				}
				else {
					for(Secretario secretario : secretarioSalario) System.out.println(secretario);
				}
				sDaoSalario.close();
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
		SecretarioDAO sDao = new SecretarioJPADAO();
		List<Secretario> secretarios = sDao.findAll();	
//		sDao.close();
		
		for(Secretario secretario : secretarios) System.out.println(secretario);
		
		sDao.close();
	}
	
	public static void Atualizar() {
		int in = 1;
		SecretarioDAO sDao = new SecretarioJPADAO();
		
		System.out.println("Digite o Código do Secretário a ser atualizado");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		Secretario secretario = sDao.find(codigo);
		
		if(secretario != null) {
			System.out.println("Digite o nome do Secretário");
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
			System.out.println("Digite o Grau de Escolaridade");
			String escolaridade = entrada.nextLine();
			System.out.println("Digite o Logradouro");
			String logradouro = entrada.nextLine();
			System.out.println("Digite o Bairro");
			String bairro = entrada.nextLine();
			System.out.println("Digite o Número");
			int numero = Integer.parseInt(entrada.nextLine());
			System.out.println("Digite a Cidade");
			String cidade = entrada.nextLine();
			
			Endereco endereco = new Endereco(logradouro, bairro, numero, cidade);
			
			secretario.setNome(nome);
			secretario.setSexo(sexo);
			secretario.setDataNascimento(nascimento);
			secretario.setSalario(salario);
			secretario.setGrauEscolaridade(escolaridade);
			secretario.setEndereco(endereco);
			
			try {
				sDao.beginTransaction();
				sDao.save(secretario);
				sDao.commit();
				System.out.println("Atualizado com Sucesso!");
			} catch(IllegalStateException | PersistenceException e) {
				sDao.rollback();
				e.printStackTrace();
			} finally {
				sDao.close();
			}
			
		} else System.out.println("Secretário não encontrado com esse código!");
	}
	
	public static void Excluir() {
		System.out.println("Digite o Código do Secretario a ser excluído");
		Long codigo = Long.parseLong(entrada.nextLine());
		
		SecretarioDAO sDao = new SecretarioJPADAO();
		try {
			sDao.beginTransaction();
			sDao.deleteById(codigo);
			sDao.close();
			sDao.commit();
		} catch (IllegalStateException | PersistenceException e) {
			sDao.rollback();
			e.printStackTrace();
		} finally {
			sDao.close();
		}
	}
}
