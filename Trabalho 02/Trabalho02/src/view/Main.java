package view;

import java.util.Scanner;

import controller.GerenciarDepartamentos;
import controller.GerenciarDependentes;
import controller.GerenciarLimpeza;
import controller.GerenciarPesquisadores;
import controller.GerenciarProjetos;
import controller.GerenciarSecretarios;

public class Main {
	public static void main(String [] args) {
		Scanner entrada = new Scanner(System.in);
		
		GerenciarPesquisadores gPesquisador = new GerenciarPesquisadores();
		GerenciarSecretarios gSecretarios = new GerenciarSecretarios();
		GerenciarLimpeza gLimpeza = new GerenciarLimpeza();
		GerenciarDependentes gDependentes = new GerenciarDependentes();
		GerenciarDepartamentos gDepartamentos = new GerenciarDepartamentos();
		GerenciarProjetos gProjetos = new GerenciarProjetos();
		
		int opcao = 1;
		
		while(opcao != 0) {
			System.out.println(" ___________________________________________________\n"
							  +"|                                                   |\n"
							  +"| Escolha uma opção:                                |\n"
							  +"|___________________________________________________|\n"
							  +"| 1. Gerênciar Pesquisadores                        |\n"
							  +"| 2. Gerênciar Secretários                          |\n"
							  +"| 3. Gerênciar Funcionários de Limpeza              |\n"
							  +"| 4. Gerênciar Dependentes                          |\n"
							  +"| 5. Gerênciar Departamentos                        |\n"
							  +"| 6. Gerênciar Projetos                             |\n"
							  +"| 7. Sair                                           |\n"
							  +"|___________________________________________________|");
			opcao = Integer.parseInt(entrada.nextLine());
			
			switch (opcao) {
			case 1:
				gPesquisador.Gerenciar();
				break;

			case 2:
				gSecretarios.Gerenciar();
				break;

			case 3:
				gLimpeza.Gerenciar();
				break;

			case 4:
				gDependentes.Gerenciar();
				break;

			case 5:
				gDepartamentos.Gerenciar();
				break;

			case 6:
				gProjetos.Gerenciar();
				break;

			case 7:
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
}
