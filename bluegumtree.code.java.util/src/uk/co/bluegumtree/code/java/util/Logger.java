package uk.co.bluegumtree.code.java.util;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class Logger implements ErrorListener {

	@Override
	public void error(TransformerException e) throws TransformerException {
		log(e);
	}

	@Override
	public void fatalError(TransformerException e) throws TransformerException {
		display(e);
	}

	@Override
	public void warning(TransformerException e) throws TransformerException {
		log(e);
	}

	public static void display(Exception e) {
		e.printStackTrace();
	}

	public static void log(Exception e) {
		// e.printStackTrace();
	}

	public static void log(String message) {
		// System.out.println(message);
	}
}
