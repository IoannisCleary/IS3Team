import java.util.ArrayList;


public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		Model test = new Model(args[0]);
		System.out.println(System.currentTimeMillis() - t1 + " milliseconds");
		
		ArrayList<String[]> dataArray = test.get2VarDataArray("Factor1", "Factor2", test.getTriple());
		
		/*
		 * The following two lines show how to access the data:
		 * 		1.- how to get all the tuples <country, value> for a certain variable.
		 * 		2.- how to get a certain value for a certain country in a certain variable.
		 */
		 
		System.out.println("This is the variable \"isoCode\": \n" +
				test.getTriple().getVariable("isoCode"));
		System.out.println("\nThis is the ISO name for Nicaragua: " + 
				test.getTriple().getValue("isoCode", "Nicaragua"));
		
		
		System.out.println("\nThese are the arrays to draw the charts:");
		for (int i = 0; i< dataArray.size(); i++){
			System.out.println("[" + dataArray.get(i)[0] + ","
								+ dataArray.get(i)[1] + ","
								+ dataArray.get(i)[2] + ","
								+ dataArray.get(i)[3] + ","
								+ dataArray.get(i)[4] + "]");
		}
	}

}
