package com.kb.application.utils;

import com.kb.application.tasks.ScheduledTask;
import org.dom4j.ElementPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class InfoLogger {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private InfoLogger(){

    }

    public static void logFileName(File file) {
        log.info("File name: " + file.getName());
    }

    public static void logRootTag(ElementPath path){
        log.info(String.format("Root tag : %s", path.getCurrent().getQualifiedName()));
    }
}
