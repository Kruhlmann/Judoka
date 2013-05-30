package com.kruhlmann.judoka.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.kruhlmann.judoka.technique.Technique;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class PropertyFileHandler {
	
	public PropertyFileHandler(){
		
	}
	
	public void saveJudoka (Technique[] techniques, String name) throws ParserConfigurationException, IOException, FileNotFoundException{
		DocumentBuilderFactory doc = DocumentBuilderFactory.newInstance();
		doc.setNamespaceAware(true);
		DocumentBuilder docbuilder = doc.newDocumentBuilder();
		Document XMLDocument = docbuilder.newDocument();
		
		Element rootElement = XMLDocument.createElement("Judoka");
		Element mainElement = XMLDocument.createElement("techniques");

		mainElement.appendChild(setOptionAttribute("up", "" + techniques[0].ID, XMLDocument));
		mainElement.appendChild(setOptionAttribute("down", "" + techniques[1].ID, XMLDocument));
		mainElement.appendChild(setOptionAttribute("back", "" + techniques[2].ID, XMLDocument));
		mainElement.appendChild(setOptionAttribute("back-up", "" + techniques[3].ID, XMLDocument));
		mainElement.appendChild(setOptionAttribute("back-down", "" + techniques[4].ID, XMLDocument));
		mainElement.appendChild(setOptionAttribute("forward", "" + techniques[5].ID, XMLDocument));
		mainElement.appendChild(setOptionAttribute("forward-up", "" + techniques[6].ID, XMLDocument));
		mainElement.appendChild(setOptionAttribute("forward-down", "" + techniques[7].ID, XMLDocument));
		
		
		rootElement.appendChild(mainElement);
		XMLDocument.appendChild(rootElement);
		
		OutputFormat outputFormat = new OutputFormat(XMLDocument);
		outputFormat.setIndenting(true);
		
		File xmlFile = new File("res/judokas/" + name + ".xml");
		FileOutputStream outStream = new FileOutputStream(xmlFile);
		XMLSerializer serializer = new XMLSerializer(outStream, outputFormat);
		serializer.serialize(XMLDocument);
	}
	
	private Element setOptionAttribute(String value, String name, Document XMLDocument){
		Element attributeName = XMLDocument.createElement(value);
		Text attributeText = XMLDocument.createTextNode(name);
		attributeName.appendChild(attributeText);
		return attributeName;
	}
	
	public Technique[] getTechniquesFromXML(String tag){
		/*
		try{
			DocumentBuilderFactory doc = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = doc.newDocumentBuilder();
			Document XMLDocument = docbuilder.parse("res/judokas/properties.xml");
			XMLDocument.normalize();
			
			NodeList rootOptions = XMLDocument.getElementsByTagName("options");
			Element rootOption = (Element) rootOptions.item(0);
			
			NodeList options = rootOption.getElementsByTagName("option");
			Element indexElement = (Element) options.item(0);
			return indexElement.getElementsByTagName(tag).item(0).getTextContent();
		}catch (IOException e) {
			System.out.println("[ERROR] COULD NOT PARSE FILE LOCATION TO XML DOCUMENT");
		}catch (ParserConfigurationException e) {
			System.out.println("[ERROR] UNABLE TO CONFIGURE DOCUMENT BUILDER");
		}catch (SAXException e) {
			System.out.println("[ERROR] XML DOCUMENT FORMATTING WAS UNREADABLE");
		}
		return "";
		*/
		return null;
	}
	
}
