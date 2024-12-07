package Programm;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args)
	{
		AnalyzerService newAnalyzer = new AnalyzerService(); 
		ArrayList<Statistic> testStatistic = newAnalyzer.AnalyzeDirectory();
		StatisticData(testStatistic);
		
		try
		{
			CSVGenerator testGen = new CSVGenerator(); 
			testGen.Generate(testStatistic); 
		}
		catch (IOException e) 
		{
			System.out.println("Trouble occured during csv file generation");
		}
		
		float [][] testMatrix = newAnalyzer.cosMatrix(testStatistic); 
		ArrayList<Float> maxminV = newAnalyzer.extreamValue(testMatrix);
		System.out.println (" 3 min value" + maxminV.subList(0, 3));
		System.out.println(" 3 max value " + maxminV.subList(maxminV.size() -3, maxminV.size()));
	}
	
	public static void StatisticData(ArrayList<Statistic> testStatistic)
	{
		System.out.println(" Number of files: " + testStatistic.size());
		
		int counter = 0; 
		for (Statistic file : testStatistic )
		{
			if (file.getFileName().endsWith(".java"))
			counter++; 
		}
		 System.out.println(" Number of java files: " + counter); 
		
		int wordlength = 0;  
		for (Statistic file : testStatistic )
		{
			System.out.println("File: " + file.getFileName() + " , length if bytes " + file.getBytelength() + " , length in words " + file.getWordnumber()); 
			System.out.println(" Libraries " + file.getLibraries());
			wordlength += file.getWordnumber(); 
		}
		
	
		double AverageLength = (double)wordlength / testStatistic.size();
		System.out.println(" Average length of words is " + AverageLength);
	}
	
	
	
}
