package Parser;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Main.MainClass;

public class XMLCreate {

	public void xmlCreate(Map<String, String> parametersValue, Parser parser)
			throws TransformerException {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			ElementGroup elementGroup = parser.elementGroup;
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(elementGroup
					.getGlobalName());
			doc.appendChild(rootElement);
			for (int i = 1; i < elementGroup.getElementsList().size(); i++) {
				if (elementGroup.getElementsList().get(i)
						.getParentElementName()
						.equals(elementGroup.getGlobalName())) {
					Element staff = doc.createElement(elementGroup
							.getElementsList().get(i).getElementName());
					rootElement.appendChild(staff);
					Attr attr = doc.createAttribute("name");
					String x = parametersValue.get(elementGroup
							.getElementsList().get(i).getElementName());
					attr.setValue(x);
					staff.setAttributeNode(attr);
				}
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			// daca vrei sa afisezi in fisier:
			StreamResult result = new StreamResult(new File(
					MainClass.getParametersPath()));

			// daca vrei sa afisezi la consola:
			// StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}
}