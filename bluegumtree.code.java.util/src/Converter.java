package uk.co.bluegumtree.code.java.util;

import java.util.ArrayList;

public class Converter {

	/**
	 * Split the incoming String into smaller parts using the specified
	 * delimiter to decide where to separate the component parts
	 * 
	 * @param in
	 * the String to convert into an array
	 * @param delimiter
	 * the character (or sequence of characters) that separate the component
	 * parts
	 * @return an array containing the component parts of the string
	 */
	public static String[] toArray(String in, String delimiter) {

		// Use the delimiter to split the String into
		// its component parts. If a component is
		// effectively empty, return null instead.
		return in.split(delimiter, -1);
	}

	/**
	 * Split the incoming String into smaller parts using the specified
	 * delimiter to decide where to separate the component parts
	 * 
	 * @param in
	 * the String to convert into an ArrayList
	 * @param delimiter
	 * the character (or sequence of characters) that separate the component
	 * parts
	 * @return an ArrayList containing the component parts of the string
	 */
	public static ArrayList<String> toArrayList(String in, String delimiter) {

		// Break the incoming string down into its component parts
		String[] asArray = Converter.toArray(in, delimiter);

		// Convert it into an ArrayList of Strings
		ArrayList<String> out = new ArrayList<String>();
		for (String currComponent : asArray) {
			out.add(currComponent);
		}

		// Return the resultant ArrayList
		return out;
	}
}
