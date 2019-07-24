package com.kb.application.tasks;

import com.kb.application.utils.FileSearcher;
import com.kb.application.utils.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.kb.application.utils.InfoLogger.logFileName;

@Component
public class ScheduledTask {

    private XmlReader xmlReader;
    private FileSearcher fileSearcher;

    @Autowired
    public ScheduledTask(FileSearcher fileSearcher, XmlReader xmlReader) {
        this.fileSearcher = fileSearcher;
        this.xmlReader = xmlReader;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkDirectory() {
        fileSearcher.getApplicableFiles().forEach(file -> {
            logFileName(file);
            xmlReader.readXml(file);
        });
    }
}
