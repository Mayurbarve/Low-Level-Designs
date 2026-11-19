package org.example.adaptee;

public class XmlData {

    public String getXmlData(String xmlData) {
        String[] parts = xmlData.split(":");

        if (parts.length < 2) {                      // changed: 2 -> 3
            return "<error>Invalid format</error>";
        }

        String name = parts[0];
        String id = parts[1];

        // Build the XML string manually
        StringBuilder xml = new StringBuilder();
        xml.append("<record>\n");
        xml.append("  <name>").append(name).append("</name>\n");
        xml.append("  <id>").append(id).append("</id>\n");// added
        xml.append("</record>");

        return xml.toString();
    }
}