
public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		Model test = new Model(args[0]);
		System.out.println(System.currentTimeMillis() - t1 + " milliseconds");
		
		/*
		 * The following two lines show how to access the data:
		 * 		1.- how to get all the tuples <country, value> for a certain variable.
		 * 		2.- how to get a certain value for a certain country in a certain variable.
		 */
		 
		System.out.println("This is the variable \"isoCode\": \n" +
				test.getTriple().getVariable("isoCode"));
		System.out.println("This is the ISO name for Nicaragua: " + 
				test.getTriple().getValue("isoCode", "Nicaragua"));
	}

}
