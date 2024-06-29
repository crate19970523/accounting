package com.crater.accounting.task;

import com.crater.accounting.service.AutoSavingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoSavingTask {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private AutoSavingService autoSavingService;

    @Scheduled(cron = "${accounting.schedule.autoSaving}")
    public void runTask() {
        try {
            log.debug("will run AutoSavingTask");
            autoSavingService.autoSave();
        } catch (Exception e) {
            log.error("auto saving failed", e);
        }
    }

    @Autowired
    public void setAutoSavingService(AutoSavingService autoSavingService) {
        this.autoSavingService = autoSavingService;
    }
}
