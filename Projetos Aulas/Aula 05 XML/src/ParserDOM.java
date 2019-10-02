import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserDOM {
	
	private Document doc;
	
	public void parse(String filePath) {
		try {
			File xmlFile = new File(filePath);
			DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
			doc.normalize();
			
			System.out.println(doc.getDocumentElement().getNodeName());
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			
			e.printStackTrace();
		}	
	}
	
	
	public void printNo(Node no) {
		if(no.getNodeType() == Node.TEXT_NODE) {
			if(!no.getNodeValue().trim().isEmpty()) {
				System.out.println(no.getNodeValue());
			}
		} else {
			
			System.out.println(no.getNodeName());
			NamedNodeMap attrs = no.getAttributes();
			for(int k=0; k < attrs.getLength(); k ++) {
				Node n = attrs.item(k);
				System.out.println(" " + n.getNodeName() + " ");
				System.out.println(n.getNodeValue());
			}
			System.out.println();
			
			NodeList filhos = no.getChildNodes();
			for(int i = 0; i < filhos.getLength(); i ++) {
				Node filho = filhos.item(i);
				this.printNo(filho);
			}	
		}
	}
	
	public void printRaiz() {
		this.printNo(this.doc.getDocumentElement());
	}
	
}
