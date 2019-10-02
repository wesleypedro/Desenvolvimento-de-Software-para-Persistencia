import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Contato;

public class Principal {

	public static final String ARQUIVO_JSON = "arquivos/contatos.json";

	public static void main(String[] args) {
		// salvarEmJSON();
		lerDoJSON();
		/*
		EXERCÍCIO:
		- Crie uma classe Endereço com 
			rua (String), bairro (String) e número (int).
		- Na cla sse Contato, adicione um atributo endereço (Endereco).
		- Na classe Contato adicione também um atributo
		 	telofones (List<String>)
		- Na main, crie contatos preenchendo todas as informações, 
			salve como json depois leia de volta.
		 
		*/
		
		
		System.out.println("FIM");
	}
	
	public static void lerDoJSON() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Contato>> mapType 
					= new TypeReference<List<Contato>>(){};
		try {
			List<Contato> contatos 
				= mapper.readValue(new File(ARQUIVO_JSON), mapType);
			for(Contato contato : contatos) {
				System.out.println(contato);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void salvarEmJSON() {
		List<Contato> contatos = new ArrayList<Contato>();
		contatos.add(new Contato(1, "Mauricio", "mauricio@ufc.br"));
		contatos.add(new Contato(2, "Mary", "mary@ufc.br"));
		contatos.add(new Contato(3, "Kely", "kely@ufc.br"));

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(ARQUIVO_JSON), contatos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}












