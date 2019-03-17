package it.discovery.events;

import it.discovery.logger.Logger;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EventBus {

    private final List<Logger> loggers;

    public void onLogEvent(LogEvent event){
        loggers.forEach(logger -> logger.log(event.getMessage()));
    }
}
