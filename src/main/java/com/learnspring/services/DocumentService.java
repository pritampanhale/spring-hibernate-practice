package com.learnspring.services;

import org.springframework.stereotype.Service;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.StringWriter;

@Service
public class DocumentService {

	public String getTag(String fileName, String id, String author) {
		String changeSet = "";
		try {

			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("changeSet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				String textContentAthor = nNode.getAttributes().getNamedItem("author").getTextContent();
				String textContentId = nNode.getAttributes().getNamedItem("id").getTextContent();

				if (nNode.getNodeType() == Node.ELEMENT_NODE && textContentId.equals(id)
						&& textContentAthor.equals(author)) {

					NodeList childNodes = nNode.getChildNodes();
					changeSet = getInsideNodes(childNodes);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return changeSet;
	}
	
	private String getInsideNodes(NodeList childNodes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node n = childNodes.item(i);
			sb.append(nodeToString(n));
		}
		
		return sb.toString();
	}
	
	private String nodeToString(Node node) {
		  StringWriter sw = new StringWriter();
		  try {
		    Transformer t = TransformerFactory.newInstance().newTransformer();
		    t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    t.transform(new DOMSource(node), new StreamResult(sw));
		  } catch (TransformerException te) {
		    System.out.println("nodeToString Transformer Exception");
		  }
		  return sw.toString();
		}

}
