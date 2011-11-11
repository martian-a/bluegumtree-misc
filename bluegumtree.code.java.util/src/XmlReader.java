package uk.co.bluegumtree.code.java.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReader {

	public static Document read(File currFile) throws XmlReadException, IOException {
		return XmlReader.read(currFile.getCanonicalPath());
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result in the format specified.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @param resultFormat
	 * the format that's expected to be returned.
	 * @return the result of the query.
	 */
	public static Object read(Object currElement, String currExpression, QName resultFormat) {

		if (currElement == null) {
			return null;
		}

		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath currXPath = xPathFactory.newXPath();

		try {
			return currXPath.evaluate(currExpression, currElement, resultFormat);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Ingests the XML document of the filename specified.
	 * 
	 * @param filename
	 * the name of the XML document to be parsed.
	 * @return an XML document node containing the ingested source.
	 * @throws XmlReadException
	 */
	public static Document read(String filename) throws XmlReadException {

		// Read saved data
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder;
		Document currDocument = null;
		try {
			documentBuilder = documentFactory.newDocumentBuilder();
			currDocument = documentBuilder.parse(filename);
		} catch (ParserConfigurationException e) {
			throw new XmlReadException(filename);
		} catch (SAXException e) {
			throw new XmlReadException(filename);
		} catch (IOException e) {
			throw new XmlReadException(filename);
		}

		return currDocument;
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as a boolean object.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @return the result of the query.
	 */
	public static Boolean readBoolean(Object currElement, String currExpression) {
		String asString = readString(currElement, currExpression);
		if (asString != null && !(asString.trim().equals(""))) {
			return Boolean.parseBoolean(asString);
		}
		return null;
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as a Date object.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @param the
	 * data format to be used.
	 * @return the result of the query.
	 */
	public static Date readDate(Object currElement, String currExpression, SimpleDateFormat currFormat) {
		String asString = readString(currElement, currExpression);
		if (asString != null && !asString.trim().equals("")) {
			try {
				return currFormat.parse(asString);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as a Double object.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @return the result of the query.
	 */
	public static Double readDouble(Object currElement, String currExpression) {
		String asString = readString(currElement, currExpression);
		if (asString != null && !asString.trim().equals("")) {
			return Double.parseDouble(asString);
		}
		return null;
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as an Element.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @return the result of the query.
	 */
	public static Element readElement(Object currElement, String currExpression) {
		return (Element) read(currElement, currExpression, XPathConstants.NODE);
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as a Integer object.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @return the result of the query.
	 */
	public static Integer readInteger(Object currElement, String currExpression) {
		String asString = readString(currElement, currExpression);
		if (asString != null && !(asString.trim().equals(""))) {
			return Integer.parseInt(asString);
		}
		return null;
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as a Long object.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @return the result of the query.
	 */
	public static Long readLong(Object currElement, String currExpression) {
		String asString = readString(currElement, currExpression);
		if (asString != null && !(asString.trim().equals(""))) {
			return Long.parseLong(asString);
		}
		return null;
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as a NodeList.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @return the result of the query.
	 */
	public static NodeList readNodeList(Object currElement, String currExpression) {
		return (NodeList) read(currElement, currExpression, XPathConstants.NODESET);
	}

	/**
	 * Queries the XML provided using the expression provided and returning the
	 * result as a String object.
	 * 
	 * @param currElement
	 * the source XML node.
	 * @param currExpression
	 * the XPath expression for querying the node.
	 * @return the result of the query.
	 */
	public static String readString(Object currElement, String currExpression) {
		return (String) read(currElement, currExpression, XPathConstants.STRING);
	}

}
