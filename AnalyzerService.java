package Programm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalyzerService {

	public ArrayList AnalyzeDirectory ()
	{
		ArrayList<Statistic> Statistics = new ArrayList<>(); 
		File directory = new File("/Users/vetta/javaconferences.github.io-main");
		
		if ( directory.exists() && directory.isDirectory())
		{
		  traverseDirectory( directory, Statistics);
		}
		else 
		{
			System.out.println(" Directory or file is not found ");
		}
		
		
		return Statistics;
		
	}
	
	public void traverseDirectory(File directory, ArrayList<Statistic> Statistics) 
	{
		for ( File file : directory.listFiles())
		{
			if( file.isDirectory())
			{
				traverseDirectory( file , Statistics);
			}
			else if (file.isFile())
			{
			try
			{
			Statistic currentFileStatistic	=  analyzefile(file); 
			Statistics.add(currentFileStatistic);
			}
			catch(IOException e)
			{
				System.out.println("Error during file reading: " + e);
			}
			}
		}
	}

	private Statistic analyzefile(File file) throws IOException 
	{
		
		String fileName = file.getName(); 
		String content = Files.readString(file.toPath());
		int bytelength = content.getBytes().length; 
		int lengthInWords = content.replaceAll("(?<!http:|https:)//.*|/\\*(.|\\R)*?\\*/", "").split("\\s+").length;
		List<String> libraries = extractLibraries (content); 
		Statistic currentFileStatistic = new Statistic( fileName, bytelength, lengthInWords, (ArrayList<String>) libraries, content); 
		return currentFileStatistic;
	}
	
	private List<String> extractLibraries(String content) {
        return Arrays.stream(content.split("\\n"))    								//split content to array of string
                .filter(line -> line.trim().startsWith("import "))					//select string of array 
                .map(line -> line.replace("import", "").replace(";", "").trim())	//remove import and semicolumn from line
                .collect(Collectors.toList());
    }
	
	public float[][] cosMatrix(ArrayList<Statistic> liststatistic)
	{
		int matrixsize = liststatistic.size(); 
		float [][] matrix = new float [matrixsize][matrixsize]; 
		
		for (int i = 0; i < matrixsize; i++)
		{
			for (int j = 0; j < matrixsize; j++)
			{
				matrix[i][j] = -1;
				
			}
		}
		
		for (int i = 0; i < matrixsize; i++)
		{
			for (int j = i+1; j < matrixsize; j++)
			{
				matrix[i][j] = calculateCosin(liststatistic.get(i).getSavedfile(), liststatistic.get(j).getSavedfile());
				
			}
		}
		
		return matrix; 
	}
	public ArrayList<Float> extreamValue(float [][] matrix)
	{
		int matrixsize = matrix.length; 
		
		ArrayList<Float> maxminV = new ArrayList<>();
		
		for (int i = 0; i < matrixsize; i++)
		{
			for (int j = i+1; j < matrixsize; j++)
			{
				maxminV.add(matrix[i][j]);
			}
		}
		Collections.sort(maxminV);
		return maxminV;
	}
	
	
	
	private float calculateCosin(String savedfile, String savedfile2) 
	{
		
		Map<String, Integer> frequence1 = wordFrequency(savedfile);  
		Map<String, Integer> frequence2 = wordFrequency(savedfile2); 
		Set<String> allWords = new HashSet<>(frequence1.keySet());
		allWords.addAll(frequence2.keySet()); 
		
		int AB = 0, A = 0, B = 0; 
		
		for(String word : allWords)
		{
			int a = frequence1.getOrDefault(word, 0);
			int b = frequence2.getOrDefault(word, 0);
			AB += a * b; 
			A += a*a; 
			B += b*b; 
		}
		
		float cos = (float)(AB/(Math.sqrt(A) * Math.sqrt(B))); 
		return cos;
	
	}
	
	private Map<String, Integer> wordFrequency(String text) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : text.split("\\s+")) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        return freqMap;
    }
	
	
	
}
