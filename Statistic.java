package Programm;

import java.util.ArrayList;

public class Statistic {
	
	private String fileName; 
	private int bytelength; 
	private int wordnumber; 
	private ArrayList<String> libraries = new ArrayList<>(); 
	private String savedfile; //content of file
	
	
	public Statistic (String filename, int bytelength, int wordnumber, ArrayList<String> libraries, String savedfile)
	
	{
		this.fileName = filename;
		this.bytelength = bytelength; 
		this.wordnumber = wordnumber; 
		this.libraries = libraries; 
		this.savedfile = savedfile; 
		
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public int getBytelength()
	{
		return bytelength; 
	}
	
	public int getWordnumber()
	{
		return wordnumber; 
	}
	
	public ArrayList<String> getLibraries()
	{
		return libraries; 
	}
	
	public String getSavedfile()
	{
		return savedfile; 
	}

}
