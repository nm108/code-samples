package src.factorizationtree;

public class FactorizationTreeEntryPoint {

	// entry point.
	
	public static void main(String[] args) {
		boolean useThreshold = false;
		boolean cutBounds = false;
		// long num = 17;

//		NumberFactored n = new NumberFactored(108, useThreshold);
		 NumberFactored n = new NumberFactored(36*36*10, useThreshold);
		// NumberFactored n1 = new NumberFactored(num, useThreshold);
		// NumberFactored n = new NumberFactored(n1);

		System.out.println(n.getGeneralStateAsStringBuilder(cutBounds));
		System.out.println(n.getTreeAsStringBuilder());

		// printDebugInfo();
	}

	// utility method for debugging.
	
	public static void printDebugInfo() {
		NumberFactored n;
		StringBuilder sb = new StringBuilder();
		
		System.out.println(sb.toString());
	}
}
