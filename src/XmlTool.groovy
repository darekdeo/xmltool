import models.Item

import javax.xml.parsers.*
import javax.xml.transform.*
import javax.xml.transform.dom.*
import javax.xml.transform.stream.*
import org.xml.sax.*
import org.w3c.dom.*

class XmlTool {

    def List<Item> readXml(String xml) {
        List<Item> items = new ArrayList<>()
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance()
        try {
            DocumentBuilder db = dbf.newDocumentBuilder()
            Document dom = db.parse(xml);

            NodeList nodeList1 = dom.getDocumentElement().getChildNodes()

            for (int i = 0; i < nodeList1.length; i++) {
                Node node = nodeList1.item(i)
                if (node.getNodeName() == "string") {
                    Item item = new Item()
                    item.id = node.getAttributes().getNamedItem("id").getNodeValue()
                    println item.id

                    NodeList childNodes = node.getChildNodes()
                    for (int j = 0; j < childNodes.length; j++) {
                        Node cNode = childNodes.item(j)

                        if (cNode.getNodeName() == "text") {
                            item.text = cNode.getLastChild().getTextContent().trim()
                        }
                    }
                    items.add(item);
                }
            }
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage())
        } catch (SAXException se) {
            System.out.println(se.getMessage())
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage())
        }
        return items;
    }

    def Document getXML(List<Item> items) {
        Document dom = null

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("string_table");

            // create data elements and place them under root
            for (Item item : items) {
                Element stringElem = dom.createElement("string")
                stringElem.setAttribute("id", item.id)

                Element textElem = dom.createElement("text")
                textElem.appendChild(dom.createTextNode(item.text));
                stringElem.appendChild(textElem)
                rootEle.appendChild(stringElem);
            }
            dom.appendChild(rootEle);
        } catch (ParserConfigurationException pce) {
            System.out.println("Error trying to instantiate DocumentBuilder " + pce);
        }
        return dom
    }

    def writeXml(String xmlPath, Document dom) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(dom),
                    new StreamResult(new FileOutputStream(xmlPath)));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
