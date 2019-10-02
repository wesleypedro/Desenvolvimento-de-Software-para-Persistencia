import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static final String ARQUIVO
		= "/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/Aula02/mySample.csv";
	public static final String ARQUIVO_TRANSPOSTO
	= "/mnt/Data/UFC/2019.2 - DSP/Projetos Aulas/Arquivos/Aula02/arquivoTansposto.csv";

	public static void main(String[] args) {
		List<List> matriz = lerCSV();
		
		transpostaCSV(matriz);
	}
	
	
//	public static void lerCSV() {
	public static List<List> lerCSV(){
		
		try {
			InputStream is = new FileInputStream(ARQUIVO);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			Scanner sc = new Scanner(is);

			List <List> matriz = new ArrayList<>();
			List<String> linhaList = new ArrayList<>();
			
			List<String[]> l = new ArrayList<>();

			while(br.ready()) {
				String s = br.readLine();
				
				String[] linhaVetor = s.split(",");
				linhaList = Arrays.asList(linhaVetor);
				matriz.add(linhaList);
				
				//Outra forma de Armazenar as Listas
				l.add(linhaVetor);
			}
			
			// Outra maneira de Armazenar em matriz
			String [][] mat = l.toArray(new String[][] {});
			
			
			//Imprimir matriz
//			for (List<String> linha : matriz) {
//				for(String elemento : linha) {
//					System.out.print(elemento + " ");
//				}
//				System.out.println();
//			}
			
			is.close();
			
			return matriz;
			
			
			
			
			
			
			//Transposta aqui mesmo salvando no arquivo
//			if(mat.length == 0 || mat[0].length==0) return null;
//			OutputStream os = new FileOutputStream(ARQUIVO_TRANSPOSTO);
//			OutputStreamWriter osw = new OutputStreamWriter(os);
//			BufferedWriter bw = new BufferedWriter(osw);
//			for(int j = 0; j < mat[0].length; j ++) {
//				for(int i = 0; i < mat.length - 1; i ++) {
//					bw.write(mat[i][j] + ",");
//				}
//				bw.write(mat[mat.length-1][j]);
//			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static void transpostaCSV(List<List> matriz) {
		try {
			PrintStream ps = new PrintStream(ARQUIVO_TRANSPOSTO);
			
			int tamanho  = matriz.size();
			int comprimentoLinha = matriz.get(0).size();
			
			List<String> linha = null;
			
			for (int i = 0; i < comprimentoLinha; i ++) {
				for(int j = 0; j < tamanho - 1; j ++) {
					linha = matriz.get(j);
					ps.print(linha.get(i) + ",");
				}
				linha = matriz.get(tamanho - 1);
				ps.print(linha.get(i));
				ps.println();
			}
			
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
