package src.factorizationtree;

public class FactorizationTreeEntryPoint {

	// entry point.
	
	public static void main(String[] args) {
		boolean useThreshold = true;
		boolean cutBounds = true;
		// long num = 17;

//		NumberFactored n = new NumberFactored(108, useThreshold);
		 NumberFactored n = new NumberFactored(36*101, useThreshold);
		// NumberFactored n1 = new NumberFactored(num, useThreshold);
		// NumberFactored n = new NumberFactored(n1);

		System.out.println(n.getGeneralStateAsStringBuilder(cutBounds));
		System.out.println(n.getTreeAsStringBuilder());

	}

}
