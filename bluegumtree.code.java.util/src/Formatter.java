package uk.co.bluegumtree.code.java.util;

public class Formatter {

	public static final String DOUBLE_QUOTE = "\"";

	public static String alignCentre(String content, int cellWidth) {
		while (getCodePointLength(content) < cellWidth) {
			content = " " + content + " ";
		}
		if (getCodePointLength(content) > cellWidth) {
			content = content.substring(0, (cellWidth));
		}
		return content;
	}

	public static String alignLeft(String content, int cellWidth) {
		while (getCodePointLength(content) < cellWidth) {
			content = content + " ";
		}
		return content;
	}

	public static String alignRight(String content, int cellWidth) {
		while (getCodePointLength(content) < cellWidth) {
			content = " " + content;
		}
		return content;
	}

	public static String copy(String original, int multiplier) {
		String copy = "";
		for (int i = 0; i < multiplier; i++) {
			copy = copy + original;
		}
		return copy;
	}

	public static int getCodePointLength(String string) {
		int codePointLength = 0;
		if (string != null) {
			codePointLength = string.codePointCount(0, string.length());
		}
		return codePointLength;
	}

	public static int getLengthOfLongestString(String[] allStrings) {
		int lengthOfLongestString = 0;
		String longestString = "";
		for (String currString : allStrings) {
			if (currString.length() > lengthOfLongestString) {
				lengthOfLongestString = currString.length();
				longestString = currString;
			}
		}
		return getCodePointLength(longestString);
	}

	public static String wrap(String in, String wrapper) {
		return wrap(in, wrapper, wrapper);
	}

	public static String wrap(String in, String open, String close) {
		return open + in + close;
	}
}
