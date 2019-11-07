package toJson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Assunto;
import model.Enquadramento_porte;
import model.Enquadramento_potencial;
import model.Licencas;
import model.Municipios;
import model.Porte;
import model.Potencial;
import model.Situacao;
import model.Subtipologia_classe;

public class ToJson extends DefaultHandler{
	private static final String CAMINHO_XML = "/mnt/Data/UFC/2019.2 - DSP/Trabalho 01/Arquivos/XML/licenca.xml";
	private static final String CAMINHO_JSON = "/mnt/Data/UFC/2019.2 - DSP/Trabalho 01/Arquivos/JSON/licencas.json";
	
	static List<Licencas> licencas = new ArrayList<>();
	Licencas licenca = null;
	Assunto assunto = null;
	Municipios municipio = null;
	Subtipologia_classe subtipologia = null;
	Enquadramento_porte Eporte = null;
	Porte porte = null;
	Enquadramento_potencial Epotencial = null;
	Potencial potencial = null;
	Situacao situacao = null;
	
	
	static boolean bidlicenca = false;
	static boolean blicenca = false;
	static boolean bassunto = false;
	static boolean bidProcesso = false;
	static boolean bdata_cadastro = false;
	static boolean bRazao_Social = false;
	static boolean bCNPJ_CPF = false;
	static boolean bidempreendimento = false;
	static boolean bidEmpreendedor = false;
	static boolean bInscricao_estadual = false;
	static boolean bendereco = false;
	static boolean bcep = false;
	static boolean bnumero = false;
	static boolean bBairro = false;
	static boolean bcomplemento = false;
	static boolean bmunicipio = false;
	static boolean bvalidade_licenca = false;
	static boolean bsubtipologia = false;
	static boolean bcodigoclasse = false;
	static boolean benquadramento_porte = false;
	static boolean benquadramento_potencial = false;
	static boolean bemissao_licenca = false;
	static boolean bSituacao = false;
	static boolean bcd_seg = false;
	static boolean bcod_cabure = false;
	static boolean bcaracterizacao = false;
	static boolean bResumo_consulta = false;
	static boolean bResquisitos = false;
	static boolean bobservacao = false;
	static boolean bdespacho = false;
	static boolean bobjetivo_aut = false;
	static boolean bobservacao_aut = false;
	
	//Subtags
	static boolean bCodigoAssunto = false;
	static boolean bDescricaoAssunto = false;
	static boolean bSiglaAssunto = false;
	
	static boolean bcod_ibge = false;
	static boolean bnome_municipio = false;
	
	static boolean bidsituacao = false;
	static boolean bcodigo_subclasse = false;
	static boolean bdesc_classe = false;
	
	static boolean bEporte = false;
	static boolean bporte = false;
	static boolean bdescporte = false;
	
	static boolean bEpotencial = false;
	static boolean bpotencial = false;
	static boolean bdescpotencial = false;
	
	static boolean bidSituacao = false;
	static boolean bsituacao = false;
	
	
	public ToJson(){}
	
	public void executar() {
		parse(CAMINHO_XML);
	}
	
