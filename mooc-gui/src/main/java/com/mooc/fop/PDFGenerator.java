package com.mooc.fop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

public class PDFGenerator {

	/* .. */

	// Step 1: Construct a FopFactory by specifying a reference to the
	// configuration file
	// (reuse if you plan to render multiple documents!)
	private static FopFactory fopFactory;

	public PDFGenerator() throws SAXException, IOException {
		if (fopFactory == null) {
			fopFactory = FopFactory.newInstance(new File(".").toURI());
		}
	}

	public boolean generateFO(InputStream xmlIN, String outputPath) {
		OutputStream out = null;
		try {
			// Step 2: Set up output stream.
			// Note: Using BufferedOutputStream for performance reasons (helpful
			// with FileOutputStreams).
			out = new BufferedOutputStream(new FileOutputStream(new File(outputPath)));

			InputStream courseIN = PDFGenerator.class.getClassLoader().getResourceAsStream("course-overview.xsl");
			Source xslSource = new StreamSource(courseIN);
			// Step 4: Setup JAXP using identity transformer
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(xslSource); // identity
																// transformer

			// Step 5: Setup input and output for XSLT transformation
			// Setup input stream

			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			Result res = new StreamResult(out);

			// Step 6: Start XSLT transformation and FOP processing
			StreamSource xmlSource = new StreamSource(xmlIN);
			transformer.transform(xmlSource, res);
			try {
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			// Clean-up
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean generatePDF(InputStream xmlIN, String outputPath) {

		OutputStream out = null;
		try {
			if (!generateFO(xmlIN, "course-overview.fo")) {
				return false;
			}
			// Step 2: Set up output stream.
			// Note: Using BufferedOutputStream for performance reasons (helpful
			// with FileOutputStreams).
			out = new BufferedOutputStream(new FileOutputStream(new File(outputPath)));
			
			// Step 3: Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			// Step 4: Setup JAXP using identity transformer
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(); // identity
																// transformer

			// Step 5: Setup input and output for XSLT transformation
			// Setup input stream
			Source src = new StreamSource(new File("course-overview.fo"));

			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Step 6: Start XSLT transformation and FOP processing
			transformer.transform(src, res);
			return true;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Clean-up
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
