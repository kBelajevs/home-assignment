package com.kb.application.utils;

import com.kb.application.exceptions.RootElementFoundException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class XmlReader {

    private SAXReader saxReader;

    public  XmlReader(SAXReader saxReader){
        this.saxReader = saxReader;
    }

    public void readXml(File inputFile) {
        try {
            saxReader.read(inputFile);
        } catch (Exception e) {
            if (!(e.getCause() instanceof RootElementFoundException)) {
                e.printStackTrace();
            }
        }
    }
}
