import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;


/**
 * Stub class for the model. At the moment it opens a .csv file and stores its content in memory.
 * It also has a couple of methods to access the data from those classes that instantiate a Model
 * object.
 * 
 * @author Georgios Moleski and Mario Moro Hernández
 *
 */
public class Model {
	
	private Triple data;
	private String[] variables;
	
	
	
	/**
	 * Parameterised constructor. Given a file name, it creates a Triple type data structure  
	 * @param fileName
	 */
	public Model(String fileName){
		data = new Triple();
		process(fileName);
	}
	
	
	
	/**
	 * Auxiliary method to open the file specified as a parameter in the process method.
	 * @param path string representing the name of the .csv file containing the data
	 * @return a scanner for the content of the file; null if the file is not found.
	 */
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
	
	
	
	/**
	 * Auxiliary method to insert the data in the .csv file into the Triple data structure.
	 *  
	 * @param line
	 * @param triple
	 * @param variables
	 */
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
	
	
	
	/**
	 * Process the data contained in the .csv file passed as a parameter. This method
	 * is to be called from the File->Open dialog box in the GUI.
	 * @param file .csv file containing the dataset.
	 */
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
	
	
	
	/**
	 * Accessor method to the Triple data structure containing the dataset. 
	 * @return the Triple structure containing the dataset
	 */
	public Triple getTriple(){
		return data;
	}
	
	
	
	/**
	 * It returns an array list of arrays of the form [country, xValue, yValue, xLabel, yLabel]
	 * to be used in the charts generation.
	 * 
	 * @param xVar variable on x-axis
	 * @param yVar variable on y-axis
	 * @param triple data structure containing the dataset
	 * @return an array list of arrays [country, xValue, yValue, xLabel, yLabel]
	 */
	public ArrayList<String[]> get2VarDataArray(String xVar, String yVar, Triple triple){
		
		ArrayList<String[]> toReturn = new ArrayList<String[]>(triple.size());
		
		Iterator<String> it = triple.getVariable(xVar).keySet().iterator();
				
		while (it.hasNext()){	
			String[] datum = new String[5];
			String country = it.next(); 
			datum[0] = country;//country
			datum[1] = triple.getValue(xVar, country);//xval
			datum[2] = triple.getValue(yVar, country);
			datum[3] = xVar;
			datum[4] = yVar;
			toReturn.add(datum);
		}
		
		return toReturn;
	}
}
