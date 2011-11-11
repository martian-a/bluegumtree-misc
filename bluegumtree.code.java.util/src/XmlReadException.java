package uk.co.bluegumtree.code.java.util;

@SuppressWarnings("serial")
public class XmlReadException extends Exception {

	private String filename;

	public XmlReadException(String currFile) {
		this.filename = currFile;
	}

	public String getFile() {
		return this.filename;
	}

}
