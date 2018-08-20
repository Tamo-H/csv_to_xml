package com.marlabs.factory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

public class DocumentFactory {

	private static DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY=null;
	private static DocumentBuilder DOCUMENT_BUILDER=null;
	
	public static DocumentBuilder build()
	{
		try {    
		    DOCUMENT_BUILDER_FACTORY=DocumentBuilderFactory.newInstance();
			DOCUMENT_BUILDER=DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
		}
		 catch (FactoryConfigurationError exp) {
				System.err.println(exp.toString());
		 }
		catch (ParserConfigurationException e) {
			System.err.println(e.toString());
		}
		catch(Exception e)
		{
			System.err.println(e.toString());
		}
		return DOCUMENT_BUILDER;
	}
}
