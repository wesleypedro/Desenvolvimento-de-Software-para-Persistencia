package main;

import toXml.ToXml;
import toJson.ToJson;

public class Main {

	public static void main(String[] args) {
		executar();
	}

	public static void executar() {
		ToXml xml = new ToXml();
		ToJson json = new ToJson();
		
		System.out.println("=================================\n" +
						   "#          TRABALHO 01          #\n" +
						   "=================================\n");
		
		System.out.println("CONVERSÃO CSV >>> XML");
		xml.executar();
		System.out.println("CONVERSÃO XML >>> JSON");
		json.executar();
	}
	
	public static void testes() {
	}
}