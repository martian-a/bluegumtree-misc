package co.uk.bluegumtree.code.java.util;

import java.util.HashMap;

public class ArgsInterpreter {
	
	public static HashMap<String, String> interpret(String[] args, String[] validOptionSwitches) {
		
		HashMap<String, String> options = new HashMap<String, String>();
	
		// Loop through the arguments
		for (int i = 0; i < args.length; i++) {
	
			String currArg = args[i];
			String nextArg = null;
			if ((i + 1) < args.length) {
				nextArg = args[i + 1];
				if (nextArg.startsWith("-")) {
					nextArg = null;
				}
			}
	
			// Loop through the list of valid switches
			for (String currSwitch : validOptionSwitches) {
	
				// Check whether the switch specified is valid
				if (currArg.equals(currSwitch)) {
	
					// Valid switch, add to the list of options to pass on
					options.put(currArg, nextArg);
				}
			}
		}
		return options;
	}

}
