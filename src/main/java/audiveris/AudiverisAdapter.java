package audiveris;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.audiveris.omr.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class AudiverisAdapter {

	private void processImage(String filename) {
		String[] params = new String[8];
		params[0] = "-batch";
		params[1] = "-option";
		params[2] = "org.audiveris.omr.sheet.BookManager.useCompression=false";
		params[3] = "-export";
		params[4] = "-output";
		params[5] = ".\\output";
		params[6] = "--";
		params[7] = ".\\data\\" + filename + ".png";

		Main.main(params);
	}

	private String processXML(String filename) {
		String s = "";
		
		File inputFile = new File("output\\" + filename + "\\" + filename + ".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			dBuilder.setEntityResolver(new EntityResolver() {
		        @Override
		        public InputSource resolveEntity(String publicId, String systemId)
		                throws SAXException, IOException {
		            if (systemId.contains(".dtd")) {
		                return new InputSource(new StringReader(""));
		            } else {
		                return null;
		            }
		        }
		    });
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList notes = doc.getElementsByTagName("note");
			for (int i = 0; i < notes.getLength(); i++) {
				Node note = notes.item(i);
				NodeList noteChilds = note.getChildNodes();
				for (int j = 0; j < noteChilds.getLength(); j++) {
					if (noteChilds.item(j).getNodeName() == "pitch") {
						NodeList pitchChilds = noteChilds.item(j).getChildNodes();
						for (int k = 0; k < pitchChilds.getLength(); k++) {
							if (pitchChilds.item(k).getNodeName() == "step") {
								s+=pitchChilds.item(k).getTextContent();
								s += ", ";
							}
						}
					}
				}

			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		if (s.length() >= 2) s = s.substring(0,s.length() - 2); //quitamos la última coma
		return s;

	}

	public String convertImage(String filename) {
		processImage(filename);
		return processXML(filename);
	}
}
