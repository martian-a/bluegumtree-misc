package co.uk.bluegumtree.code.java.util;

import java.io.File;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

/**
 * <p>
 * An implementation of URIResolver that can be called by the processor to turn
 * a URI used within an XSLT stylesheet, for example via the document(),
 * xsl:import, or xsl:include statements, into a Source object.
 * </p>
 * <p>
 * To use:
 * </p>
 * <ul>
 * <li>create an instance of ResourceResolver<br />
 * <em>ResourceResolver resolver = new ResourceResolver();</em></li>
 * <li>set it on the TransformerFactory<br />
 * <em>factory.setURIResolver(new ResourceResolver()</em></li>
 * <li>check that your stylesheets have an xml:base attribute<br />
 * <em><xsl:stylesheet xml:base="xsl" /></em></li>
 * </ul>
 * 
 * @author Sheila Thomson 
 * 
 * @author Based on an example kindly published by Jez Higgins
 * (http://www.jezuk.co.uk/cgi-bin/view/jez?id=2260)
 */
public class ResourceResolver implements URIResolver {

	/**
	 * Called by the stylesheet whenever a resource (other than that stylesheet)
	 * is requested.
	 * 
	 * @param currURI
	 * the path to the resource that is being requested, as specified in the
	 * stylesheet.
	 * 
	 * @param baseURI
	 * the path to the directory that the resource is saved in within the jar.
	 * This will be supplied by the stylesheet making the request to the
	 * resolver, based on the value explicitly stated in that stylesheet. For
	 * example: <xsl:stylesheet xml:base="xsl" />
	 */
	@Override
	public Source resolve(String currURI, String baseURI) throws TransformerException {

		ClassLoader currLoader = this.getClass().getClassLoader();
		InputStream currInputStream = currLoader.getResourceAsStream(baseURI + File.separator + currURI);
		return new StreamSource(currInputStream, currURI);

	}
}