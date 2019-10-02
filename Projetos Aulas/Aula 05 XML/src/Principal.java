import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Principal {
	
	public static final String ARQUIVO_XML = "/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/Aula05/livros.xml";
	public static final String ARQUIVO_XML_SAIDA = "/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/Aula06/criarLivros.xml";
	public static final String ARQUIVO_XML_CATALOGO_SAIDA = "/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/Aula06/criarCds.xml";

	public static void main(String[] args) {
		parserWithDOM();
//		parserWithSAX();
//		createXML();
//		createXMLCatalogo();
	}
	
	public static void parserWithDOM() {
		ParserDOM parser = new ParserDOM();
		parser.parse(ARQUIVO_XML);
		parser.printRaiz();
	}
	
	public static void parserWithSAX() {
		ParserSAX parser = new ParserSAX();
		parser.parse(ARQUIVO_XML);
	}
	
	public static void createXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element raiz = doc.createElement("livros");
			doc.appendChild(raiz);
			
			Element livro1 = criaLivro("Título 1", 1, "1", "Editora 1", 
										"1ª", new String[] {"Eu",  "Alguém"}, 
										new String[] {"Nada", "Lugar Nenhum"}, doc);

			
			Element livro2 = criaLivro("Título 2", 2, "2", "Editora 2", 
										"2ª", new String[] {"Me",  "Someone"}, 
										new String[] {"Nothing", "Aneone Place"}, doc);

			
			Element livro3 = criaLivro("Título 3", 3, "3", "Editora 3", 
										"1ª", new String[] {"Yo",  "Alguien"}, 
										new String[] {"Nada", "Logradouro Algun"}, doc);
			raiz.appendChild(livro1);
			raiz.appendChild(livro2);
			raiz.appendChild(livro3);
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer optimusPrime = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(ARQUIVO_XML_SAIDA);
			
			optimusPrime.setOutputProperty(OutputKeys.INDENT, "yes");
			optimusPrime.setOutputProperty("{http://xml.apache.org/xskt}ident-amount", "4");
			
			optimusPrime.transform(source, result);
			
		} catch(ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static Element criaLivro(String titulo, int ano,
									String isbn, String editora, 
									String edicao, String[] nomesAutores, 
									String[] enderecosAutores, Document doc) {
		Element livro = doc.createElement("livro");
		Attr attrAno = doc.createAttribute("ano");
		attrAno.setValue(String.valueOf(ano));
		livro.setAttributeNode(attrAno);
		
		Element tagTitulo = doc.createElement("titulo");
		tagTitulo.appendChild(doc.createTextNode(titulo));
		livro.appendChild(tagTitulo);

		Element tagIsbn = doc.createElement("isbn");
		tagIsbn.appendChild(doc.createTextNode(isbn));
		livro.appendChild(tagIsbn);

		Element tagEditora = doc.createElement("editora");
		tagEditora.appendChild(doc.createTextNode(editora));
		livro.appendChild(tagEditora);

		Element tagEdicao = doc.createElement("edicao");
		tagEdicao.appendChild(doc.createTextNode(edicao));
		livro.appendChild(tagEdicao);
		
		Element tagAutores = doc.createElement("autores");
		for(int i = 0; i < nomesAutores.length; i ++) {
			Element tagAutor = doc.createElement("autor");
			Element tagNomeAutor = doc.createElement("nome");
			Element tagEnderecoAutor = doc.createElement("endereco");
			tagNomeAutor.appendChild(doc.createTextNode(nomesAutores[i]));
			tagEnderecoAutor.appendChild(doc.createTextNode(enderecosAutores[i]));
			
			tagAutor.appendChild(tagNomeAutor);
			tagAutor.appendChild(tagEnderecoAutor);
			tagAutores.appendChild(tagAutor);
		}
		livro.appendChild(tagAutores);
		
		return livro;
	}
	
	
	
	// CRIAR XML DOS CDS
	public static void createXMLCatalogo() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element raiz = doc.createElement("catalogo");
			doc.appendChild(raiz);
			
			Element cd1 = criaCd("Título 1", 2011, "Ashuashua", "Cometa",
										new String[] {"Nada", "Lugar Nenhum"}, 10, doc);

			
			Element cd2 = criaCd("Título 2", 2012, "Me", "Não tem", 
										new String[] {"Nothing", "Aneone Place"}, 12.5, doc);

			
			Element cd3 = criaCd("Título 3", 2015, "-(%)-", "Não sei", 
										new String[] {"Nada", "Logradouro Algun"}, 25.99, doc);
			raiz.appendChild(cd1);
			raiz.appendChild(cd2);
			raiz.appendChild(cd3);
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer optimusPrime = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(ARQUIVO_XML_CATALOGO_SAIDA);
			
			optimusPrime.setOutputProperty(OutputKeys.INDENT, "yes");
			optimusPrime.setOutputProperty("{http://xml.apache.org/xskt}ident-amount", "4");
			
			optimusPrime.transform(source, result);
			
		} catch(ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static Element criaCd(String titulo, int ano,
									String artista, String gravadora,
									String[] faixas, 
									double preco, Document doc) {
		Element cd = doc.createElement("cd");
		Attr attrAno = doc.createAttribute("ano");
		attrAno.setValue(String.valueOf(ano));
		cd.setAttributeNode(attrAno);
		
		Element tagTitulo = doc.createElement("titulo");
		tagTitulo.appendChild(doc.createTextNode(titulo));
		cd.appendChild(tagTitulo);

		Element tagArtista = doc.createElement("artista");
		tagArtista.appendChild(doc.createTextNode(artista));
		cd.appendChild(tagArtista);

		Element tagGravadora = doc.createElement("gravadora");
		tagGravadora.appendChild(doc.createTextNode(gravadora));
		cd.appendChild(tagGravadora);

		Element tagFaixas = doc.createElement("faixas");
		for(int i = 0; i < faixas.length; i ++) {
			Element tagFaixa = doc.createElement("faixa");
			tagFaixa.appendChild(doc.createTextNode(faixas[i]));
			
			tagFaixas.appendChild(tagFaixa);
		}
		
		cd.appendChild(tagFaixas);
		
		Element tagPreco = doc.createElement("preco");
		tagPreco.appendChild(doc.createTextNode(String.valueOf(preco)));
		cd.appendChild(tagPreco);
		
		return cd;
	}

}
