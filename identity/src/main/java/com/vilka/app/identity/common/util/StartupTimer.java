package com.vilka.app.identity.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupTimer {
    private static final Logger log = LoggerFactory.getLogger(StartupTimer.class);
    private final long startTime = System.currentTimeMillis();

    @EventListener(ApplicationReadyEvent.class)
    public void logStartupTime() {
        long time = System.currentTimeMillis() - startTime;
        log.info("🚀 App started in {} ms", time);
    }
}
