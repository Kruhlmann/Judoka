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

		rootElement.appendChild(setOptionAttribute("up", "" + techniques[0].ID, XMLDocument));
		rootElement.appendChild(setOptionAttribute("down", "" + techniques[1].ID, XMLDocument));
		rootElement.appendChild(setOptionAttribute("back", "" + techniques[2].ID, XMLDocument));
		rootElement.appendChild(setOptionAttribute("back-up", "" + techniques[3].ID, XMLDocument));
		rootElement.appendChild(setOptionAttribute("back-down", "" + techniques[4].ID, XMLDocument));
		rootElement.appendChild(setOptionAttribute("forward", "" + techniques[5].ID, XMLDocument));
		rootElement.appendChild(setOptionAttribute("forward-up", "" + techniques[6].ID, XMLDocument));
		rootElement.appendChild(setOptionAttribute("forward-down", "" + techniques[7].ID, XMLDocument));
		
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
	
	public Technique[] loadPlayerTechniques(String name) throws SAXException, IOException, ParserConfigurationException{
		Technique[] techniques = new Technique[8];
		String id = null;
		Document doc;
		DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder docBuilder = docBuildFact.newDocumentBuilder();
		doc = docBuilder.parse("res/judokas/" + name + ".xml");
		Element docElement = doc.getDocumentElement();
		
		id = getTextValue(id, docElement, "up");
		techniques[0] = Technique.getTechnique(Integer.parseInt(id));

		id = getTextValue(id, docElement, "down");
		techniques[1] = Technique.getTechnique(Integer.parseInt(id));

		id = getTextValue(id, docElement, "back");
		techniques[2] = Technique.getTechnique(Integer.parseInt(id));

		id = getTextValue(id, docElement, "back-up");
		techniques[3] = Technique.getTechnique(Integer.parseInt(id));

		id = getTextValue(id, docElement, "back-down");
		techniques[4] = Technique.getTechnique(Integer.parseInt(id));

		id = getTextValue(id, docElement, "forward");
		techniques[5] = Technique.getTechnique(Integer.parseInt(id));

		id = getTextValue(id, docElement, "forward-up");
		techniques[6] = Technique.getTechnique(Integer.parseInt(id));

		id = getTextValue(id, docElement, "forward-down");
		techniques[7] = Technique.getTechnique(Integer.parseInt(id));
		
		return techniques;
	}
	
	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}
	
}
