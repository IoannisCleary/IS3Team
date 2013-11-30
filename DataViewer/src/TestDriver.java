
public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		Model test = new Model(args[0]);
		System.out.println(System.currentTimeMillis() - t1 + " milliseconds");
		System.out.println(test.getTriple().getVariable("isoCode"));
		System.out.println(test.getTriple().getValue("isoCode", "Nicaragua"));
	}

}
