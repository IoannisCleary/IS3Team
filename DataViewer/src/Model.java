import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Model {
	
	private Triple data;
	private String[] variables;
	
	
	public Model(String fileName){
		data = new Triple();
		process(fileName);
	}
	

	private static Scanner openFile(String path){
		File fd = new File(path);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fd));
			return new Scanner(br);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void parseLine(String line, Triple triple, String[] variables){
		String[] tokens = line.split(",");
		for (int i = 0; i < variables.length; i++){
			String country = tokens[1];					
			for (int j = 0; j < tokens.length; j++){
				if (triple == null){
					triple = new Triple(variables[j],country,tokens[i]);
				} else{
					triple.put(variables[j], country, tokens[j]);
				}
			}
		}
	}
	
	public void process (String file) {
		Scanner scn = openFile(file);
		
		boolean firstLine = false;

		while (scn != null && scn.hasNextLine()){
			String line = scn.nextLine();
			if (!firstLine){
				variables = line.split(",");				
				firstLine = true;
				continue;
			}
			
			parseLine(line, data, variables);
			
		}		
	}
	
	public Triple getTriple(){
		return data;
	}
}
