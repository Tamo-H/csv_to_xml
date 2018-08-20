package com.marlabs.xml_csv;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CsvProducer {

	public void convertToXML(String xmlFile,String xslFile) {
		
		File xmlSource=new File(xmlFile);
		File styleScource=new File(xslFile);
		
		DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
			Document document=documentBuilder.parse(xmlSource);
			StreamSource streamSource=new StreamSource(styleScource);
			
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			Transformer transformer=transformerFactory.newTransformer(streamSource);
			Source source=new DOMSource(document);
//			Result result=new StreamResult(System.out);
			Result result=new StreamResult(new File("C:\\\\Users\\\\himanshu.kumar\\\\Documents\\\\xml_to_csv.csv"));
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
