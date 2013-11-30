import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * The class Triple holds the data structure to store the data retrieved from the
 * .csv file. It is a HashMap with key String and value HashMap<String, String>.
 * The key stores the full name of the variable and the HashMap contains a mapping
 * of all the countries and their values for the variable stored as the key.
 * 
 * @author Mario Moro Hernández (1106740m)
 *
 */
public class Triple implements Comparable<Triple>{

	private HashMap<String, HashMap<String, String>> triple;
	private String label;

	/**
	 * Default constructor
	 */
	public Triple(){
		triple = new HashMap<String, HashMap<String, String>>(); 
	}



	/**
	 * Triple constructor. It creates a new triple containing the
	 * variable's name and an entry to the hashmap containing 
	 * the country and the value for that variable hold by that
	 * country. E.g. given variable "Factor1", it stores: <pr>
	 * 
	 * &ltMortality, (Afghanistan, 85)&gt. <p>
	 *
	 * If the triple already exists, then it only adds a new &ltcountry,value&gt 
	 * entry to the variable hashmap.
	 *
	 * @param label String containing the variable name to be displayed to the user as key. 		  
	 * @param country String containing the country's name.
	 * @param value String containing the value of country for variable denoted by label.
	 */
	public Triple(String label, String country, String value){
		HashMap<String, String> temp;

		if (triple == null){
			temp = new HashMap<String, String>(500);
			triple = new HashMap<String, HashMap<String, String>>(500);
			this.label = label; 
		}

		temp = triple.get(label);

		if (temp == null) {
			temp = new HashMap<String, String>(500);
		}

		temp.put(country, value);
		triple.put(label,temp);
	}

	
	
	public boolean isEmpty(){
		return triple.isEmpty();
	}
	
	
	
	public int size(){
		return triple.size();
	}
	
	
	
	/**
	 * Inserts a new tuple &ltcountry, value&gt for a specified variable.
	 * 
	 * @param label the variable that is extended
	 * @param country key for the new tuple inserted
	 * @param value new value to be inserted
	 */
	public void put(String label, String country, String value){
		HashMap<String, String> temp = triple.get(label);
		if (temp == null) {
			temp = new HashMap<String, String>(500);
		}		
		temp.put(country, value);
		triple.put(label, temp);
	}



	/**
	 * Retrieves the value of an specified variable for the specified country.
	 * @param label variable to be retrieved 
	 * @param country country for which the variable value is retrieved
	 * @return the value of the specified variable for the specified country 
	 */
	public String getValue(String label, String country){
		return triple.get(label).get(country);
	}



	/**
	 * Retrieves the set of countries and values associated to a specified variable.
	 * @param label variable to be retrieved
	 * @return the set of countries and values associated to them for the specified variable
	 */
	public HashMap<String, String> getVariable(String label){
		return triple.get(label);
	}



	/**
	 * Changes the name of the label of a triple. 
	 * @param oldLabel
	 * @param newLabel
	 */
	public void changeLabel(String oldLabel, String newLabel){
		HashMap<String, String> temp = triple.get(oldLabel);
		triple.put(newLabel, temp);
		triple.remove(oldLabel);
	}



	/**
	 * Iterator returns an iterator on the Triple object. It iterates over the labels of the
	 * triple.
	 * @return an Iterator&ltTriple&gt object.
	 */
	public Iterator<String> iterator(){
		return triple.keySet().iterator();
	}



	/**
	 * Compares two Triple object based upon their labels.
	 * @param o a Triple object
	 * @return <li> a negative integer if this.label is lexicographically less than o.label</li>
	 * 		   <li> 0 if both Triple objects have the same label.</li>
	 * 		   <li> a positive integer if this.label is lexicographically greater than o.label</li>
	 */
	@Override
	public int compareTo(Triple o) {
		return this.label.compareTo(o.label);
	}

	
	/**
	 *
	 */
	public String toString(){
		String toReturn = "";
		for (String label: triple.keySet()){
			toReturn += label + ": " + triple.get(label).toString() + "\n";
		}
		return toReturn;
	}
}
