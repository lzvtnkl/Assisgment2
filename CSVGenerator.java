package Programm;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CSVGenerator {

	public void Generate(ArrayList<Statistic> GenList) throws IOException 
	{
		GenList.sort(Comparator.comparingInt(Statistic::getBytelength));
		FileWriter csvFile = new FileWriter("statistic.csv"); 
		
		csvFile.append("File name,length \n");
		for(Statistic item : GenList)
		{
			csvFile.append(item.getFileName()+ ","+item.getBytelength()+"\n");
		}
		
		csvFile.close();
	}
}