	public void parse(String CAMINHO_XML) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(CAMINHO_XML, this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void startDocument() throws SAXException {
		System.out.println("\tIniciando processamento do XML");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("\tFim do processamento do XML");
		gerarJson(licencas);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("licenca")) {
			licenca = new Licencas();
			licenca.setIdlicenca(attributes.getValue("idlicenca"));
			blicenca = true;
		}
		if (qName.equals("Assunto")) {
			assunto = new Assunto();
			bassunto = true;
		}
		if (qName.equals("idProcesso")) {
			bidProcesso = true;
		}
		if (qName.equals("data_cadastro")) {
			bdata_cadastro = true;
		}
		if (qName.equals("Razao_Social")) {
			bRazao_Social = true;
		}
		if (qName.equals("CNPJ_CPF")) {
			bCNPJ_CPF = true;
		}
		if (qName.equals("idempreendimento")) {
			bidempreendimento = true;
		}
		if (qName.equals("idEmpreendedor")) {
			bidEmpreendedor = true;
		}
		if (qName.equals("Inscriacao_estadual")) {
			bInscricao_estadual = true;
		}
		if (qName.equals("Endereco")) {
			bendereco = true;
		}
		if (qName.equals("cep")) {
			bcep = true;
		}
		if (qName.equals("numero")) {
			bnumero = true;
		}
		if (qName.equals("Bairro")) {
			bBairro = true;
		}
		if (qName.equals("complemento")) {
			bcomplemento = true;
		}
		if (qName.equals("Municipio")) {
			municipio = new Municipios();
			bmunicipio = true;
		}
		if (qName.equals("validade_licenca")) {
			bvalidade_licenca = true;
		}
		if (qName.equals("Subtipologia_classe")) {
			subtipologia = new Subtipologia_classe();
			bsubtipologia = true;
		}
		if (qName.equals("codigoclasse")) {
			bcodigoclasse = true;
		}

		if (qName.equals("emissao_licenca")) {
			bemissao_licenca = true;
		}
		if (qName.equals("Situacao")) {
			situacao = new Situacao();
			bSituacao = true;
		}
		if (qName.equals("cd_seg")) {
			bcd_seg = true;
		}
		if (qName.equals("cod_cabure")) {
			bcod_cabure = true;
		}
		if (qName.equals("Caracterizacao")) {
			bcaracterizacao = true;
		}
		if (qName.equals("Resumo_consulta")) {
			bResumo_consulta = true;
		}
		if (qName.equals("Requisitos")) {
			bResquisitos = true;
		}
		if (qName.equals("observacao")) {
			bobservacao = true;
		}
		if (qName.equals("despacho")) {
			bdespacho = true;
		}
		if (qName.equals("objetivo_aut")) {
			bobjetivo_aut = true;
		}
		if (qName.equals("observacao_aut")) {
			bobservacao_aut = true;
		}
		
		
		if (qName.equals("CodigoAssunto")){
			bCodigoAssunto = true;
		}
		if (qName.equals("DescricaoAssunto")){
			bDescricaoAssunto = true;
		}
		if(qName.equals("SiglaAssunto")){
			bSiglaAssunto = true;
		}
		
		if(qName.equals("cod_ibge")){
			bcod_ibge = true;
		}
		if(qName.equals("nome_municipio")){
			bnome_municipio = true;
		}
		
		if(qName.equals("idsituacao")){
			bidsituacao = true;
		}
		if(qName.equals("codigo_subclasse")){
			bcodigo_subclasse = true;
		}
		if(qName.equals("desc_classe")) {
			bdesc_classe = true;
		}
		
		if(qName.equals("Enquadramento_porte")) {
			Eporte = new Enquadramento_porte(); 
			bEporte = true;
		}
		if(qName.equals("porte")) {
			porte = new Porte();
			bporte = true;
		}
		if(qName.equals("desc_porte")) {
			bdescporte = true;
		}
		
		if(qName.equals("Enquadramento_potencial")) {
			Epotencial = new Enquadramento_potencial();
			bEpotencial = true;
		}
		if(qName.equals("potencial")) {
			potencial = new Potencial();
			bpotencial = true;
		}
		if(qName.equals("desc_potencial")) {
			bdescpotencial = true;
		}
		
		if(qName.equals("idSituacao")) {
			bidSituacao = true;
		}
		if(qName.equals("situacao")) {
			bsituacao = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(blicenca) {
			licencas.add(licenca);
			blicenca = false;
		}
		if(bassunto) {
			licenca.setAssunto(assunto);
			bassunto = false;
		}
		if(bidProcesso) {
			licenca.setIdProcesso(new String(ch, start, length));
			bidProcesso = false;
		}	
		if(bdata_cadastro) {
			licenca.setData_cadastro(new String(ch, start, length));
			bdata_cadastro = false;
		}
		if(bRazao_Social) {
			licenca.setRazao_Social(new String(ch, start, length));
			bRazao_Social = false;
		}
		if(bCNPJ_CPF) {
			licenca.setCNPJ_CPF(new String(ch, start, length));
			bCNPJ_CPF = false;
		}
		if(bidempreendimento) {
			licenca.setIdempreendimento(new String(ch, start, length));
			bidempreendimento = false;
		}
		if(bidEmpreendedor) {
			licenca.setIdEmpreendedor(new String(ch, start, length));
			bidEmpreendedor = false;
		}
		if(bInscricao_estadual) {
			licenca.setInscricao_estadual(new String(ch, start, length));
			bInscricao_estadual = false;
		}
		if(bendereco) {
			licenca.setEndereco(new String(ch, start, length));
			bendereco = false;
		}
		if(bcep) {
			licenca.setCep(new String(ch, start, length));
			bcep = false;
		}
		if(bnumero) {
			licenca.setNumero(new String(ch, start, length));
			bnumero = false;
		}
		if(bBairro) {
			licenca.setBairro(new String(ch, start, length));
			bBairro = false;
		}
		if(bcomplemento) {
			licenca.setComplemento(new String(ch, start, length));
			bcomplemento = false;
		}
		if(bmunicipio) {
			licenca.setMunicipio(municipio);
			bmunicipio = false;
		}
		if(bvalidade_licenca) {
			licenca.setValidade_licenca(new String(ch, start, length));
			bvalidade_licenca = false;
		}
		if(bsubtipologia) {
			licenca.setSubtipologia(subtipologia);
			bsubtipologia = false;
		}
		if(bcodigoclasse) {
			licenca.setCodigoclasse(new String(ch, start, length));
			bcodigoclasse = false;
		}
//		if(benquadramento_porte) {
//			Eporte.setPorte(porte);
//			licenca.setEnquadramento_porte(Eporte);
//			benquadramento_porte = false;
//		}
//		if(benquadramento_potencial) {
//			licenca.setEnquadramento_potencial(Epotencial);
//			benquadramento_potencial = false;
//		}
		if(bemissao_licenca) {
			licenca.setEmissao_licenca(new String(ch, start, length));
			bemissao_licenca = false;
		}
		if(bSituacao) {
			licenca.setSituacao(situacao);
			bSituacao = false;
		}
		if(bcd_seg) {
			licenca.setCd_seg(new String(ch, start, length));
			bcd_seg = false;
		}
		if(bcod_cabure) {
			licenca.setCod_cabure(new String(ch, start, length));
			bcod_cabure = false;
		}
		if(bcaracterizacao) {
			licenca.setCaracterizacao(new String(ch, start, length));
			bcaracterizacao = false;
		}
		if(bResumo_consulta) {
			licenca.setResumo_consulta(new String(ch, start, length));
			bResumo_consulta = false;
		}
		if(bResquisitos) {
			licenca.setResquisitos(new String(ch, start, length));
			bResquisitos = false;
		}
		if(bobservacao) {
			licenca.setObservacao(new String(ch, start, length));
			bobservacao = false;
		}
		if(bdespacho) {
			licenca.setDespacho(new String(ch, start, length));
			bdespacho = false;
		}
		if(bobjetivo_aut) {
			licenca.setObjetivo_aut(new String(ch, start, length));
			bobjetivo_aut = false;
		}
		if(bobservacao_aut) {
			licenca.setObservacao_aut(new String(ch, start, length));
			bobservacao_aut = false;
		}
		
		
		
		
		if (bCodigoAssunto){
			assunto.setCodigoAssunto(new String(ch, start, length));
			bCodigoAssunto = false;
		}
		if (bDescricaoAssunto){
			assunto.setDescricaoAssunto(new String(ch, start, length));
			bDescricaoAssunto = false;
		}
		if(bSiglaAssunto){
			assunto.setSiglaAssunto(new String(ch, start, length));
			bSiglaAssunto = false;
		}
		
		if(bcod_ibge){
			municipio.setCod_ibge(new String(ch, start, length));
			bcod_ibge = false;
		}
		if(bnome_municipio){
			municipio.setNome_municipio(new String(ch, start, length));
			bnome_municipio = false;
		}
		
		if(bidsituacao){
			subtipologia.setIdSituacao(new String(ch, start, length));
			bidsituacao = false;
		}
		if(bcodigo_subclasse){
			subtipologia.setCodigo_subclasse(new String(ch, start, length));
			bcodigo_subclasse = false;
		}
		if(bdesc_classe) {
			subtipologia.setDesc_classe(new String(ch, start, length));
			bdesc_classe = false;
		}
		
		if(bEporte) {
//			licenca.setEnquadramento_porte(Eporte);
			bEporte = false;
		}
		if(bporte) {
			bporte = false;
		}
		if(bdescporte) {
			Eporte.setPorte(porte);
			licenca.setEnquadramento_porte(Eporte);
			porte.setdesc_porte(new String(ch, start, length));
			bdescporte = false;
		}
		
		if(bEpotencial) {
			bpotencial = false;
		}
		if(bpotencial) {
			bpotencial = false;
		}
		if(bdescpotencial) {
			potencial.setdesc_potencial(new String(ch, start, length));
			Epotencial.setPotencial(potencial);
			licenca.setEnquadramento_potencial(Epotencial);
			bdescpotencial = false;
		}
		
		if(bidSituacao) {
			situacao.setIdSituacao(new String(ch, start, length));
			bidSituacao = false;
		}
		if(bsituacao) {
			situacao.setSituacao(new String(ch, start, length));
			bsituacao = false;
		}
	}
	
	
	public static void gerarJson(List<Licencas> licencas) {
		System.out.println("\tSalvando em JSON");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_JSON), licencas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\tObjeto salvo no JSON");
	}
	
	
}
