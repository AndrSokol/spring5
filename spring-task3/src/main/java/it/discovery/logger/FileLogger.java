package it.discovery.logger;

import javax.annotation.PostConstruct;

public class FileLogger implements Logger {

    @PostConstruct
    public void init(){
        System.out.println("File logger started");
    }

    @Override
    public void log(String message) {
        System.out.println("FIle logger: " + message);
    }
}
