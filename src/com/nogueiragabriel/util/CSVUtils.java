package com.nogueiragabriel.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.nogueiragabriel.crawler.Company;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CSVUtils {
	
	String outputFile = "companys.csv";
	
	public void WriteLine(Company company) {
		boolean alreadyExists = new File(outputFile).exists();
		try{
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			
			if (!alreadyExists) {
				csvOutput.write("name");
				csvOutput.write("phone");
				csvOutput.write("adress");
				csvOutput.endRecord();
			}
			
			csvOutput.write(company.getName());
			csvOutput.write(company.getPhone());
			csvOutput.write(company.getAdress());
			csvOutput.endRecord();
			
			csvOutput.close();			
		} catch (IOException ioe){ 
			ioe.printStackTrace();
		}
		
	}
}
