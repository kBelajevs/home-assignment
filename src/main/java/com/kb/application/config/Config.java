package com.kb.application.config;

import com.kb.application.exceptions.RootElementFoundException;
import com.kb.application.utils.FileSearcher;
import com.kb.application.utils.XmlReader;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.kb.application.utils.InfoLogger.logRootTag;

@Configuration
public class Config {

    @Value("${directory}")
    private String directory;

    private SAXReader saxReader() {
        SAXReader reader = new SAXReader();
        reader.setDefaultHandler(
                new ElementHandler() {
                    public void onStart(ElementPath path) {
                        logRootTag(path);
                        throw new RootElementFoundException();
                    }

                    public void onEnd(ElementPath path) {
                    }
                }
        );

        return reader;
    }

    @Bean
    public FileSearcher fileSearcher() {
        return new FileSearcher(directory);
    }

    @Bean
    public XmlReader xmlReader() {
        return new XmlReader(saxReader());
    }

}
