import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Principal {
	
	public static final String EXEMPLO = "https://en.wikipedia.org";
	public static final String EXEMPLOLOCAL = "/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/Aula04/local.html";

	public static void main(String[] args) {
//		exemploConexaoRemotaJsoup();
		exemploConexaoLocalJsoup();
	}

	public static void exemploConexaoRemotaJsoup() {
		try {
			Document doc = Jsoup.connect(EXEMPLOLOCAL).get();
			
			String title = doc.title();
			System.out.println(title);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void exemploConexaoLocalJsoup() {
		try {
			File input = new File(EXEMPLOLOCAL);
			Document doc = Jsoup.parse(input, "UTF-8"/*, "http://example.com/"*/);
			
			String title = doc.title();
			System.out.println(title);
			
//			Elements links = doc.select("a");
			Elements links = doc.select("a[href]");
			for(Element link : links) {
				System.out.println(link.attr("href"));
			}
			
			Elements images = doc.select("img[src$=.png]");
			for(Element image : images) {
				System.out.println(image);
			}
			

			Elements divs = doc.select("div.classe1");
			for(Element div : divs) {
				System.out.println(div);
			}
			

//			Element primeiraDivDaClasse = doc.selectFirst("div.classe1");
			Element primeiraDivDaClasse = doc.select("div.classe1").first();
			Element ultimaDivDaClasse = doc.select("div.classe1").last();
			
			System.out.println(primeiraDivDaClasse);
			System.out.println(ultimaDivDaClasse);
			
			
	//	Exercicio Sala
			{
				System.out.println("\nEXERCÍCIO\n");
				
	//			Elements divExercicio = doc.select("div");
	//			for(Element div : divExercicio) {
	//				if(div.attr("id").equals("div3")) {
	//					System.out.println(div.attr("class"));
	//				}
	//			}
				
				Element div3 = doc.selectFirst("div#div3");
				String classeDiv3 =  div3.attr("class");
				System.out.println(classeDiv3);
			}
			
			
			Elements resultLinks = doc.select("h3 > a");
			for(Element link : resultLinks) {
				System.out.println(link.attr("href"));
			}
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
