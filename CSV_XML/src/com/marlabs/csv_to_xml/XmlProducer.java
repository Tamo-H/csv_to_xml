package com.marlabs.csv_to_xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

import com.marlabs.factory.DocumentFactory;

public class XmlProducer {
	
	public XmlProducer() {
		System.out.println(getClass().getName());
	}

	public int convertFile(String csvFile,String xmlFile,String delimiter)
	{
		int rowCount=-1;
		try
		{
			org.w3c.dom.Document newDoc=DocumentFactory.build().newDocument();
			Element rootElement=newDoc.createElement("XmlRoot");
			newDoc.appendChild(rootElement);
			BufferedReader csvReader;
			csvReader=new BufferedReader(new FileReader(csvFile));
			int fieldCount=0;
			String[] csvFields=null;
			StringTokenizer stringTokenizer=null;
			
			String line=csvReader.readLine();
			if(line!=null) {
				stringTokenizer=new StringTokenizer(line, delimiter);
			    fieldCount=stringTokenizer.countTokens();
			    if(fieldCount>0) {
				    csvFields=new String[fieldCount];
			        int i=0;
			        while(stringTokenizer.hasMoreElements())
				    csvFields[i++]=String.valueOf(stringTokenizer.nextElement());
			    }
			}
			
			while((line=csvReader.readLine())!=null)
			{
				stringTokenizer=new StringTokenizer(line, delimiter);
				fieldCount=stringTokenizer.countTokens();
				if(fieldCount>0)
				{
					Element rowElement=newDoc.createElement("row");
					int i=0;
					while(stringTokenizer.hasMoreElements())
					{
						try {
							String value=String.valueOf(stringTokenizer.nextElement());
							Element element=newDoc.createElement(csvFields[i++]);
							element.appendChild(newDoc.createTextNode(value));
							rowElement.appendChild(element);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					rootElement.appendChild(rowElement);
					rowCount++;
				}
			}
			
			csvReader.close();
			
//---------------------save the file on the disk------------------------------------------------------------
			
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			Transformer transformer=transformerFactory.newTransformer();
			Source source=new DOMSource(newDoc);
			javax.xml.transform.Result result=new StreamResult(new File(xmlFile));
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			transformer.setOutputProperty(OutputKeys.METHOD,"xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
			result = new StreamResult(System.out);
			rowCount++;
			
			
		}
		catch (IOException e) {
			System.err.println(e.toString());
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		
		return rowCount;
		
	}
}
