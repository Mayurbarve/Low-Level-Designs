package org.example;

import org.example.adaptee.XmlData;
import org.example.adapter.IJasonFormat;
import org.example.adapter.XmlDataAdapter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        XmlData xmlData = new XmlData();

        IJasonFormat format = new XmlDataAdapter(xmlData);

        String str = "Chiku:01";

        FormatConverter converter = new FormatConverter();

        converter.getData(format, str);
    }
}
