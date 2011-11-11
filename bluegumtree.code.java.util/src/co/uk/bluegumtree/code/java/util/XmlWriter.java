package co.uk.bluegumtree.code.java.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlWriter {

	public enum FileType {
		DIR, FILE
	}

	public static Element append(Document currDocument, Element parentElement, String childName) {

		// Create the new childElement
		Element childElement = XmlWriter.toElement(currDocument, childName);

		// Append the childElement to the parentElement
		parentElement.appendChild(childElement);

		// Return the new element
		return childElement;
	}

	public static Element append(Document currDocument, Element parentElement, String childName, Object childValueObject) {
		return XmlWriter.append(currDocument, parentElement, childName, childValueObject, false);
	}

	public static Element append(Document currDocument, Element parentElement, String childName, Object childValueObject, boolean ifEmpty) {

		String childValue = "";
		if (childValueObject == null) {
			childValue = null;
		} else {
			childValue = childValueObject.toString();
		}

		// Check whether the childElment should be added if it's null or empty
		// and, if not, whether it actually is null or empty,
		if (ifEmpty == false) {
			
			if (childValue == null || childValue.trim().equals("")) {

				// Null or empty value and the instruction (ifEmpty) is not to add
				// the element when that's the case
				return null;
			}
		}

		// Create the new childElement
		Element childElement = XmlWriter.toElement(currDocument, childName, childValue);

		// Append the childElement to the parentElement
		parentElement.appendChild(childElement);

		// Return the new element
		return childElement;
	}

	/**
	 * Creates an actual file to match the Java file object provided. Also
	 * ensures that all the ancestor directories specified in the path, exist.
	 * 
	 * @param currFile
	 * a Java File object
	 * @param type
	 * a value from the FileType enumeration.
	 * @return true if the file exists or was created; false if not.
	 */
	private static boolean createFile(File currFile, FileType type) {

		File parentFile = currFile.getParentFile();
		// Check that the parent of this file exists
		if (parentFile != null) {
			if (!parentFile.exists()) {
				createFile(parentFile, FileType.DIR);
			}
		}

		// Check that this file exists
		if (!currFile.exists()) {
			try {
				if (type.equals(FileType.DIR)) {
					currFile.mkdir();
				} else {
					currFile.createNewFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		return currFile.exists();
	}

	/**
	 * @return an XML Document node.
	 * @throws ParserConfigurationException
	 */
	public static Document createXmlDocument() throws ParserConfigurationException {

		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document currDocument = documentBuilder.newDocument();

		return currDocument;
	}

	public static Document fromJsonString(String jsonString) throws XmlWriteException {

		try {

			JSONObject o = new JSONObject(jsonString);
			String xmlString = "<response>" + org.json.XML.toString(o) + "</response>";

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));

			Document currDocument = documentBuilder.parse(is);
			return currDocument;

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		throw new XmlWriteException();
	}

	public static Document fromXmlString(String xmlString) throws XmlWriteException {

		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));

			Document currDocument = documentBuilder.parse(is);

			return currDocument;

		} catch (SAXException e) {
			System.err.println("MyLittleError: " + xmlString);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		throw new XmlWriteException();
	}

	/**
	 * Creates an empty Element with the specified name.
	 * 
	 * @param currDocument
	 * the XML Document that will be used to create this Node.
	 * @param name
	 * the name of the Element.
	 * @return an XML Element of the name specified.
	 */
	public static Element toElement(Document currDocument, String name) {

		// Create the element node requested
		return currDocument.createElement(name.toLowerCase());

	}

	/**
	 * Creates an Element with the specified name and populates it with the
	 * value provided.
	 * 
	 * @param currDocument
	 * The XML Document that will be used to create this Node.
	 * @param name
	 * The name of the Element
	 * @param value
	 * The value of the Element
	 * @return the newly created element
	 */
	public static Element toElement(Document currDocument, String name, String value) {

		// Create the element node
		Element currElement = toElement(currDocument, name.toLowerCase());

		if (value == null) {

			// If value is null, convert to empty string
			value = "";

		} else {

			// Otherwise, remove any leading or trailing whitespace
			value = value.trim();

		}

		// Populate the element with the specified value;
		currElement.appendChild(currDocument.createTextNode(value));

		return currElement;
	}

	public static String toString(Document currDocument) {
		
		DOMSource source = new DOMSource(currDocument);
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {

			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		// Create string from XML DOM

		return sw.toString();
	}

	/**
	 * Writes the XML provided to the file location specified.
	 * 
	 * @param currDocument
	 * the XML to be written
	 * @param currFile
	 * the location that the XML is to be saved in.
	 * @return true if the XML is successfully written; false if not.
	 */
	public static boolean write(Document currDocument, File currFile) {

		// Write the content into XML file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {

			transformer = transformerFactory.newTransformer();

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return false;
		}
		DOMSource source = new DOMSource(currDocument);

		try {

			createFile(currFile, FileType.FILE);

			// Create file as new or overwrite existing file
			currFile.createNewFile();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		StreamResult result = new StreamResult(currFile);
		try {

			transformer.transform(source, result);

		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Writes the XML provided to the file location specified.
	 * 
	 * @param currDocument
	 * the XML to be written
	 * @param filename
	 * the name of the file that the XML is to be saved in.
	 * @return true if the XML is successfully written; false if not.
	 */
	public static boolean write(Document currDocument, String filename) {

		// Create the File object
		File xmlFile = new File(filename);

		return XmlWriter.write(currDocument, xmlFile);

	}
}
