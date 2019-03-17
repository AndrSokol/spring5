package it.discovery.logger;

import javax.annotation.PostConstruct;

public class MemoryLogger implements Logger {

    @PostConstruct
    public void init(){
        System.out.println("Memory logger started");
    }

    @Override
    public void log(String message) {
        System.out.println("Memory logger: " + message);
    }
}
