package org.example.adapter;

import org.example.adaptee.XmlData;

public class XmlDataAdapter implements IJasonFormat {

    private XmlData xmlData;

    public XmlDataAdapter(XmlData xmlData) {
        this.xmlData = xmlData;
    }

    @Override
    public String getXMLtoJason(String data) {
        String store = xmlData.getXmlData(data);


        String id = extractValue(store, "id");
        String name = extractValue(store, "name");
        String role = "unknown";

        // Hardcode the JSON structure using a Text Block (Java 15+)
        return """
        {
            "user": {
                "id": %s,
                "name": "%s",
                "role": "%s"
            }
        }
        """.formatted(id, name, role);
    }

    private static String extractValue(String xml, String tag) {
        String openTag = "<" + tag + ">";
        String closeTag = "</" + tag + ">";

        int start = xml.indexOf(openTag) + openTag.length();
        int end = xml.indexOf(closeTag);

        return xml.substring(start, end).trim();
    }

}
