package PersistenciaAula01Arquivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

public class Principal {

	public static final String ARQUIVO
		= "/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/Aula01/teste.txt";

	public static void main(String[] args) {
		
		try {
			
			// Demais conteúdos das aulas usando funções para criar e salvar

			
//			exemplo10Properties();
//			exemplo11SalvarProperties();
//			exemplo12LerProperties();
			exemplo13LendoZip();
			
			
			
			
			
	//	USANDO INPUTSTREAM, INPUTSTREAMREADER E BUFFERREADER
	//	NA LEITURA DE ARQUIVOS COM BYTES, LINHAS USANDO O BUFFER
			
//			InputStream is = new FileInputStream(ARQUIVO);
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);

//			int b = is.read();
			
//			char buffer[] = new char[5];
//			isr.read(buffer);
			
//			String s = new String(buffer);
			
//			while(br.ready()) {
//				String s = br.readLine();
//				
//				System.out.println(s);
//			}

//			is.close();
//			isr.close();
//			br.close();
	
	

	//	LENDO ARQUIVOS USANDO SCANNER
			
//			Scanner sc = new Scanner(is);
//			while(sc.hasNextLine()) {
//				String s = sc.next();
//				System.out.println(s);
//			}
//			
//			is.close();
			
			
			
			
	//	LENDO DO TECLADO USANDO BUFFER
			
//			InputStream is = System.in;
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			
//			String s = br.readLine();
//			while(!s.isEmpty()) {
//				System.out.println(s);
//				s = br.readLine();
//			}
//			
//			is.close();
			
			
			
			
	//	ESCREVENDO EM ARQUIVOS
//			OutputStream os = new FileOutputStream(ARQUIVO);
//			OutputStreamWriter osw = new OutputStreamWriter(os);
//			BufferedWriter bw = new BufferedWriter(osw);
//			
////			bw.write("Escrevi num arquivo");
////			bw.newLine();
//			
//			
//			// Escrevendo no arquivo o que foi digitado no teclado
//			// Parado quando a entrada é nula
//			InputStream is = System.in;
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			
//			String s = br.readLine();
//			while(!s.isEmpty()) {
//				bw.write(s);
//				bw.newLine();
//				s = br.readLine();
//			}
//			
//			bw.close();
			
			
	
	//	SALVANDO EM ARQUIVO USANDO PRINTSTREAM		
			
//			PrintStream ps = new PrintStream(ARQUIVO);
//			ps.println("Igual acima mas usando print");
//			
//			ps.close();
			
			
	//	LENDO DO TECLADO E SALVANDO EM ARQUIVO USANDO PRINTSTREAM
			Scanner sc = new Scanner(System.in);
			PrintStream ps = new PrintStream(ARQUIVO);
			String s = sc.nextLine();
			while(!s.isEmpty()) {
				ps.println(s);
				s = sc.nextLine();
			}
			
			ps.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void exemplo13LendoZip() throws IOException{
		InputStream is = new FileInputStream("/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/teste.zip");
		ZipInputStream zis = new ZipInputStream(is);
		zis.getNextEntry();
		InputStreamReader isr = new InputStreamReader(zis);
		BufferedReader br = new BufferedReader(isr);
		
		while(zis.getNextEntry() != null) {	//PAssa por cada arquivo do zip
			while(br.ready()) System.out.println(br.readLine());  // Passa por cada linha de cada arquivo
		}
		
		br.close();
	}
	
	
	public static void exemplo12LerProperties() throws IOException{
		Properties prop = new Properties();
		
		prop.load(new FileInputStream(ARQUIVO));
		
		String aluno = prop.getProperty("aluno");
		String faculdade = prop.getProperty("faculdade");
		double salario = Double.parseDouble(prop.getProperty("salario"));
		
		System.out.println(aluno);
		System.out.println(faculdade);
		System.out.println(salario);
	}
	
	
	public static void exemplo11SalvarProperties() throws IOException{
		Properties prop = new Properties();
		prop.setProperty("aluno", "alguem");
		prop.setProperty("faculdade", "ufc");
		prop.setProperty("salario", "10000");
		
		prop.store(new FileOutputStream(ARQUIVO), null);
	}
	
	public static void exemplo10Properties() throws IOException{
		Properties prop = new Properties();
		prop.setProperty("aluno", "alguem");
		prop.setProperty("faculdade", "ufc");
		prop.setProperty("salario", "10000");
		
		String aluno = prop.getProperty("aluno");
		String faculdade = prop.getProperty("faculdade");
		double salario = Double.parseDouble(prop.getProperty("salario"));

		System.out.println(aluno);
		System.out.println(faculdade);
		System.out.println(salario);
	}
	
	public static void exemplo9TryWithResources() throws IOException{
		InputStream is = new FileInputStream(ARQUIVO);
		InputStreamReader isr = new InputStreamReader(is);
		
		try (BufferedReader br = new BufferedReader(isr)) {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
