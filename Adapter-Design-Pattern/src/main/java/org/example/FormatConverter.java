package org.example;

import org.example.adapter.IJasonFormat;

public class FormatConverter {

    public void  getData(IJasonFormat format, String data){
        System.out.println("Process JSON: ");

        System.out.println(format.getXMLtoJason(data));
    }
}
