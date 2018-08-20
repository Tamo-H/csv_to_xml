package com.marlabs.operations;

import com.marlabs.csv_to_xml.XmlProducer;
import com.marlabs.xml_csv.CsvProducer;

public class OperationClass {
	public static void main(String[] args) {
		XmlProducer xmlProducer=new XmlProducer();
		System.out.println("creating XMl file at specified location . . . .  ");
		xmlProducer.convertFile("C:\\Users\\himanshu.kumar\\Documents\\name.txt", "C:\\Users\\himanshu.kumar\\Documents\\order.xml", ",");
	    System.out.println("xml file is created");
		CsvProducer csvProducer=new CsvProducer();
		System.out.println("creating CSV file from XML data......");
	    csvProducer.convertToXML("C:\\\\Users\\\\himanshu.kumar\\\\Documents\\\\order.xml","C:\\Users\\himanshu.kumar\\Documents\\style.xsl");
	    System.out.println("XML file is created");
	}
}
