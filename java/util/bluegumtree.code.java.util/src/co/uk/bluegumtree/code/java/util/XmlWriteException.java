package co.uk.bluegumtree.code.java.util;

@SuppressWarnings("serial")
public class XmlWriteException extends Exception {

	public XmlWriteException() {
		this("Error writing XML.");
	}
	
	public XmlWriteException(String message) {
		super(message);
	}

}
