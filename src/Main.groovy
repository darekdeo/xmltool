import models.Item
import org.w3c.dom.Document

class Main {
    static void main(String... args) {
        XmlTool xmlTool = new XmlTool()

        try {
            String xml1Path = args[0]
            String xml2Path = args[1]

            List<Item> items1 = xmlTool.readXml(xml1Path)
            List<Item> items2 = xmlTool.readXml(xml2Path)

            for (Item item : items1) {
                if (!items2.contains(item)) {
                    items2.add(item);
                }
            }

            Document document = xmlTool.getXML(items2)
            xmlTool.writeXml("./merged_output.xml", document)
        } catch (IndexOutOfBoundsException e) {
            e.println("Missing param for one of source xml files.")
        }
    }
}
